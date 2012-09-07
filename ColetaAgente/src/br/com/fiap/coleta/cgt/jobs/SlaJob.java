package br.com.fiap.coleta.cgt.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.fiap.coleta.bo.SlaBO;

public class SlaJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		SlaBO bo = new SlaBO();
		
		System.out.print("Executando sla diário... ");
		bo.calculaSlaDiario();
		System.out.println("OK!");
		
		System.out.print("Executando sla mensal... ");
		bo.calculaSlaMensal();
		System.out.println("OK!");
		
	}

}
