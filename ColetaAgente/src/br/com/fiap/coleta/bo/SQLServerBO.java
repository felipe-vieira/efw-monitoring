package br.com.fiap.coleta.bo;

import br.com.fiap.coleta.dao.ColetaDAO;
import br.com.fiap.coleta.dao.SQLServerDAO;

public class SQLServerBO {

	private SQLServerDAO sqlServerDAO;
	private ColetaDAO coletaDAO;
	
	public SQLServerBO(){
		this.coletaDAO = new ColetaDAO();
		this.sqlServerDAO = new SQLServerDAO();
	}
}
