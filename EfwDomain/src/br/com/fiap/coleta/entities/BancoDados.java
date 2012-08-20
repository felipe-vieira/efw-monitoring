package br.com.fiap.coleta.entities;

import java.io.Serializable;


public class BancoDados extends No implements Serializable{
	
	private static final long serialVersionUID = -4029595958441788034L;
	
	
	private Integer port;
	
	private String usuario;
	
	private String senha;
	
	private Long targetServerMemory;
	
	private String version;
	
	private String edition;
	
	private String status;
	
	private String collation;
	
	private BancoDadosThreshold threshold;
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Long getTargetServerMemory() {
		return targetServerMemory;
	}

	public void setTargetServerMemory(Long targetServerMemory) {
		this.targetServerMemory = targetServerMemory;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCollation() {
		return collation;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	public BancoDadosThreshold getThreshold() {
		return threshold;
	}

	public void setThreshold(BancoDadosThreshold threshold) {
		this.threshold = threshold;
	}
	
	
	
	
	
	
}