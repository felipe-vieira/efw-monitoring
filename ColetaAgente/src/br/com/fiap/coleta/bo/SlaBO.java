package br.com.fiap.coleta.bo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.dao.NoDAO;
import br.com.fiap.coleta.dao.SlaDAO;
import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.JanelaSla;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Sla;

public class SlaBO {

	private SlaDAO slaDAO;
		
	
	public SlaBO(){
		this.slaDAO = new SlaDAO();
	}
	
	public void calculaSlaDiario(){
	
		Session session = this.slaDAO.getSession();
		Transaction t = session.beginTransaction();
		
		//Define o periodo do SLA Calculado (D-1)
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		
		dataInicio.set(Calendar.HOUR_OF_DAY, 0);
		dataInicio.set(Calendar.MINUTE, 0);
		dataInicio.set(Calendar.SECOND, 0);
		dataInicio.set(Calendar.DATE, (dataInicio.get(Calendar.DATE) -1) );
		
		dataFim.set(Calendar.HOUR_OF_DAY, 0);
		dataFim.set(Calendar.MINUTE, 0);
		dataFim.set(Calendar.SECOND, 0);
		
		
		try{
			List<Sla> slas = this.slaDAO.listSlaNaoRodados(dataInicio);
			
			for (Sla sla : slas) {
				List<No> nos = this.slaDAO.listNosSla(sla);
				List<JanelaSla> janelas = this.slaDAO.listJanelasSla(sla,dataInicio);
				
				//Gera os calendars pro time
				Calendar horaInicio = Calendar.getInstance();
				Calendar horaFim = Calendar.getInstance();
				
				horaInicio.setTime(sla.getHoraInicio());
				horaFim.setTime(sla.getHoraFim());
				
				
				//Gera as datas com horas para consulta de indisponibilidades
				Calendar dataHoraInicio = Calendar.getInstance();
				Calendar dataHoraFim = Calendar.getInstance();
				
				dataHoraInicio.setTime(dataInicio.getTime());
				dataHoraInicio.set(Calendar.HOUR_OF_DAY, horaInicio.get(Calendar.HOUR_OF_DAY));
				dataHoraInicio.set(Calendar.MINUTE, horaInicio.get(Calendar.MINUTE));
				dataHoraInicio.set(Calendar.SECOND, horaInicio.get(Calendar.SECOND));
					
				dataHoraFim.setTime(dataFim.getTime());
				dataHoraFim.set(Calendar.HOUR_OF_DAY, horaFim.get(Calendar.HOUR_OF_DAY));
				dataHoraFim.set(Calendar.MINUTE, horaFim.get(Calendar.MINUTE));
				dataHoraFim.set(Calendar.SECOND, horaFim.get(Calendar.SECOND));
				dataHoraFim.set(Calendar.DATE, (dataHoraFim.get(Calendar.DATE) -1) );
				
				//Faz o tratamento pra SLAs que começam em um dia e terminam no outro
				if(horaInicio.after(horaFim)){
					dataHoraInicio.set(Calendar.DATE, (dataInicio.get(Calendar.DATE) -1) );
				}
				
				System.out.println(dataHoraInicio.getTime());
				System.out.println(dataHoraFim.getTime());
				
				
				//Calcula o tempo total em ms do sla
				Long tempoTotal = 0l;
				Long tempoIndisponivel = 0l;
				
				if(sla.getHoraFim().after(sla.getHoraInicio())){
					tempoTotal = sla.getHoraFim().getTime() - sla.getHoraInicio().getTime();
				}else{
					tempoTotal = sla.getHoraInicio().getTime() - sla.getHoraFim().getTime();
				}
								
				for(No no: nos){
					List<Indisponibilidade> indisponibilidades = this.pegaIndisponibilidadesNo(no, 
							dataHoraInicio, dataHoraFim);
					
					for (Indisponibilidade indisponibilidade : indisponibilidades) {
						List<JanelaSla> janelasIndisponibilidade = this.listaJanelasIndisponibilidade(indisponibilidade,janelas);
						//tempoIndiponivel += this.calculaTempoIndisponivel(indisponibilidade,janelas);
					}
					
				}
				
			}
			
			t.commit();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
		}
		
	}
	
	
	private List<JanelaSla> listaJanelasIndisponibilidade(
			Indisponibilidade indisponibilidade, List<JanelaSla> janelas) {
		
		List<JanelaSla> janelasIndisponibilidade = new ArrayList<JanelaSla>();
		SimpleDateFormat timeFormat = new SimpleDateFormat("k:m:s");
		
		Long inicio = Time.valueOf(timeFormat.format(indisponibilidade.getInicio())).getTime();
		Long fim = Time.valueOf(timeFormat.format(indisponibilidade.getFim())).getTime();
		
		
		for (JanelaSla janela : janelas) {
			Boolean valida = false;
			
			Long inicioJanela = janela.getHoraInicio().getTime();
			Long fimJanela = janela.getHoraFim().getTime();
			
			if(inicioJanela >= inicio && fimJanela <= fim ){
				valida = true;
			}else if(inicioJanela <= inicio && fimJanela >= fim){
				
				janela.setHoraInicio(indisponibilidade.getInicio());
				janela.setHoraFim(indisponibilidade.getFim());
				
				valida = true;
				
			}else if(inicioJanela <= inicio && fimJanela > inicio){
				
				janela.setHoraInicio(indisponibilidade.getInicio());
				
				if(fimJanela > fim){
					janela.setHoraFim(indisponibilidade.getFim());
				}
				
				valida = true;			
			}
			
			if(valida){
				janelasIndisponibilidade.add(janela);
			}
			
		}
		
		
		return janelasIndisponibilidade;
	}

	public List<Indisponibilidade> pegaIndisponibilidadesNo(No no, Calendar dataInicio, Calendar dataFim){
		
		List<Indisponibilidade> indisponibilidades = this.slaDAO.listaIndisponibilidadesPeriodo(no, 
				dataInicio.getTime(), dataFim.getTime());
		
		for (Indisponibilidade indisponibilidade : indisponibilidades) {
			
			if(indisponibilidade.getInicio().before(dataInicio.getTime())){
				indisponibilidade.setInicio(dataInicio.getTime());
			}
			
			if(indisponibilidade.getFim() == null || indisponibilidade.getFim().after(dataFim.getTime())){
				indisponibilidade.setFim(dataFim.getTime());
			}
			
		}
			
		return indisponibilidades;
		
	}
	
	
}
