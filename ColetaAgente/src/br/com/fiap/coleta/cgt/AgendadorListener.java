package br.com.fiap.coleta.cgt;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import br.com.fiap.coleta.bo.AgendamentoBO;
import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.util.cgt.SchedulerUtil;

public class AgendadorListener implements SchedulerListener{

	
	private AgendamentoBO agendamentoBO;
	private Scheduler scheduler;
	
	public AgendadorListener(){
	
		try {
			this.scheduler = StdSchedulerFactory.getDefaultScheduler();
			this.agendamentoBO = new AgendamentoBO();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void jobAdded(JobDetail arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobDeleted(JobKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobPaused(JobKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobResumed(JobKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobScheduled(Trigger arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobUnscheduled(TriggerKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobsPaused(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobsResumed(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerError(String arg0, SchedulerException arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerInStandbyMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerShuttingdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulingDataCleared() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		System.out.println("Trigger finalizada... reagendando");
		Integer idAgendamento = (Integer) trigger.getJobDataMap().get("agendamento");
		
		if(idAgendamento != null){
			
			try {
				Agendamento agendamento = this.agendamentoBO.pegaAgendamento(idAgendamento);
				SchedulerUtil.adicionarAgendamento(scheduler, agendamento);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void triggerPaused(TriggerKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerResumed(TriggerKey arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggersPaused(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggersResumed(String arg0) {
		// TODO Auto-generated method stub
		
	}
	
}