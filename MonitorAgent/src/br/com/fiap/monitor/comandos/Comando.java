package br.com.fiap.monitor.comandos;

import org.hyperic.sigar.*;

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
		JBoss app = new JBoss();
		Glassfish gf = new Glassfish();

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
					retorno = new SqlServer().getMemory();
				}else if(arg[1].equals("mssql.config.memory")){
					retorno = new SqlServer().getConfigMemory();
				}else if(arg[1].equals("mssql.config.version")){
					retorno = new SqlServer().getConfigVersion();
				}else if(arg[1].equals("mssql.config.drive")){
					retorno = new SqlServer().getConfigDrive();
				}else if(arg[1].equals("mssql.config.files")){
					retorno = new SqlServer().getConfigFiles();
				}else if(arg[1].equals("mssql.config.collation")){
					if(arg.length > 2){
						retorno = new SqlServer().getConfigCollation(arg[2]);
					}
				}else if(arg[1].equals("mssql.logspace")){
					retorno = new SqlServer().getLogSpace();
				}else if(arg[1].equals("mssql.status")){
					if(arg.length > 2){
						retorno = new SqlServer().getStatus(arg[2]);
					}
				}else if(arg[1].equals("mssql.config.collation")){
					if(arg.length > 2){
						retorno = new SqlServer().getConfigCollation(arg[2]);
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
					retorno = new SqlServer().getBackup(setCount);
				}else if(arg[1].equals("mssql.config.jobs")){
					retorno = new SqlServer().getConfigJobs();
				}else if(arg[1].equals("mssql.jobHistory")){
					retorno = new SqlServer().getJobHistory();
				}else if(arg[1].equals("mssql.status")){
					if(arg.length > 2){
						retorno = new SqlServer().getConfigCollation(arg[2]);
					}
				}else if(arg[1].equals("ora.config.memory")){
					retorno = new Oracle().getConfigMemory();
				}else if(arg[1].equals("ora.memory")){
					retorno = new Oracle().getMemory();
				}else if(arg[1].equals("ora.config.version")){
					retorno = new Oracle().getConfigVersion();
				}else if(arg[1].equals("ora.status")){
					retorno = new Oracle().getStatus();
				}else if(arg[1].equals("ora.config.collation")){
					retorno = new Oracle().getConfigCollation();
				}else if(arg[1].equals("ora.config.files")){
					retorno = new Oracle().getConfigFiles();
				}else if(arg[1].equals("ora.config.backup")){
					Long setCount = 0l;
					
					if(arg.length >= 3 && arg[2] != null){
						try{
							setCount = Long.parseLong(arg[2]);
						}catch(NumberFormatException ex){
							setCount = 0l;
						}
					}
					
					retorno = new Oracle().getConfigBackup(setCount);
					
				}else if(arg[1].equals("ora.config.jobHistory")){
					retorno = new Oracle().getConfigJobHistory();
				}else if(arg[1].equals("jboss.status")){
					retorno = app.getStatus();
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
