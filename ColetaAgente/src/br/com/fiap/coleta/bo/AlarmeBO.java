package br.com.fiap.coleta.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.fiap.coleta.dao.ColetaDAO;
import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.BancoDadosThreshold;
import br.com.fiap.coleta.entities.BancoFileColeta;
import br.com.fiap.coleta.entities.BancoJobColeta;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreshold;
import br.com.fiap.coleta.entities.ServidorThreshold;
import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.coleta.entities.TipoAlarme;
import br.com.fiap.coleta.entities.enumerators.CriticidadeAlarme;

public class AlarmeBO {

	private ColetaDAO coletaDAO;
	private Map<Integer,TipoAlarme> tipos;

	
	public AlarmeBO(){
		this.coletaDAO = new ColetaDAO();
		geraMapaAlarmes();
	}
	
	public void geraMapaAlarmes(){
		
		List<TipoAlarme> listaTipos = coletaDAO.listAll(TipoAlarme.class);
		tipos = new HashMap<Integer, TipoAlarme>();
		
		if(listaTipos != null && listaTipos.size() > 0){
			for (TipoAlarme tipoAlarme : listaTipos) {
				tipos.put(tipoAlarme.getId(), tipoAlarme);
			}
		}
					
	}
		
	
	public void geraAlarmeIndsiponibilidade(No no, Boolean ultimoStatus){
		if(!no.getDisponivel() && no.getDisponivel() != ultimoStatus){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(1));
			alarme.setNo(no);
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
			
			coletaDAO.salvaColeta(alarme);
		}
	}
	
	public void geraAlarmeNaoGerenciavel(No no, Boolean ultimoStatus){
		if(!no.getGerenciavel() && ultimoStatus){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(2));
			alarme.setNo(no);			
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
			
			coletaDAO.salvaColeta(alarme);
		}
	}
	
	public void geraAlarmeCpu(Servidor servidor, Double utilizacao) {
		BigDecimal valor = new BigDecimal(utilizacao);
		ServidorThreshold threshold = servidor.getThreshold();
		
		if(threshold != null && threshold.getLimiteCpu() != null){
			if(valor.compareTo(threshold.getLimiteCpu()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(3));
				alarme.setValor(valor);
				alarme.setValorLimite(threshold.getLimiteCpu());
				alarme.setNo(servidor);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				coletaDAO.salvaColeta(alarme);
			}
		}
		
	}
	
	public void geraAlarmeMemoria(Servidor servidor, BigDecimal utilizacao){
		
		ServidorThreshold threshold = servidor.getThreshold();
		
		if(threshold != null && threshold.getLimiteMemoria() != null){
			if(utilizacao.compareTo(servidor.getThreshold().getLimiteMemoria()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(4));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getLimiteMemoria());
				alarme.setNo(servidor);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				coletaDAO.salvaColeta(alarme);
			}
		}
				
	}


	public void geraAlarmeParticao(Servidor servidor, Particao particao, BigDecimal utilizacao){
		
		ServidorThreshold threshold = servidor.getThreshold();
		
		if(threshold != null && threshold.getLimiteParticao() != null){
			if(utilizacao.compareTo(servidor.getThreshold().getLimiteParticao()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(5));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getLimiteParticao());
				alarme.setNo(servidor);
				alarme.setParametro(particao.getNome());
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				coletaDAO.salvaColeta(alarme);
			}			
		}
	}

	public void geraAlarmeMemoriaHeap(ServidorAplicacao sa, BigDecimal utilizacao) {
		ServidorAplicacaoThreshold threshold = sa.getThreshold();
		
		if(threshold != null && threshold.getThresholdHeap() != null){
			if(utilizacao.compareTo(threshold.getThresholdHeap()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(8));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getThresholdHeap());
				alarme.setNo(sa);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				coletaDAO.salvaColeta(alarme);
			}	
		}
		
	}
	
	public void geraAlarmeMemoriaNonHeap(ServidorAplicacao sa, BigDecimal utilizacao) {
		ServidorAplicacaoThreshold threshold = sa.getThreshold();
		
		if(threshold != null && threshold.getThresholdNonHeap() != null){
			if(utilizacao.compareTo(threshold.getThresholdNonHeap()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(9));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getThresholdNonHeap());
				alarme.setNo(sa);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				coletaDAO.salvaColeta(alarme);
			}	
		}
		
	}

	public void geraAlarmesDeploymemnts(ServidorAplicacao sa,
			Map<String, ServidorAplicacaoDeployment> deployments) {
		
		Integer deployCount = 0;
		if(deployments != null){
			Set<String> keys = deployments.keySet();
			for (String key : keys) {
				ServidorAplicacaoDeployment deployment = deployments.get(key);
				if(deployment.getAtivo()){
					deployCount++;
				}else{
					Alarme alarme = new Alarme();
					alarme.setData(new Date());
					alarme.setTipo(tipos.get(7));
					alarme.setNo(sa);
					alarme.setParametro(deployment.getNome());
					alarme.setCriticidade(CriticidadeAlarme.ALERTA);
					
					coletaDAO.salvaColeta(alarme);					
				}
			}
		}
		
		if(deployCount == 0){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(6));
			alarme.setNo(sa);
			alarme.setCriticidade(CriticidadeAlarme.ALERTA);
			
			coletaDAO.salvaColeta(alarme);			
		}
	}

	public void geraAlarmeMemoriaBancoDados(BancoDados bd,
			BigDecimal utilizacao) {
		
		BancoDadosThreshold threshold = bd.getThreshold();
		
		if(threshold != null && threshold.getLimiteMemoria() != null){
			if(utilizacao.compareTo(threshold.getLimiteMemoria()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(13));
				alarme.setNo(bd);
				alarme.setValor(utilizacao);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				coletaDAO.salvaColeta(alarme);
			}
		}
		
	}

	public void geraAlarmeFileBancoDados(BancoDados bd,
			BancoFileColeta coleta) {

		BancoDadosThreshold threshold = bd.getThreshold();
		
		if(threshold != null && threshold.getLimiteFile() != null && coleta.getFile().getMaxSize() != -1){
			BigDecimal utilizacao = new BigDecimal((coleta.getSize().doubleValue()/coleta.getFile().getMaxSize())*100);
			if(utilizacao.compareTo(threshold.getLimiteFile()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(12));
				alarme.setNo(bd);
				alarme.setValor(utilizacao);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				coletaDAO.salvaColeta(alarme);
			}
			
		}
	}

	public void geraAlarmeStatus(BancoDados bd, String ultimoStatusBanco) {
		if(!bd.getStatus().equals("ONLINE") && !bd.getStatus().equals("ultimoStatusBanco") ){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(16));
			alarme.setNo(bd);
			alarme.setParametro(bd.getStatus());
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
		}
	}

	public void geraAlarmesJobs(BancoDados bd, BancoJobColeta coleta,
			String strStatus) {

		if(coleta.getStatus() != 2){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(14));
			alarme.setNo(bd);
			alarme.setParametro(coleta.getBancoJob().getJobName()+";"+strStatus);
			alarme.setCriticidade(CriticidadeAlarme.ALERTA);
		}
		
		if(bd.getThreshold() != null && bd.getThreshold().getLimiteTempoJob() != null){
			if(coleta.getExecutionTime() > bd.getThreshold().getLimiteTempoJob()){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(17));
				alarme.setNo(bd);
				alarme.setParametro(coleta.getBancoJob().getJobName());
				alarme.setValor(new BigDecimal(coleta.getExecutionTime().doubleValue()));
				alarme.setValorLimite(new BigDecimal(bd.getThreshold().getLimiteTempoJob().doubleValue()));
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
			}
		}
	}
	
	public void geraAlarmeUltimoBackup(BancoDados bd, BancoBackup ultimoBackup){
		BancoDadosThreshold threshold = bd.getThreshold();
		
		if(threshold != null && threshold.getLimiteTempoBackup() != null && ultimoBackup != null){
			Long secsUltimo = ultimoBackup.getBackupStartDate().getTime()/1000;
			Long secsNow = new Date().getTime()/1000;
			Long diff = secsNow - secsUltimo;
			
			if(diff > threshold.getLimiteTempoBackup()){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(15));
				alarme.setNo(bd);
				alarme.setValor(new BigDecimal(diff.doubleValue()));
				alarme.setValorLimite(new BigDecimal(threshold.getLimiteTempoBackup().doubleValue()));
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
			}
			
		}
		
	}
	
	public void geraAlarmeSlaDiario(Sla sla, No no, BigDecimal percentual) {

		
			if(percentual.compareTo(sla.getMeta()) == -1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(18));
				alarme.setNo(no);
				alarme.setValor(percentual);
				alarme.setValorLimite(sla.getMeta());
				alarme.setCriticidade(CriticidadeAlarme.CRITICO);
				
				coletaDAO.salvaColeta(alarme);
			}
	}

	public void geraAlarmeSlaMensal(Sla sla, No no, BigDecimal percentual) {
	
		if(percentual.compareTo(sla.getMeta()) == -1){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(19));
			alarme.setNo(no);
			alarme.setValor(percentual);
			alarme.setValorLimite(sla.getMeta());
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
		
			coletaDAO.salvaColeta(alarme);
		}
	
	}
	
}
