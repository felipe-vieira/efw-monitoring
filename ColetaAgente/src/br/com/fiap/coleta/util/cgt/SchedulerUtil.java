package br.com.fiap.coleta.util.cgt;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import br.com.fiap.coleta.cgt.jobs.ColetaJob;
import br.com.fiap.coleta.cgt.jobs.SlaJob;
import br.com.fiap.coleta.entities.Agendamento;

public class SchedulerUtil  {
	
	public static void adicionarAgendamento(Scheduler scheduler, Agendamento agendamento) throws SchedulerException{
		//Cria o job
		JobDetail job = JobBuilder.newJob(ColetaJob.class)
				.withIdentity(String.valueOf(agendamento.getId()), String.valueOf(agendamento.getNo().getId()))
				.build();
		
		//Passa o id do nó
		job.getJobDataMap().put("no", agendamento.getNo().getId());		
		
		//Faz o parse da hora de inicio e hora de fim
		String[] horaInicio = agendamento.getHoraInicio().split(":");
		String[] horaFim = agendamento.getHoraFim().split(":");
		
		//Cria a trigger pra chamar o job
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(String.valueOf(agendamento.getId()), String.valueOf(agendamento.getNo().getId()))
				.startAt(DateBuilder.dateOf(Integer.parseInt(horaInicio[0]), Integer.parseInt(horaInicio[1]), 0))
				.endAt(DateBuilder.dateOf(Integer.parseInt(horaFim[0]), Integer.parseInt(horaFim[1]), 0))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInMinutes(agendamento.getIntervalo())
					.repeatForever()
				 )
				.build();					
			

		scheduler.scheduleJob(job, trigger);
	}
	
	public static void criaAgendamentoSla(Scheduler scheduler) throws SchedulerException{
		
		//Cria o job
		JobDetail job = JobBuilder.newJob(SlaJob.class)
				.withIdentity("sla", "sla")
				.build();
		
		
		//Cria a trigger pra chamar o job
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("sla", "sla")
				.startAt(DateBuilder.dateOf(0, 0, 0))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInMinutes(30)
					.repeatForever()
				 )
				.build();					
			

		scheduler.scheduleJob(job, trigger);
	}
	
	public static void removerAgendamento(Scheduler scheduler, String jobKey , String groupKey) throws SchedulerException{
		scheduler.deleteJob(new JobKey(jobKey,groupKey));
	}
	
}
