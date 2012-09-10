package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.AlarmeBO;
import br.com.fiap.coleta.bo.BancoDadosBO;
import br.com.fiap.coleta.bo.IndisponibilidadeBO;
import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoFileColeta;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.coleta.entities.BancoJobColeta;
import br.com.fiap.coleta.entities.BancoMemoriaColeta;
import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class SQLServerColeta {
	
	private SQLServer sqlserver;
	
	private BancoDadosBO bancoDadosBO;
	
	private IndisponibilidadeBO indisponibilidadeBO;
	
	private AlarmeBO alarmeBO;
	
	private SocketUtil socket;
	
	private Date dataColeta;
	
	private Map<String,BancoFile> files;
	
	private List<BancoBackup> backups;
	
	private Map<String,BancoJob> jobs;
	
	private Indisponibilidade indisp;
	
	private Boolean ultimoStatus;
	
	private Boolean ultimoGerenciavel;
	
	
	public SQLServerColeta(No no){
		
		this.sqlserver = (SQLServer) no;
		
		this.bancoDadosBO = new BancoDadosBO();
		this.alarmeBO = new AlarmeBO();
		this.indisponibilidadeBO = new IndisponibilidadeBO();
		
		if(this.sqlserver.getUltimaColeta() != null){
			this.ultimoStatus = this.sqlserver.getDisponivel();
			this.ultimoGerenciavel = this.sqlserver.getGerenciavel();
		}else{
			this.ultimoStatus = false;
			this.ultimoGerenciavel = false;
		}
		
	}
	
	public void initColeta(){
		
	
		try{
			
			this.dataColeta = new Date();
			this.sqlserver.setUltimaColeta(dataColeta);
			
			// Pega utlima indisponibilidade
			this.indisp = this.indisponibilidadeBO
					.pegaUltimaInstanciaIndisponibilidade(this.sqlserver);
			
			
			
			if(connect()){
				socket = new SocketUtil(this.sqlserver.getHostname(), this.sqlserver.getAgentPort());
				
				//Abre o socket
				socket.openSocket();
				this.setCredentials();
				
				//Pega a data atual
				this.dataColeta = new Date();		
				
				//Atualiza os itens de configura��o
				this.getConfigMemory();
				this.getConfigCollation();
				this.getConfigVersion();
				this.getStatus();
				
				files = this.getConfigFiles();
				backups = this.getConfigBackup();
				jobs = this.getConfigJobHistory(); 
				
				
				//Salva os itens de configura��o.
				this.bancoDadosBO.salvaConfigFiles(files);
				this.bancoDadosBO.salvaConfigBackups(backups);
				this.bancoDadosBO.salvaConfigJobs(jobs);
				
				//Pega os jobs e files com IDs, necess�rios para as coletas.
				jobs = this.bancoDadosBO.pegaMapJobsBancoDados(this.sqlserver);
				files = this.bancoDadosBO.pegaMapFilesBancoDados(this.sqlserver);
				
				//Realiza as coletas
				BancoMemoriaColeta memoriaColeta= this.getMemoriaColeta();
				List<BancoFileColeta> filesColeta = this.getFilesColeta();
				List<BancoJobColeta> jobsColeta = this.getJobsColeta();
				
				//Fecha o socket
				socket.close();

				//Persiste tudo
				this.bancoDadosBO.salvaColetaMemoria(memoriaColeta);
				this.bancoDadosBO.salvaColetasFiles(filesColeta);
				this.bancoDadosBO.salvaColetasJobs(jobsColeta);
				
				this.sqlserver.setUltimaColeta(this.dataColeta);
				
				this.sqlserver.setGerenciavel(true);
			}
			
		}catch (IOException e) {
			System.out.println("Imposs�vel abrir o socket. Verifique se o agente est� instalado no servidor.");
			this.sqlserver.setGerenciavel(false);
		}catch (Exception e){
			e.printStackTrace();
			this.sqlserver.setGerenciavel(false);
		}finally{
			
			BancoBackup ultimoBackup = this.bancoDadosBO.pegaUltimoBackup(this.sqlserver);
			this.alarmeBO.geraAlarmeUltimoBackup(this.sqlserver,ultimoBackup);
			
			this.alarmeBO.geraAlarmeNaoGerenciavel(this.sqlserver, ultimoGerenciavel);
			
			this.bancoDadosBO.salvaBanco(this.sqlserver);
		}
		
	}
	
	public Boolean connect(){
		
		boolean result = false;
		
		String url = "jdbc:jtds:sqlserver://"+this.sqlserver.getHostname()+"/"+this.sqlserver.getDatabase()+";instance="+this.sqlserver.getInstanceName();
		String usuario = this.sqlserver.getUsuario();
		String senha = this.sqlserver.getSenha();
		
		try{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Connection conn =  DriverManager.getConnection(url,usuario,senha);
			this.sqlserver.setDisponivel(true);
			conn.close();
			
			result = true;
			
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}catch(SQLException ex){
			this.sqlserver.setDisponivel(false);
			result = false;
			
		}finally{
			
			this.sqlserver.setUltimaColeta(dataColeta);
			
			if (!this.sqlserver.getDisponivel() && (ultimoStatus || this.indisp == null)){

				if (this.indisp == null) {
					this.indisp = new Indisponibilidade();
					this.indisp.setNo(this.sqlserver);
					this.indisp.setInicio(this.dataColeta);
				}
				
				
			} else if (this.sqlserver.getDisponivel() && this.indisp !=null){
				this.indisp.setFim(this.dataColeta);
			}
			
			if(indisp != null){
				this.indisponibilidadeBO.salvaIndisponibilidade(indisp);
			}
			
		}
		
		this.alarmeBO.geraAlarmeIndsiponibilidade(this.sqlserver, ultimoStatus);
		
		return result;
		
	}
	
	private void setCredentials(){
		try{
			this.socket.enviaComando("get mssql.credentials " + this.sqlserver.getUsuario() + " " + this.sqlserver.getSenha() + " " + this.sqlserver.getHostname() + " " + this.sqlserver.getPort());
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private List<BancoJobColeta> getJobsColeta() {
		List<BancoJobColeta> jobsColeta= null;
		
		try{
			if(this.files != null){
				jobsColeta = new ArrayList<BancoJobColeta>();
				JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.jobHistory"));

				if(json != null){
					JSONArray jsonArray = json.getJSONArray("jobhistory");
				 	
					for (Object object : jsonArray) {
						JSONObject i = (JSONObject) object;
						String jobName = i.getString("JobName");
						
						BancoJob job = this.jobs.get(jobName);
						Long logId = i.getLong("LogID");


						
						if(job != null){
							Boolean coletaSalva = this.bancoDadosBO.verificaColetaJobSalva(job,logId);
							
							if(!coletaSalva){
								BancoJobColeta coleta = new BancoJobColeta(job);
								
								String strStatus = i.getString("Status");
																
								coleta.setDataColeta(this.dataColeta);
								coleta.setLogId(logId);
								coleta.setExecutionTime(i.getLong("Duracao"));
								coleta.setDataExecucao(new Date(i.getLong("DataExecucao")));
								coleta.setStatusDescr(strStatus);
								coleta.setSqlMsg(i.getString("MensagemSQL"));
								
								if(strStatus.equals("SUCCEEDED")){
									coleta.setStatus(2);
									coleta.setSqlMsg(null);
								}else if(strStatus.equals("FAILED")){
									coleta.setStatus(0);
								}else{
									coleta.setStatus(1);
								}
								
								this.alarmeBO.geraAlarmesJobs(this.sqlserver,coleta,strStatus);
		
								jobsColeta.add(coleta);
							
							}
						}						
					}
				}				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return jobsColeta;
	}

	

	private List<BancoFileColeta> getFilesColeta() {
		
		List<BancoFileColeta> filesColeta= null;
		
		try{
			
			if(this.files != null){
				filesColeta = new ArrayList<BancoFileColeta>();
				JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.files"));

				if(json != null){
					JSONArray jsonArray = json.getJSONArray("files");
				 	
					for (Object object : jsonArray) {
						JSONObject i = (JSONObject) object;
						String idFile = i.getString("FileName");
						
						BancoFile file = this.files.get(idFile);
						
						if(file != null){
							BancoFileColeta coleta = new BancoFileColeta(file);
							coleta.setDataColeta(this.dataColeta);
							coleta.setSize(i.getLong("Size"));
							filesColeta.add(coleta);
							
							alarmeBO.geraAlarmeFileBancoDados(this.sqlserver,coleta);
								
						}						
					}
				}				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return filesColeta;
		
	}

	private BancoMemoriaColeta getMemoriaColeta() {
		BancoMemoriaColeta coleta = null;
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.memory"));
			
			if(json != null){
				coleta = new BancoMemoriaColeta(this.sqlserver);
				coleta.setDataColeta(this.dataColeta);
				coleta.setMemory(json.getLong("totalMemory"));
				coleta.setStolenMemory(json.getLong("stolenMemory"));
				
				BigDecimal utilizacao = new BigDecimal((coleta.getMemory().doubleValue()/this.sqlserver.getTargetServerMemory()) * 100);
				
				this.alarmeBO.geraAlarmeMemoriaBancoDados(this.sqlserver, utilizacao);
				
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return coleta;
	}

	private String getConfigCollation(){
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.collation "+this.sqlserver.getDatabase()));
			
			if(json == null){
				this.sqlserver.setGerenciavel(false);
			}else{
				this.sqlserver.setCollation(json.getString("Collation"));
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return null;
	
	}

	
	private void getConfigVersion(){
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.version"));
			
			if(json == null){
				this.sqlserver.setGerenciavel(false);
			}else{
				this.sqlserver.setVersion(json.getString("Version"));
				this.sqlserver.setEdition(json.getString("Edition"));
			}
									
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	
	}
	
	
	private Map<String,BancoFile> getConfigFiles(){
		Map<String,BancoFile> mapFiles = null;
		mapFiles = bancoDadosBO.pegaMapFilesBancoDados(this.sqlserver);
		
		if(mapFiles == null){
			mapFiles = new HashMap<String,BancoFile>();
		}
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.files"));
			JSONArray jsonArray = json.getJSONArray("files");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String idFile = i.getString("FileName");
				
				BancoFile file = mapFiles.get(idFile);
				
				if(file==null){
					file = new BancoFile(this.sqlserver);
				}
				
				file.setFile(idFile);
				file.setFilePath(i.getString("FilePath"));
				file.setMaxSize(i.getLong("Maxsize"));
				file.setGrowth(i.getString("Growth"));
				file.setSituacao(i.getString("Situacao"));
				file.setFileName(i.getString("FileName"));
				file.setDatabaseName(i.getString("DatabaseName"));
				file.setAtivo(true);
				
				mapFiles.put(idFile, file);
						
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return mapFiles;
		
	}
	
	private void getConfigMemory(){
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.memory"));
			
			if(json == null){
				this.sqlserver.setGerenciavel(false);
			}else{
				this.sqlserver.setTargetServerMemory(json.getLong("targetMemory"));
			}		
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		
	}
		
	
	private List<BancoBackup> getConfigBackup(){
		List<BancoBackup> listaBackup= new ArrayList<BancoBackup>();
		
		try{
			
			Long ultimoSet = this.bancoDadosBO.pegaUltimoSetBackup(this.sqlserver);
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.backup " + ultimoSet));
			JSONArray jsonArray = json.getJSONArray("backup");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				
				BancoBackup b = new BancoBackup(this.sqlserver);
				
				b.setFileName(i.getString("FileName"));
				b.setBackupStartDate(new Date(i.getLong("BackupStartDate")));
				b.setTempoExecucao(i.getLong("TempoExecucao"));
				b.setRecoveryModel(i.getString("RecoveryModel"));
				b.setTamanho(i.getLong("Tamanho"));
				b.setSetCount(i.getLong("SetCount"));
				
				b.setTipo(i.getString("Tipo"));
				b.setDatabaseName(i.getString("DatabaseName"));

				
				listaBackup.add(b);
				
			}
			
			//TODO Alarmar
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaBackup;
		
	}

	

	private Map<String,BancoJob> getConfigJobHistory(){
		Map<String,BancoJob> map = bancoDadosBO.pegaMapJobsBancoDados(this.sqlserver);
		
		if(map == null){
			map = new HashMap<String, BancoJob>();
		}
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.jobs"));
			JSONArray jsonArray = json.getJSONArray("jobs");
			
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String jobName = i.getString("JobName");
				
				
				BancoJob job = map.get(jobName); 
				
				if(job == null){
					job = new BancoJob(this.sqlserver);
				}
							
				job.setJobName(i.getString("JobName"));
				job.setOwner(i.getString("Owner"));

				map.put(jobName,job);				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return map;
		
	}
	
	private void getStatus(){
				
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.status " + this.sqlserver.getDatabase()));
			if(json == null){
				this.sqlserver.setGerenciavel(false);				
				
			}else{
				String ultimoStatusBanco = this.sqlserver.getStatus();
				
				this.sqlserver.setStatus(json.getString("Status"));
				
				this.alarmeBO.geraAlarmeStatus(this.sqlserver,ultimoStatusBanco);
			}
								
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	
}
