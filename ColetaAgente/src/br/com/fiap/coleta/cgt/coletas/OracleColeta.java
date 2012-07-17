package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.BancoDadosBO;
import br.com.fiap.coleta.bo.OracleBO;
import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoFileColeta;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.coleta.entities.BancoJobColeta;
import br.com.fiap.coleta.entities.BancoMemoriaColeta;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class OracleColeta {
	
	private Oracle oracle;
	
	private BancoDadosBO bancoDadosBO;
	
	private OracleBO oracleBO;
	
	private SocketUtil socket;
	
	private Date dataColeta;
	
	private Map<String,BancoFile> files;
	
	private List<BancoBackup> backups;
	
	private Map<String,BancoJob> jobs;
	
	public OracleColeta(No no){
		this.oracle = (Oracle) no;
		this.bancoDadosBO = new BancoDadosBO();
		this.oracleBO = new OracleBO();
	}
	
	public void initColeta(){

			socket = new SocketUtil(this.oracle.getHostname(), 9090);
			
			try{
				
				
				
				//Abre o socket
				socket.openSocket();
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
				
			}catch (IOException e) {
				System.out.println("Imposs�vel abrir o socket. Verifique se o agente est� instalado no servidor.");
				this.oracle.setGerenciavel(false);
			}catch (Exception e){
				e.printStackTrace();
				this.oracle.setGerenciavel(false);
			}finally{
				this.bancoDadosBO.salvaBanco(this.oracle);
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
								
								if(strStatus.equals("SUCCEEDED")){
									coleta.setStatus(true);
								}else{
									coleta.setStatus(false);
								}
								
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
			
			Long ultimoSet = this.oracleBO.pegaUltimoSetBackup(this.oracle);
			
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
				this.oracle.setStatus(json.getString("Status"));
			}
								
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	
}