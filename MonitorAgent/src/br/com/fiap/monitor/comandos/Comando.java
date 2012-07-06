package br.com.fiap.monitor.comandos;

import org.hyperic.sigar.*;

import br.com.fiap.monitor.coletas.Configuracoes;
import br.com.fiap.monitor.coletas.Glassfish;
import br.com.fiap.monitor.coletas.JBoss;
import br.com.fiap.monitor.coletas.Metricas;
import br.com.fiap.monitor.coletas.Oracle;
import br.com.fiap.monitor.coletas.SqlServer;
import br.com.fiap.monitor.coletas.estrutura.ReturnObject;

public class Comando {
	
	public static String trataComando (String comando){
		
		Metricas metrica = new Metricas();
		Configuracoes config = new Configuracoes();
		JBoss app = new JBoss();
		Glassfish gf = new Glassfish();
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
				}else if(arg[1].equals("mssql.config.version")){
					retorno = new SqlServer().getVersion();
				}else if(arg[1].equals("mssql.drive")){
					retorno = new SqlServer().getDrive();
				}else if(arg[1].equals("mssql.files")){
					retorno = new SqlServer().getFiles();
				}else if(arg[1].equals("mssql.logspace")){
					retorno = new SqlServer().getLogSpace();
				}else if(arg[1].equals("mssql.status")){
					if(arg.length > 2){
						retorno = new SqlServer().getStatus(arg[2]);
					}
				}else if(arg[1].equals("mssql.collation")){
					if(arg.length > 2){
						retorno = new SqlServer().getCollation(arg[2]);
					}
				}else if(arg[1].equals("mssql.backup")){
					retorno = new SqlServer().getBackup();
				}else if(arg[1].equals("mssql.jobRunning")){
					retorno = new SqlServer().getJobRunning();
				}else if(arg[1].equals("mssql.jobHistory")){
					retorno = new SqlServer().getJobHistory();
					
				}else if(arg[1].equals("ora.config.memory")){
					retorno = new Oracle().getConfigMemory();
				}else if(arg[1].equals("ora.memory")){
					retorno = new Oracle().getMemory();
				}else if(arg[1].equals("ora.version")){
					retorno = new Oracle().getVersion();
				}else if(arg[1].equals("ora.status")){
					retorno = new Oracle().getStatus();
				}else if(arg[1].equals("ora.collation")){
					retorno = new Oracle().getCollation();
				}else if(arg[1].equals("ora.drive")){
					retorno = new Oracle().getDrive();
				}else if(arg[1].equals("ora.backup")){
					retorno = new Oracle().getBackup();
				}else if(arg[1].equals("ora.jobRunning")){
					retorno = new Oracle().getJobRunning();
				}else if(arg[1].equals("ora.jobHistory")){
					retorno = new Oracle().getJobHistory();
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
