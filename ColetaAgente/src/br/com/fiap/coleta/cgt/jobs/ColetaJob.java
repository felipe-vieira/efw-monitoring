package br.com.fiap.coleta.cgt.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.fiap.coleta.cgt.coletas.GlassFishColeta;
import br.com.fiap.coleta.cgt.coletas.JBossColeta;
import br.com.fiap.coleta.cgt.coletas.OracleColeta;
import br.com.fiap.coleta.cgt.coletas.SQLServerColeta;
import br.com.fiap.coleta.cgt.coletas.ServidorColeta;
import br.com.fiap.coleta.dao.NoDAO;
import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.coleta.entities.Servidor;

public class ColetaJob implements Job{

	private NoDAO noDAO;
	
	public ColetaJob(){
		this.noDAO = new NoDAO();
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("come�ou uma coleta");
		Integer idNo = context.getJobDetail().getJobDataMap().getInt("no");
		
		No noAtual = this.noDAO.getNoById(idNo);
		
		try{
			if(noAtual instanceof Servidor){
				ServidorColeta coleta = new ServidorColeta(noAtual);
				coleta.initColeta();
			}else if(noAtual instanceof SQLServer){
				SQLServerColeta coleta = new SQLServerColeta(noAtual);
				coleta.initColeta();
			}else if(noAtual instanceof Oracle){
				OracleColeta coleta = new OracleColeta(noAtual);
				coleta.initColeta();
			}else if(noAtual instanceof Glassfish){
				GlassFishColeta coleta = new GlassFishColeta(noAtual);
				coleta.initColeta();
			}else if(noAtual instanceof JBoss){
				JBossColeta coleta =  new JBossColeta(noAtual);
				coleta.initColeta();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
