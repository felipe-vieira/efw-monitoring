package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class Particao implements Serializable {

	private static final long serialVersionUID = 5814810736967145200L;

	private Integer id;
	
	private Servidor servidor;
	
	private String nome;
	
	private String sistemaArquivo;
	
	private Long tamanho;
	
	public Particao(){	
	}
	
	public Particao(Servidor servidor){
		this.servidor = servidor;
	}	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSistemaArquivo() {
		return sistemaArquivo;
	}

	public void setSistemaArquivo(String sistemaArquivo) {
		this.sistemaArquivo = sistemaArquivo;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
		result = prime * result
				+ ((sistemaArquivo == null) ? 0 : sistemaArquivo.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
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
		Particao other = (Particao) obj;
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
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		if (sistemaArquivo == null) {
			if (other.sistemaArquivo != null)
				return false;
		} else if (!sistemaArquivo.equals(other.sistemaArquivo))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
