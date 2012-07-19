package br.com.fiap.coleta.entities;

import java.io.Serializable;

import br.com.fiap.coleta.cgt.coletas.SQLServerColeta;

public class SQLServer extends BancoDados implements Serializable{
	
	private static final long serialVersionUID = -4029595958441788034L;
	
	private String instanceName;
	private String database;

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	@Override
	public void coleta() {
		SQLServerColeta coleta = new SQLServerColeta(this);
		coleta.initColeta();
	}
	
}
