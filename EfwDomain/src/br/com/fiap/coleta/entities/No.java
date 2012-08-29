package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class No implements Serializable{
	
	private static final long serialVersionUID = 407583883725145010L;

	private Integer id;
	
	private String nome;
	
	private String hostname;
	
	private Integer agentPort;
	
	private Boolean disponivel;
	
	private Boolean gerenciavel;
	
	private Boolean ativo;
	
	private Date ultimaColeta;
	
	private String tipo;
	
	private String subTipo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Integer getAgentPort() {
		return agentPort;
	}
	public void setAgentPort(Integer agentPort) {
		this.agentPort = agentPort;
	}
	public Boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
	public Boolean getGerenciavel() {
		return gerenciavel;
	}
	public void setGerenciavel(Boolean gerenciavel) {
		this.gerenciavel = gerenciavel;
	}
	public Date getUltimaColeta() {
		return ultimaColeta;
	}
	public void setUltimaColeta(Date ultimaColeta) {
		this.ultimaColeta = ultimaColeta;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		No other = (No) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getSubTipo() {
		return subTipo;
	}
	public void setSubTipo(String subTipo) {
		this.subTipo = subTipo;
	}
	
}
