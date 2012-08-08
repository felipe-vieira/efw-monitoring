package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class BancoLogSpace implements Serializable {
	
	private static final long serialVersionUID = -1532054018006988702L;
	
	private SQLServer sqlServer;
	private Long id;
	private String dbName;
		
	public BancoLogSpace(){
		
	}
	
	public BancoLogSpace(SQLServer sqlServer){
		this.sqlServer = sqlServer;
	}

	public SQLServer getSqlServer() {
		return sqlServer;
	}

	public void setSqlServer(SQLServer sqlServer) {
		this.sqlServer = sqlServer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dbName == null) ? 0 : dbName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((sqlServer == null) ? 0 : sqlServer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BancoLogSpace other = (BancoLogSpace) obj;
		if (dbName == null) {
			if (other.dbName != null)
				return false;
		} else if (!dbName.equals(other.dbName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sqlServer == null) {
			if (other.sqlServer != null)
				return false;
		} else if (!sqlServer.equals(other.sqlServer))
			return false;
		return true;
	}
	
	
	
}