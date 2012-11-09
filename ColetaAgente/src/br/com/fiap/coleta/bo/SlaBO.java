package br.com.fiap.coleta.bo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.dao.SlaDAO;
import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.JanelaSla;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.coleta.entities.SlaCalculado;
import br.com.fiap.coleta.entities.enumerators.TipoSla;

public class SlaBO {

	private SlaDAO slaDAO;
	private AlarmeBO alarmeBO;	
	
	public SlaBO(){
		this.slaDAO = new SlaDAO();
		this.alarmeBO = new AlarmeBO();
	}
	
	public synchronized void calculaSlaDiario(){
	
		//Define o periodo do SLA Calculado (D-1)
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		
		dataInicio.set(Calendar.HOUR_OF_DAY, 0);
		dataInicio.set(Calendar.MINUTE, 0);
		dataInicio.set(Calendar.SECOND, 0);
		dataInicio.add(Calendar.DATE, -1);
		
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
				
				//Calcula o tempo total em ms do sla
				Long tempoTotal = 0l;
				Long tempoJanelas = 0l;
				
				//Calcula o tempo total em ms do sla
				if(sla.getHoraFim().after(sla.getHoraInicio())){
					tempoTotal = sla.getHoraFim().getTime() - sla.getHoraInicio().getTime();
				}else{
					tempoTotal = sla.getHoraInicio().getTime() - sla.getHoraFim().getTime();
				}
				
				//Calcula o tempo das janelas
				for (JanelaSla janela : janelas) {
					if(janela.getHoraFim().after(janela.getHoraInicio())){
						tempoJanelas += janela.getHoraFim().getTime() - janela.getHoraInicio().getTime();
					}else{
						tempoJanelas += janela.getHoraInicio().getTime() - janela.getHoraFim().getTime();
					}
				}
				
						
				for(No no: nos){
					List<Indisponibilidade> indisponibilidades = this.listIndisponibilidadesNo(no, 
							dataHoraInicio, dataHoraFim);
					
					Long tempoIndisponivel = 0l;
					
					if(indisponibilidades!= null && indisponibilidades.size()>0){
						for (Indisponibilidade indisp : indisponibilidades) {
							List<JanelaSla> janelasIndisponibilidade = this.listaJanelasIndisponibilidade(indisp,janelas);
							
							Long tempo = indisp.getFim().getTime() - indisp.getInicio().getTime();
							
							Long tempoExpurgo = 0l;
									
							for (JanelaSla janela : janelasIndisponibilidade) {
								if(janela.getHoraFim().after(janela.getHoraInicio())){
									tempoExpurgo += janela.getHoraFim().getTime() -janela.getHoraInicio().getTime();
								}else{
									tempoExpurgo += janela.getHoraInicio().getTime() - janela.getHoraFim().getTime();
								}
							}
							
							tempoIndisponivel += (tempo - tempoExpurgo);
							
						}
					}
						
					BigDecimal percentual = new BigDecimal(100 - ((tempoIndisponivel.doubleValue()/ (tempoTotal.doubleValue()-tempoJanelas.doubleValue()) ) * 100 ) );
					BigDecimal percentualCalculado = percentual.setScale(2, RoundingMode.HALF_UP);
					
					Session session = this.slaDAO.getSession();
					Transaction t = session.beginTransaction();
					
					try{
						SlaCalculado calculo = new SlaCalculado();
						
						calculo.setControle(dataInicio.getTime());
						calculo.setSla(sla);
						calculo.setNo(no);
						calculo.setTempoTotal(tempoTotal);
						calculo.setTempoJanela(tempoJanelas);
						calculo.setTempoIndisponivel(tempoIndisponivel);
						calculo.setPercentual(percentualCalculado);
						calculo.setTipo(TipoSla.DIARIO);
						
						this.slaDAO.save(calculo);
						this.slaDAO.update(sla);
						
						t.commit();
						
					}catch (Exception ex) {
						ex.printStackTrace();
						t.rollback();
					}
					
					alarmeBO.geraAlarmeSlaDiario(sla, no, percentualCalculado);
				}
				
				//Atualiza a ultima coleta do sla
				Session session = this.slaDAO.getSession();
				Transaction t = session.beginTransaction();
				
				try{
					Date ultimaColeta = new Date();		
					sla.setUltimaColeta(ultimaColeta);
					this.slaDAO.update(sla);
					t.commit();
				}catch (Exception ex) {
					t.rollback();
					ex.printStackTrace();
				}
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public synchronized void calculaSlaMensal(){
			
		//Define o periodo do SLA Calculado (D-1)
		Calendar dataInicio = Calendar.getInstance();
			
		dataInicio.add(Calendar.DATE, -1);
		dataInicio.set(Calendar.HOUR_OF_DAY, 0);
		dataInicio.set(Calendar.MINUTE, 0);
		dataInicio.set(Calendar.SECOND, 0);
		dataInicio.set(Calendar.DATE, 1);
		
		List<Sla> slas = this.slaDAO.listSlaNaoRodadosMes(dataInicio);
		
		dataInicio.set(Calendar.DATE, 1);
		
		Calendar dataFim = Calendar.getInstance();
		
		
		dataFim.set(Calendar.MONTH, dataInicio.get(Calendar.MONTH));
		dataFim.set(Calendar.YEAR, dataInicio.get(Calendar.YEAR));
		dataFim.set(Calendar.HOUR_OF_DAY, 23);
		dataFim.set(Calendar.MINUTE, 59);
		dataFim.set(Calendar.SECOND, 59);
		
		int lastDay = dataFim.getActualMaximum(Calendar.DAY_OF_MONTH);
		dataFim.set(Calendar.DAY_OF_MONTH,lastDay);
		
		for (Sla sla : slas) {
			
			List<No> nos = this.slaDAO.listNosSla(sla);
			
			for (No no : nos) {
				//Pega o valor antigo do sla mensal
				SlaCalculado slaMes = this.slaDAO.getSlaNoMes(no, dataInicio, dataFim);
				
				//Lista todos os slas diários
				List<SlaCalculado> slasDiarios = this.slaDAO.listSlasNoMes(no,dataInicio,dataFim);
				
				Long tempoTotal = 0l;
				Long tempoIndisponivel = 0l;
				Long tempoJanela = 0l;
				
				if(slasDiarios != null && slasDiarios.size() > 0){
				
					for (SlaCalculado slaDiario : slasDiarios) {
						
						tempoTotal += slaDiario.getTempoTotal();
						tempoIndisponivel += slaDiario.getTempoIndisponivel();
						tempoJanela += slaDiario.getTempoJanela();
						
					}
					
					
					
					Session session = this.slaDAO.getSession();
					Transaction t = session.beginTransaction();
					
					try{
						
						if(slaMes == null){
							slaMes = new SlaCalculado();
							slaMes.setSla(sla);
							slaMes.setNo(no);
							slaMes.setControle(dataInicio.getTime());
							slaMes.setTipo(TipoSla.MENSAL);
						}
						
						
						BigDecimal percentual = new BigDecimal(100 - ((tempoIndisponivel.doubleValue()/ (tempoTotal.doubleValue()-tempoJanela.doubleValue()) ) * 100 ) );
						BigDecimal percentualCalculado = percentual.setScale(2, RoundingMode.HALF_UP);
						
						slaMes.setPercentual(percentualCalculado);
						slaMes.setTempoIndisponivel(tempoIndisponivel);
						slaMes.setTempoTotal(tempoTotal);
						slaMes.setTempoJanela(tempoJanela);
						
						this.slaDAO.saveOrUpdate(slaMes);
						t.commit();
						
						this.alarmeBO.geraAlarmeSlaMensal(sla, no, percentualCalculado);
						
					}catch (Exception ex) {
						t.rollback();
						ex.printStackTrace();
					}
				
				}
				
			}
			
			Session session = this.slaDAO.getSession();
			Transaction t = session.beginTransaction();
			
			try{
				Date ultimaColeta = new Date();				
				sla.setUltimaColetaMes(ultimaColeta);
				this.slaDAO.update(sla);
				t.commit();
			}catch (Exception ex) {
				t.rollback();
				ex.printStackTrace();
			}
			
		}
		
	}
	
	
	private synchronized List<JanelaSla> listaJanelasIndisponibilidade(
			Indisponibilidade indisponibilidade, List<JanelaSla> janelas) {
		
		List<JanelaSla> janelasIndisponibilidade = new ArrayList<JanelaSla>();
		SimpleDateFormat timeFormat = new SimpleDateFormat("k:m:s");
		
		Date inicioTime = Time.valueOf(timeFormat.format(indisponibilidade.getInicio()));
		Date fimTime = Time.valueOf(timeFormat.format(indisponibilidade.getFim())); 
		
		Long inicio = inicioTime.getTime();
		Long fim = fimTime.getTime();
		
		for (JanelaSla janela : janelas) {
			Boolean valida = false;
			
			Long inicioJanela = janela.getHoraInicio().getTime();
			Long fimJanela = janela.getHoraFim().getTime();
			
			if(inicioJanela >= inicio && fimJanela <= fim ){
				valida = true;
				
			}else if(inicioJanela <= inicio && fimJanela >= fim){
				
				janela.setHoraInicio(inicioTime);
				janela.setHoraFim(fimTime);
				
				valida = true;
				
			}else if(inicioJanela <= inicio && fimJanela > inicio){
				
				janela.setHoraInicio(inicioTime);
				
				if(fimJanela > fim){
					janela.setHoraFim(fimTime);
				}
				
				valida = true;			
			}
			
			if(valida){
				janelasIndisponibilidade.add(janela);
			}
			
		}
		
		
		return janelasIndisponibilidade;
	}

	private synchronized List<Indisponibilidade> listIndisponibilidadesNo(No no, Calendar dataInicio, Calendar dataFim){
		
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
