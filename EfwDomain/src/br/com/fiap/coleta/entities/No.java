package br.com.fiap.coleta.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class No {
		
	private Integer id;
	
	private String nome;
	
	private Boolean disponivel;
	
	private Boolean gerenciavel;
	
	private Date ultimaColeta;
	
	private String tipo;
	
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
	
}
