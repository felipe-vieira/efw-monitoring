package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.ServidorBO;
import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoFileColeta;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.coleta.entities.BancoLogSpace;
import br.com.fiap.coleta.entities.BancoLogSpaceColeta;
import br.com.fiap.coleta.entities.BancoMemoriaColeta;
import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.MemoriaColeta;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.ParticaoColeta;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.ProcessadorColeta;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class SqlServerColetaOld {
	
	private Servidor servidor;
	
	private SQLServer sqlServer;
	
	private BancoBackup bancoBackup;
	
	private BancoJob bancoJobExecution;
			
	private BancoFile bancoFiles;
	
	private BancoLogSpace bancoLogSpace;
	
	//private SQLServerBO sqlServerBO;
	
	private SocketUtil socket;
	
	private Date dataColeta;
	
	public SqlServerColetaOld(No no){
		this.servidor = (Servidor) no;
		this.sqlServer = (SQLServer) no;
		//this.sqlServerBO = new SQLServerBO();
		
	}
	
	public void initColeta(){

		/*
		 * LEMBRAR DE CRIAR ROTINA DE VERIFICAÇÃO DE DISPONIBILIDADE DO BANCO
		 */
			socket = new SocketUtil(this.servidor.getHostname(), 9090);
			
			try{
				
				//Abre o socket
				socket.openSocket();
				
				//Pega a data atual
				this.dataColeta = new Date();
				
				SQLServer sqlServer = null;
				ProcessadorColeta processadorColeta = null;
				List<ParticaoColeta> listaParticaoColeta = new ArrayList<ParticaoColeta>();
				
				/*
				//Atualiza os itens de configuração
				this.sqlServer.setTargetServerMemory(this.getConfigMemory());
				this.sqlServer.setVersion(this.getConfigVersion());
				this.sqlServer.setCollation(this.getConfigCollation());
				
				//Faz as coletas
				sqlServer = this.getMemory();
				sqlServer = this.getStatus();								
				
				for(Particao p : this.servidor.getParticoes()){
					listaParticaoColeta.add(this.getOsPartition(p));
				}
				
				for(Particao p : this.servidor.getParticoes()){
					listaParticaoColeta.add(this.getOsPartition(p));
				}
				*/
				//Fecha o socket
				socket.close();
				
				/*
				//Persiste tudo
				this.servidorBO.updateServidorColeta(this.servidor);
				this.servidorBO.saveColetaMemoria(memoriaColeta);
				this.servidorBO.saveColetaProcessador(processadorColeta);
				this.servidorBO.saveListaColetaParticao(listaParticaoColeta);
				*/
				
			}catch (IOException e) {
				System.out.println("Impossível abrir o socket. Verifique se o agente está instalado no servidor.");
			}catch (Exception e){
				e.printStackTrace();
			}
		
	}
/*	
	private Boolean verificaDisponibilidade(){
		try{
			if(InetAddress.getByName(this.servidor.getHostname()).isReachable(30000)){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			return false;
		}
	}
	
	
	private BancoProperties getConfigCollation(){
		BancoProperties properties = sqlServerBO.pegaPropertiesSQLServer(this.sqlServer);
		
		if(properties == null){
			properties = new BancoProperties(this.sqlServer);
		}
		
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.collation"));
			//System.out.println(json.toString());
			
			properties.setVersion(json.getString("Collation"));
						
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return properties;
	
	}
	
	private BancoProperties getConfigVersion(){
		BancoProperties properties = sqlServerBO.pegaPropertiesSQLServer(this.sqlServer);
		
		if(properties == null){
			properties = new BancoProperties(this.sqlServer);
		}
		
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.version"));
			//System.out.println(json.toString());
			
			properties.setVersion(json.getString("Version"));
						
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return properties;
	
	}
	
	private List<BancoFiles> getConfigFiles(){
		List<BancoFiles> listaFiles = new ArrayList<BancoFiles>();
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.files"));
			JSONArray jsonArray = json.getJSONArray("files");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String fileName = i.getString("filename");
				
				BancoFiles f = sqlServerBO.pegaFilesSQLServer(this.sqlServer, fileName);
				
				if(f==null){
					f = new BancoFiles(this.sqlServer);
					f.setFileName(fileName);
				}
				
				f.setDBName(i.getString("File"));
				f.setFilePath(i.getString("filePath"));
				f.setMaxSize(i.getLong("maxSize"));
				f.setGrowth(i.getString("growth"));
				f.setSituacao(i.getString("situacao"));
				f.setFileName(i.getString("fileName"));
				//Pega em KBytes
				
				listaFiles.add(f);
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaFiles;
		
	}
	
	private BancoMemoria getConfigMemory(){
		BancoMemoria memoria = sqlServerBO.pegaMemoriaSQLServer(sqlServer);
		
		if(memoria == null){
			memoria = new BancoMemoria(this.sqlServer);
		}
		
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.memory"));
			System.out.println(json.toString());
			
			//pega em bytes
			memoria.setTargetServerMemory(json.getLong("targetMemory"));
			
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return memoria;
	}
	
	private List<BancoLogSpace> getConfigLogSpace(){
		List<BancoLogSpace> listaLogSpace = new ArrayList<BancoLogSpace>();
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.logspace"));
			JSONArray jsonArray = json.getJSONArray("logspace");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String dbName = i.getString("Database Name");
				
				BancoLogSpace b = sqlServerBO.pegaJobHistorySQLServer(this.sqlServer, dbName);
				
				if(b==null){
					b = new BancoLogSpace(this.sqlServer);
					b.setDbName(dbName);
				}
				
				b.setDbName(dbName);
				
				listaLogSpace.add(b);
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaLogSpace;
		
	}
		
	
	private List<BancoBackup> getConfigBackup(){
		List<BancoBackup> listaBackup= new ArrayList<BancoBackup>();
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.backup"));
			JSONArray jsonArray = json.getJSONArray("backup");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String instanceName = i.getString("Instance Name");
				
				BancoBackup b = sqlServerBO.pegaBackupSQLServer(this.sqlServer, instanceName);
				
				if(b==null){
					b = new BancoBackup(this.sqlServer);
					b.setInstanceName(instanceName);
				}
				
				b.setFileName(i.getString("File Name"));
				b.setBackupStartDate(i.getDate("Backup Start Date"));
				b.setRecoveryModel(i.getString("Recovery Model"));
				b.setServerName(i.getString("Server Name"));
				
				listaBackup.add(b);
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaBackup;
		
	}
	
	private List<BancoJobExecution> getConfigJobRunning(){
		List<BancoJobExecution> listaJobRunning= new ArrayList<BancoJobExecution>();
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.jobRunning"));
			JSONArray jsonArray = json.getJSONArray("jobRunning");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String jobName = i.getString("Job Name");
				
				BancoJobExecution b = sqlServerBO.pegaJobExecutionSQLServer(this.sqlServer, jobName);
				
				if(b==null){
					b = new BancoJobExecution(this.sqlServer);
					b.setJobName(jobName);
				}
				
				
				b.setJobName(i.getString("Job Name"));
				b.setRunDate(i.getDate("Run Date"));
				
				listaJobRunning.add(b);
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaJobRunning;
		
	}
	
	private List<BancoJobHistory> getConfigJobHistory(){
		List<BancoJobHistory> listaJobHistory = new ArrayList<BancoJobHistory>();
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.jobHistory"));
			JSONArray jsonArray = json.getJSONArray("jobHistory");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String jobName = i.getString("Job Name");
				
				BancoJobHistory b = sqlServerBO.pegaJobHistorySQLServer(this.sqlServer, jobName);
				
				if(b==null){
					b = new BancoJobHistory(this.sqlServer);
					b.setJobName(jobName);
				}
				
				b.setJobName(i.getString("Job Name"));
				b.setStatus(i.getString("Status"));
				b.setRunDate(i.getDate("Data de Execucao"));
				b.setSqlMessage(i.getString("Mensagem SQL"));
				
				listaJobHistory.add(b);
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaJobHistory;
		
	}
	
	
	//Metodos de coleta
	private BancoMemoriaColeta getMemory(){
		
		BancoMemoriaColeta coleta = null;
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.memory"));
			//System.out.println(json.toString());
			
			coleta = new BancoMemoriaColeta(this.bancoMemoria.getBancoMemoria());
			
			//pega em bytes
			coleta.setDataColeta(this.dataColeta);
			coleta.setTotalServerMemory(json.getLong("totalMemory"));
			coleta.setStolenServerMemory(null);
					
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return coleta;
	}
	
	private List<BancoLogSpaceColeta> getLogSpace(){
		
		List<BancoLogSpaceColeta> listaLogSpaceColeta= new ArrayList<BancoLogSpaceColeta>();
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.logspace"));
			JSONArray jsonArray = json.getJSONArray("logspace");
			//System.out.println(json.toString());
			
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String id = i.getString("id");
				
				BancoLogSpaceColeta b = sqlServerBO.pegaLogSpaceColetaSQLServer(this.bancoLogSpace, id);
				
				if(b==null){
					b = new BancoLogSpaceColeta(this.bancoLogSpace);
					b.setId(i.getLong(id));
				}
				
				b.setDataColeta(this.dataColeta);
				b.setLogSize(json.getLong("Log Size (MB)"));
				b.setLogSpaceUsed(json.getLong("Log Space Used (%)"));
				
				listaLogSpaceColeta.add(b);
				
			}
					
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaLogSpaceColeta;
		
	}
	
	private BancoFilesColeta getFiles(){
		
		BancoFilesColeta coleta = null;
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.files"));
			//System.out.println(json.toString());
			
			coleta = new BancoFilesColeta(this.bancoFiles.getBancoFiles());
			
			//pega em bytes
			coleta.setDataColeta(this.dataColeta);
			coleta.setSize(json.getLong("Size"));
					
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return coleta;
	}
	
	private List<BancoBackupColeta> getBackup(){
		
		List<BancoBackupColeta> listaBackupColeta= new ArrayList<BancoBackupColeta>();
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.backup"));
			JSONArray jsonArray = json.getJSONArray("backup");
			//System.out.println(json.toString());
			
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String id = i.getString("id");
				
				BancoBackupColeta b = sqlServerBO.pegaBackupColetaSQLServer(this.bancoBackup, id);
				
				if(b==null){
					b = new BancoBackupColeta(this.bancoBackup);
					b.setId(i.getLong(id));
				}
				
				b.setDataColeta(this.dataColeta);
				b.setTamanhoKB(json.getLong("Tamanho (KB)"));
				b.setTempoDeExecucaoM(json.getLong("Tempo de Execucao (M)"));
				
				listaBackupColeta.add(b);
				
			}
					
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaBackupColeta;
		
	}
	
	private List<BancoJobExecutionColeta> getJobExecution(){
		
		List<BancoJobExecutionColeta> listaJobExecutionColeta= new ArrayList<BancoJobExecutionColeta>();
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.jobRunning"));
			JSONArray jsonArray = json.getJSONArray("jobRunning");
			//System.out.println(json.toString());
			
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String id = i.getString("id");
				
				BancoJobExecutionColeta b = sqlServerBO.pegaBackupColetaSQLServer(this.bancoBackup, id);
				
				if(b==null){
					b = new BancoJobExecutionColeta(this.bancoJobExecution);
					b.setId(i.getLong(id));
				}
				
				b.setDataColeta(this.dataColeta);
				b.setExecutionTime(json.getLong("Execution Time"));
				
				
				listaJobExecutionColeta.add(b);
				
			}
					
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaJobExecutionColeta;
		
	}
	
	private List<BancoJobHistoryColeta> getJobHistory(){
		
		List<BancoJobHistoryColeta> listaJobHistoryColeta= new ArrayList<BancoJobHistoryColeta>();
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.config.jobHistory"));
			JSONArray jsonArray = json.getJSONArray("jobHistory");
			//System.out.println(json.toString());
			
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String id = i.getString("id");
				
				BancoJobHistoryColeta b = sqlServerBO.pegaJobHistoryColetaSQLServer(this.bancoJobHistory, id);
				
				if(b==null){
					b = new BancoJobHistoryColeta(this.bancoJobHistory);
					b.setId(i.getLong(id));
				}
				
				b.setDataColeta(this.dataColeta);
				b.setDuration(json.getLong("Duration"));
				
				
				listaJobHistoryColeta.add(b);
				
			}
					
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaJobHistoryColeta;
		
	}
	
	private BancoPropertiesColeta getStatus(){
		
		BancoPropertiesColeta coleta = null;
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get mssql.status"));
			//System.out.println(json.toString());
			
			coleta = new BancoPropertiesColeta(this.bancoProperties.getBancoProperties());
			
			//pega em bytes
			coleta.setDataColeta(this.dataColeta);
			coleta.setStatus(json.getString("Status"));
					
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return coleta;
	}
*/	
}