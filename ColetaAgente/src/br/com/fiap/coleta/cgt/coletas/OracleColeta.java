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
import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class OracleColeta {
	
	private Oracle oracle;
	
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
	
	public OracleColeta(No no){
		this.oracle = (Oracle) no;
		this.bancoDadosBO = new BancoDadosBO();
		this.alarmeBO = new AlarmeBO();
		
		this.indisponibilidadeBO = new IndisponibilidadeBO();
		
		if(this.oracle.getUltimaColeta() != null){
			this.ultimoStatus = this.oracle.getDisponivel();
			this.ultimoGerenciavel = this.oracle.getGerenciavel();
		}else{
			this.ultimoStatus = false;
			this.ultimoGerenciavel = false;
		}
	}
	
	public void initColeta(){

			socket = new SocketUtil(this.oracle.getHostname(), this.oracle.getAgentPort());
			
			try{

				this.oracle.setUltimaColeta(dataColeta);
				
				// Pega utlima indisponibilidade
				this.indisp = this.indisponibilidadeBO
						.pegaUltimaInstanciaIndisponibilidade(this.oracle);
				
				if(connect()){
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
					jobs = this.bancoDadosBO.pegaMapJobsBancoDados(this.oracle);
					files = this.bancoDadosBO.pegaMapFilesBancoDados(this.oracle);
					
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
					
					this.oracle.setGerenciavel(true);
				}
				
			}catch (IOException e) {
				System.out.println("Impossivel abrir o socket. Verifique se o agente esta instalado no servidor.");
				this.oracle.setGerenciavel(false);
			}catch (Exception e){
				e.printStackTrace();
				this.oracle.setGerenciavel(false);
			}finally{
				
				BancoBackup ultimoBackup = this.bancoDadosBO.pegaUltimoBackup(this.oracle);
				this.alarmeBO.geraAlarmeUltimoBackup(this.oracle,ultimoBackup);
				
				this.alarmeBO.geraAlarmeNaoGerenciavel(this.oracle, ultimoGerenciavel);
				
				this.bancoDadosBO.salvaBanco(this.oracle);
			}
			
		
	}
	
	public Boolean connect(){
		
		boolean result = false;
		
		String url = "jdbc:oracle:thin:@"+this.oracle.getHostname()+":"+this.oracle.getPort()+":"+this.oracle.getInstanceName();
		String usuario = this.oracle.getUsuario();
		String senha = this.oracle.getSenha();
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn =  DriverManager.getConnection(url,usuario,senha);
			this.oracle.setDisponivel(true);
			conn.close();
			
			result = true;
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}catch(SQLException ex){
			ex.printStackTrace();
			this.oracle.setDisponivel(false);
			result = false;
		}finally{
			
			this.oracle.setUltimaColeta(dataColeta);
			
			if (this.oracle.getDisponivel() && (ultimoStatus || this.indisp == null)){

				if (this.indisp == null) {
					this.indisp = new Indisponibilidade();
					this.indisp.setNo(this.oracle);
					this.indisp.setInicio(this.dataColeta);
				}
				
				
			} else if (this.oracle.getDisponivel() && this.indisp !=null && !ultimoStatus){
				this.indisp.setFim(this.dataColeta);
			}
			
			if(indisp != null){
				this.indisponibilidadeBO.salvaIndisponibilidade(indisp);
			}
			
		}
		
		this.alarmeBO.geraAlarmeIndsiponibilidade(this.oracle, ultimoStatus);
		
		return result;	
		
	}
	
	private void setCredentials(){
		try{
			this.socket.enviaComando("get ora.credentials " + this.oracle.getUsuario() + " " + this.oracle.getSenha() + " " + this.oracle.getHostname() + " " + this.oracle.getPort());
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
				JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.jobHistory"));

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
									//N�o guarda a mensagem se for succeeded
									coleta.setSqlMsg(null);
								}else if(strStatus.equals("FAILED")){
									coleta.setStatus(0);
								}else{
									coleta.setStatus(1);
								}
								
								this.alarmeBO.geraAlarmesJobs(this.oracle,coleta,strStatus);
								
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
				JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.files"));

				if(json != null){
					JSONArray jsonArray = json.getJSONArray("files");
				 	
					for (Object object : jsonArray) {
						JSONObject i = (JSONObject) object;
						String idFile = i.getString("File");
						
						BancoFile file = this.files.get(idFile);
						
						if(file != null){
							BancoFileColeta coleta = new BancoFileColeta(file);
							coleta.setDataColeta(this.dataColeta);
							coleta.setSize(i.getLong("Size"));
							filesColeta.add(coleta);
							
							alarmeBO.geraAlarmeFileBancoDados(this.oracle,coleta);
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
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.memory"));
			
			if(json != null){
				
				coleta = new BancoMemoriaColeta(this.oracle);
				coleta.setDataColeta(this.dataColeta);
				coleta.setMemory(json.getLong("totalMemory"));
				
				BigDecimal utilizacao = new BigDecimal((coleta.getMemory().doubleValue()/this.oracle.getTargetServerMemory()) * 100);
				
				this.alarmeBO.geraAlarmeMemoriaBancoDados(this.oracle, utilizacao);

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
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.collation"));
			
			if(json == null){
				this.oracle.setGerenciavel(false);
			}else{
				this.oracle.setCollation(json.getString("Collation"));
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
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.version"));
			
			if(json == null){
				this.oracle.setGerenciavel(false);
			}else{
				this.oracle.setVersion(json.getString("Version"));
				this.oracle.setEdition(json.getString("Edition"));
			}
									
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	
	}
	
	
	private Map<String,BancoFile> getConfigFiles(){
		Map<String,BancoFile> mapFiles = null;
		mapFiles = bancoDadosBO.pegaMapFilesBancoDados(this.oracle);
		
		if(mapFiles == null){
			mapFiles = new HashMap<String,BancoFile>();
		}
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.files"));
			JSONArray jsonArray = json.getJSONArray("files");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String idFile = i.getString("File");
				
				BancoFile file = mapFiles.get(idFile);
				
				if(file==null){
					file = new BancoFile(this.oracle);
				}
				
				file.setFile(idFile);
				file.setFilePath(i.getString("FilePath"));
				file.setMaxSize(i.getLong("Maxsize"));
				file.setGrowth(i.getString("Growth"));
				file.setSituacao(i.getString("Situacao"));
				file.setFileName(i.getString("FileName"));
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
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.memory"));
			
			if(json == null){
				this.oracle.setGerenciavel(false);
			}else{
				this.oracle.setTargetServerMemory(json.getLong("targetMemory"));
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
			
			Long ultimoSet = this.bancoDadosBO.pegaUltimoSetBackup(this.oracle);
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.backup " + ultimoSet));
			JSONArray jsonArray = json.getJSONArray("backup");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				
				BancoBackup b = new BancoBackup(this.oracle);
				
				b.setFileName(i.getString("FileName"));
				b.setBackupStartDate(new Date(i.getLong("BackupStartDate")));
				b.setTempoExecucao(i.getLong("TempoExecucao"));
				b.setRecoveryModel(i.getString("RecoveryModel"));
				b.setTamanho(i.getLong("Tamanho"));
				b.setSetCount(i.getLong("SetCount"));
				
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
		Map<String,BancoJob> map = bancoDadosBO.pegaMapJobsBancoDados(this.oracle);
		
		if(map == null){
			map = new HashMap<String, BancoJob>();
		}
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.config.jobHistory"));
			JSONArray jsonArray = json.getJSONArray("jobhistory");
			
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String jobName = i.getString("JobName");
				
				
				BancoJob job = map.get(jobName); 
				
				if(job == null){
					job = new BancoJob(this.oracle);
				}
							
				job.setOwner(i.getString("Owner"));
				job.setJobName(i.getString("JobName"));

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
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get ora.status"));
			if(json == null){
				this.oracle.setGerenciavel(false);
			}else{
				String ultimoStatusBanco = this.oracle.getStatus();
				
				this.oracle.setStatus(json.getString("Status"));
				
				this.alarmeBO.geraAlarmeStatus(this.oracle,ultimoStatusBanco);

			}
								
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	
}
