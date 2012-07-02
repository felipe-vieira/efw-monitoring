package br.com.fiap.coleta.bo;

import br.com.fiap.coleta.dao.ServidorAplicacaoDAO;
import br.com.fiap.coleta.entities.ServidorAplicacao;

public class ServidorAplicacaoBO {
	
	
	private ServidorAplicacaoDAO servidorAplicacaoDAO;
	
	public ServidorAplicacaoBO(){
		this.servidorAplicacaoDAO = new ServidorAplicacaoDAO();
	}
	
	public void updateServidorAplicacaoColeta(ServidorAplicacao servidorAplicacao){
		this.servidorAplicacaoDAO.updateServidorAplicacao(servidorAplicacao);
		
	}
	
	
}
