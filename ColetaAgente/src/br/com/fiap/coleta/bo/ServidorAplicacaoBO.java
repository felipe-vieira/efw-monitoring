package br.com.fiap.coleta.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.coleta.dao.ColetaDAO;
import br.com.fiap.coleta.dao.ServidorAplicacaoDAO;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoriaColeta;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreadColeta;
import br.com.fiap.coleta.entities.enumerators.TipoMemoriaServidorAplicacao;

public class ServidorAplicacaoBO {
	
	
	private ServidorAplicacaoDAO servidorAplicacaoDAO;
	private ColetaDAO coletaDAO;
	
	public ServidorAplicacaoBO(){
		this.servidorAplicacaoDAO = new ServidorAplicacaoDAO();
		this.coletaDAO = new ColetaDAO();
		
	}
	
	public void updateServidorAplicacaoColeta(ServidorAplicacao servidorAplicacao){
		if(servidorAplicacao != null){
			this.coletaDAO.updateColeta(servidorAplicacao);
		}		
	}

	public void salvaPropriedadesMemoria(List<ServidorAplicacaoMemoria> memorias) {
		if(memorias != null){
			this.coletaDAO.salvaListaColetas(memorias);
		}
	}
	

	public void salvaColetasMemoria(List<ServidorAplicacaoMemoriaColeta> coletasMemorias) {
		if(coletasMemorias != null){
			this.coletaDAO.salvaListaColetas(coletasMemorias);
		}
	}
	
	public void salvaColetasThread(ServidorAplicacaoThreadColeta coleta){
		if(coleta != null){
			this.coletaDAO.salvaColeta(coleta);
		}
	}
	
	public void salvaMapDeployments(Map<String, ServidorAplicacaoDeployment> map){
		if(map != null){
			this.coletaDAO.salvaMapColeta(map);
		}
	}
	
	public ServidorAplicacaoMemoria getMemoriaTipo(ServidorAplicacao servidor, TipoMemoriaServidorAplicacao tipo){
		return this.servidorAplicacaoDAO.getMemoriaTipo(servidor,tipo);
	}
	
	public Map<String, ServidorAplicacaoDeployment> getMapDeploymentsServidor(ServidorAplicacao servidor){
		List<ServidorAplicacaoDeployment> deployments = this.servidorAplicacaoDAO.getDeploymentsServidor(servidor);
		Map<String,ServidorAplicacaoDeployment> map = null;
		
		if(deployments != null && deployments.size() > 0){
			map = new HashMap<String, ServidorAplicacaoDeployment>();
			for (ServidorAplicacaoDeployment deployment : deployments) {
				deployment.setAtivo(false);
				map.put(deployment.getNome(), deployment);
			}
		}
		
		
	
		return map;
	}
	
	
	
}
