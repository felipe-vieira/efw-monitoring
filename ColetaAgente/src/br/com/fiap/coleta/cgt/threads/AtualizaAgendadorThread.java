package br.com.fiap.coleta.cgt.threads;

import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import br.com.fiap.coleta.dao.AgendamentoDAO;
import br.com.fiap.coleta.dao.AgendamentoRemovidoDAO;
import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.entities.AgendamentoRemovido;
import br.com.fiap.coleta.util.cgt.SchedulerUtil;

public class AtualizaAgendadorThread implements Runnable{

	
	private Scheduler scheduler;
	
	private AgendamentoDAO agendamentoDAO; 
	private AgendamentoRemovidoDAO agendamentoRemovidoDAO;
	
	
	public AtualizaAgendadorThread(Scheduler scheduler){
		this.scheduler = scheduler;
		this.agendamentoDAO = new AgendamentoDAO();
		this.agendamentoRemovidoDAO = new AgendamentoRemovidoDAO();
	};
	
	
	@Override
	public void run() {
		
		while(true){
			
			//Remove agendamentos marcados para exclusão.
			List<AgendamentoRemovido> listaRemovido = this.agendamentoRemovidoDAO.listaAgendamentosRemovidos();
			for (AgendamentoRemovido agendamentoRemovido : listaRemovido) {
				Agendamento agendamentoUpdate = this.agendamentoRemovidoDAO.pegaAgendamentoAgendamentoRemovido(agendamentoRemovido.getId());
				
				String job = String.valueOf(agendamentoUpdate.getId());
				String group = this.agendamentoRemovidoDAO.pegaGrupoAgendamentoRemovido(agendamentoRemovido.getId());
				
				try{
					SchedulerUtil.removerAgendamento(scheduler, job, group);
					
					//Indica que o agendamento foi desativado
					agendamentoUpdate.setAtivo(false);
					this.agendamentoDAO.updateAgendamento(agendamentoUpdate);
					
					//Indica que o processo de remoção foi executado
					agendamentoRemovido.setStatus(true);
					this.agendamentoRemovidoDAO.updateAgendamentoRemovido(agendamentoRemovido);
					
					System.out.println("removeu");
				}catch(SchedulerException se){
					se.printStackTrace();
				}
				
			}
			
			//Pega novos agendamentos...
			List<Agendamento> listaNovos = this.agendamentoDAO.listaAgendamentosNovos();
			
			for (Agendamento agendamento : listaNovos) {
				try{
					SchedulerUtil.adicionarAgendamento(this.scheduler, agendamento);
					agendamento.setAgendado(true);
					this.agendamentoDAO.updateAgendamento(agendamento);
				}catch(SchedulerException se){
					se.printStackTrace();
				}
			}
			
			try{
				//Roda a cada um minuto
				Thread.sleep(60000);
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
		
	}

}
