package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class ServidorAplicacaoDeployment implements Serializable {
	
	private static final long serialVersionUID = -6535128039607941215L;

	private ServidorAplicacao servidorAplicacao;
	
	private Long id;
	
	private String nome;
	
	private Boolean ativo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ServidorAplicacao getServidorAplicacao() {
		return servidorAplicacao;
	}
	public void setServidorAplicacao(ServidorAplicacao servidorAplicacao) {
		this.servidorAplicacao = servidorAplicacao;
	}
	
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime
				* result
				+ ((servidorAplicacao == null) ? 0 : servidorAplicacao
						.hashCode());
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
		ServidorAplicacaoDeployment other = (ServidorAplicacaoDeployment) obj;
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
		if (servidorAplicacao == null) {
			if (other.servidorAplicacao != null)
				return false;
		} else if (!servidorAplicacao.equals(other.servidorAplicacao))
			return false;
		return true;
	}
	
	
	
	
}

