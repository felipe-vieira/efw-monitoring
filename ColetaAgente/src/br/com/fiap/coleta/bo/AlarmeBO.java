package br.com.fiap.coleta.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.fiap.coleta.dao.ColetaDAO;
import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreshold;
import br.com.fiap.coleta.entities.ServidorThreshold;
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
	
	
}
