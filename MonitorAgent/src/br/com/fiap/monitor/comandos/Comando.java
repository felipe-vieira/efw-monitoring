package br.com.fiap.monitor.comandos;

import org.hyperic.sigar.SigarException;

import br.com.fiap.monitor.coletas.Configuracoes;
import br.com.fiap.monitor.coletas.Glassfish;
import br.com.fiap.monitor.coletas.JBoss;
import br.com.fiap.monitor.coletas.Metricas;
import br.com.fiap.monitor.coletas.Oracle;
import br.com.fiap.monitor.coletas.SqlServer;
import br.com.fiap.monitor.coletas.estrutura.ReturnObject;
import br.com.fiap.monitor.utils.Log;

public class Comando {
	
	public static String trataComando (String comando){
		
		Metricas metrica = new Metricas();
		Configuracoes config = new Configuracoes();
		JBoss app = JBoss.getInstance();
		Glassfish gf = Glassfish.getInstance();
		SqlServer sql = SqlServer.getInstance();
		Oracle oracle = Oracle.getInstance();
		
		Log log = new Log();

		String arg[] = comando.split(" ");
		ReturnObject retorno = null;
		
		try{
			if(arg[0].equals("get")){
				if(arg[1].equals("config.os")){
					retorno = config.getConfigOs();
				}else if(arg[1].equals("config.processor")){
					retorno = config.getConfigProcessor();
				}else if(arg[1].equals("config.memory")){
					retorno = config.getConfigMemory();
				}else if(arg[1].equals("config.partitions")){
					retorno = config.getConfigPartitions();
				}else if(arg[1].equals("os.memory")){
					retorno = metrica.getOsMemory();
				}else if(arg[1].equals("os.processor")){
					retorno = metrica.getProcessor();
				}else if(arg[1].equals("os.partition")){
					if(arg.length > 2){
						retorno = metrica.getPatitionInfo(arg[2]);
					}
				}else if(arg[1].equals("jboss.port")){
					if(arg.length > 2){
						app.setPort(Integer.parseInt(arg[2]));
					}
				}else if(arg[1].equals("jboss.memory")){
					retorno = app.getMemory();
				}else if(arg[1].equals("jboss.thread")){
					retorno = app.getThread();
				}else if(arg[1].equals("jboss.runtime")){
					retorno = app.getRuntime();
				}else if(arg[1].equals("jboss.status")){
					retorno = app.getStatus();
				}else if(arg[1].equals("jboss.deployment")){
					retorno = app.getDeployments();
				}else if(arg[1].equals("mssql.memory")){
					//retorno = new SqlServer().getMemory();
					sql.connect();
					retorno = sql.getMemory();
					sql.disconnect();
				}else if(arg[1].equals("mssql.config.memory")){
					//retorno = new SqlServer().getConfigMemory();
					sql.connect();
					retorno = sql.getConfigMemory();
					sql.disconnect();
				}else if(arg[1].equals("mssql.config.version")){
					//retorno = new SqlServer().getConfigVersion();
					sql.connect();
					retorno = sql.getConfigVersion();
					sql.disconnect();
				}else if(arg[1].equals("mssql.config.drive")){
					//retorno = new SqlServer().getConfigDrive();
					sql.connect();
					retorno = sql.getConfigDrive();
					sql.disconnect();
				}else if(arg[1].equals("mssql.config.files")){
					//retorno = new SqlServer().getConfigFiles();
					sql.connect();
					retorno = sql.getConfigFiles();
					sql.disconnect();
				}else if(arg[1].equals("mssql.config.collation")){
					if(arg.length > 2){
						//retorno = new SqlServer().getConfigCollation(arg[2]);
						sql.connect();
						retorno = sql.getConfigCollation(arg[2]);
						sql.disconnect();
					}
				}else if(arg[1].equals("mssql.logspace")){
					//retorno = new SqlServer().getLogSpace();
					sql.connect();
					retorno = sql.getLogSpace();
					sql.disconnect();
				}else if(arg[1].equals("mssql.status")){
					if(arg.length > 2){
						//retorno = new SqlServer().getStatus(arg[2]);
						sql.connect();
						retorno = sql.getStatus(arg[2]);
						sql.disconnect();
					}
				}else if(arg[1].equals("mssql.config.collation")){
					if(arg.length > 2){
						//retorno = new SqlServer().getConfigCollation(arg[2]);
						sql.connect();
						retorno = sql.getConfigCollation(arg[2]);
						sql.disconnect();
					}
				}else if(arg[1].equals("mssql.config.backup")){
					Long setCount = 0l;
					
					if(arg.length >= 3 && arg[2] != null){
						try{
							setCount = Long.parseLong(arg[2]);
						}catch(NumberFormatException ex){
							setCount = 0l;
						}
					}
					//retorno = new SqlServer().getBackup(setCount);
					sql.connect();
					retorno = sql.getBackup(setCount);
					sql.disconnect();
				}else if(arg[1].equals("mssql.config.jobs")){
					//retorno = new SqlServer().getConfigJobs();
					sql.connect();
					retorno = sql.getConfigJobs();
					sql.disconnect();
				}else if(arg[1].equals("mssql.jobHistory")){
					//retorno = new SqlServer().getJobHistory();
					sql.connect();
					retorno = sql.getJobHistory();
					sql.disconnect();
				}else if(arg[1].equals("mssql.status")){
					if(arg.length > 2){
						//retorno = new SqlServer().getConfigCollation(arg[2]);
						sql.connect();
						retorno = sql.getConfigCollation(arg[2]);
						sql.disconnect();
					}
				}else if(arg[1].equals("ora.config.memory")){
					//retorno = new Oracle().getConfigMemory();
					oracle.connect();
					retorno = oracle.getConfigMemory();
					oracle.disconnect();
				}else if(arg[1].equals("ora.memory")){
					//retorno = new Oracle().getMemory();
					oracle.connect();
					retorno = oracle.getMemory();
					oracle.disconnect();
				}else if(arg[1].equals("ora.config.version")){
					//retorno = new Oracle().getConfigVersion();
					oracle.connect();
					retorno = oracle.getConfigVersion();
					oracle.disconnect();
				}else if(arg[1].equals("ora.status")){
					//retorno = new Oracle().getStatus();
					oracle.connect();
					retorno = oracle.getStatus();
					oracle.disconnect();
				}else if(arg[1].equals("ora.config.collation")){
					//retorno = new Oracle().getConfigCollation();
					oracle.connect();
					retorno = oracle.getConfigCollation();
					oracle.disconnect();
				}else if(arg[1].equals("ora.config.files")){
					//retorno = new Oracle().getConfigFiles();
					oracle.connect();
					retorno = oracle.getConfigFiles();
					oracle.disconnect();
				}else if(arg[1].equals("ora.config.backup")){
					Long setCount = 0l;
					
					if(arg.length >= 3 && arg[2] != null){
						try{
							setCount = Long.parseLong(arg[2]);
						}catch(NumberFormatException ex){
							setCount = 0l;
						}
					}
					
					//retorno = new Oracle().getConfigBackup(setCount);
					oracle.connect();
					retorno = oracle.getConfigBackup(setCount);
					oracle.disconnect();
					
				}else if(arg[1].equals("ora.config.jobHistory")){
					//retorno = new Oracle().getConfigJobHistory();
					oracle.connect();
					retorno = oracle.getConfigJobHistory();
					oracle.disconnect();
				}else if(arg[1].equals("jboss.status")){
					retorno = app.getStatus();
				}else if(arg[1].equals("glassfish.credentials")){
					
					try {
						if(arg.length == 5){
							System.out.println("ok");
							gf.setCredentials(arg[2], arg[3], Integer.parseInt(arg[4]));
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}else if(arg[1].equals("glassfish.memory")){
					retorno = gf.getMemory();				
				}else if(arg[1].equals("glassfish.runtime")){
					retorno = gf.getRuntime();				
				}else if(arg[1].equals("glassfish.thread")){
					retorno = gf.getThread();
				}else if(arg[1].equals("glassfish.deployment")){
					retorno = gf.getDeployments();
				}else if(arg[1].equals("log")){
					try {
						if(arg.length == 4){
							log.getLinhas(arg[2], System.out, Integer.parseInt(arg[3]));		
						}
						else if(arg.length == 5){
							log.getLinhas(arg[2], System.out, Integer.parseInt(arg[3]), arg[4]);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}		
			}else if(arg[0].equals("set")){
				if(arg[1].equals("ora.credentials")){
				
				try {
					if(arg.length == 7){
						oracle.setCredentials(arg[2], arg[3], arg[4], Integer.parseInt(arg[5]), arg[6]);
					}
				} catch (Exception e){
					e.printStackTrace();
				}
				}else if(arg[1].equals("mssql.credentials")){
					
					try {
						if(arg.length == 7){
							sql.setCredentials(arg[2], arg[3], arg[4], Integer.parseInt(arg[5]), arg[6]);
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}else if(arg[0].equals("list")){
				return "get config.os / config.processor / config.memory / config.partitions / os.memory / os.processor / os.partition";
			}
		}catch(SigarException ex){
			ex.printStackTrace();
		}
		
		if(retorno != null){
			return retorno.toJSON();
		}else{
			return null;
		}
				
	}
}
