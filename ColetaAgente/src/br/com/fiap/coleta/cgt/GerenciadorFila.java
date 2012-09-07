package br.com.fiap.coleta.cgt;

import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import br.com.fiap.coleta.cgt.threads.AtualizaAgendadorThread;
import br.com.fiap.coleta.dao.AgendamentoDAO;
import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.util.cgt.SchedulerUtil;

public class GerenciadorFila {
	
	private Scheduler scheduler;
	private AgendamentoDAO agendamentoDAO;
	
	public GerenciadorFila(){
		this.agendamentoDAO = new AgendamentoDAO();
	}
	
	public void iniciaGerenciador(){
		//"Zera" o estado de todos os agendamentos
		this.agendamentoDAO.initAgendamentos();
		//Popula o Scheduler 
		this.populaScheduler();
		try{
			//Inicia o agendador
			this.scheduler.start();
			
			//Cria uma thread pra atualizar o scheduler, não da certo com o quartz porquê perde ele serializa os objetos do data map.
			AtualizaAgendadorThread atualiza = new AtualizaAgendadorThread(this.scheduler);
			Thread atualizaThread = new Thread(atualiza);
			atualizaThread.start();
			
		}catch(SchedulerException se){
			se.printStackTrace();
		}
	}
	
	
	public void populaScheduler(){
		try{
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			List<Agendamento> agendamentos = this.agendamentoDAO.listaAgendamentosAtivos();
			
			//Cria o agendamento de sla
			SchedulerUtil.criaAgendamentoSla(scheduler);
			
			for(Agendamento agendamento:agendamentos){	
				SchedulerUtil.adicionarAgendamento(scheduler, agendamento);
				agendamento.setAgendado(true);
				this.agendamentoDAO.updateAgendamento(agendamento);
			}
			
			System.out.println("Agendador populado");
			
		}catch(SchedulerException se){
			se.printStackTrace();
		}
	}
	
}
