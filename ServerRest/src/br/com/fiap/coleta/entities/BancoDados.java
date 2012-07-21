package br.com.fiap.coleta.entities;

import java.io.Serializable;


public class BancoDados extends No implements Serializable{
	
	private static final long serialVersionUID = -4029595958441788034L;
	
	private String hostname;
	
	private Integer port;
	
	private Integer portAgent;
	
	private String usuario;
	
	private String senha;
	
	private Long targetServerMemory;
	
	private String version;
	
	private String edition;
	
	private String status;
	
	private String collation;
	
	

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

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	public Integer getPortAgent() {
		return portAgent;
	}

	public void setPortAgent(Integer portAgent) {
		this.portAgent = portAgent;
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
	
	@Override
	public void coleta() {
		System.out.println("Banco de dados invalido");
	}
	
	
	
	
	
}