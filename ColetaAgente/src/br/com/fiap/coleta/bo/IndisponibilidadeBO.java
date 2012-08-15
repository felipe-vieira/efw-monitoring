package br.com.fiap.coleta.bo;

import br.com.fiap.coleta.dao.ColetaDAO;
import br.com.fiap.coleta.dao.IndisponibilidadeDAO;
import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.No;

public class IndisponibilidadeBO {
	
	private IndisponibilidadeDAO indisponibilidadeDAO;
	private ColetaDAO coletaDAO;
	
	public IndisponibilidadeBO(){
		this.indisponibilidadeDAO = new IndisponibilidadeDAO();
		this.coletaDAO = new ColetaDAO();
	}
		
	public Indisponibilidade pegaUltimaInstanciaIndisponibilidade(No no){
		return this.indisponibilidadeDAO.pegaUltimaInstanciaIndisponibilidade(no);	
	}
	
	public void salvaIndisponibilidade(Indisponibilidade ind){
		coletaDAO.salvaColeta(ind);
	}

}
