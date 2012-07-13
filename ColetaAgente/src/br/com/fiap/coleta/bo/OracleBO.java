package br.com.fiap.coleta.bo;

import br.com.fiap.coleta.dao.OracleDAO;
import br.com.fiap.coleta.entities.Oracle;

public class OracleBO {
	
	private OracleDAO oracleDAO;
	
	public OracleBO(){
		this.oracleDAO = new OracleDAO();
	}

	public Long pegaUltimoSetBackup(Oracle oracle) {
		return this.oracleDAO.pegaUltimoSetBackup(oracle);		
	}
	
	
	
}
