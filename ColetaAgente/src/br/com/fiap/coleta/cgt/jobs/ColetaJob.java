package br.com.fiap.coleta.cgt.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.fiap.coleta.dao.NoDAO;
import br.com.fiap.coleta.entities.No;

public class ColetaJob implements Job{

	private NoDAO noDAO;
	
	public ColetaJob(){
		this.noDAO = new NoDAO();
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		Integer idNo = context.getJobDetail().getJobDataMap().getInt("no");
		
		No noAtual = this.noDAO.getNoById(idNo);
		noAtual.coleta();
	}

}
