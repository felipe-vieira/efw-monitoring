package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

public class BancoMemoriaColeta implements Serializable {
	
	private static final long serialVersionUID = -1532054018006988702L;
	
	private BancoDados bancoDados;
	private Long id;
	private Date dataColeta;
	private Long memory;
	private Long stolenMemory;
	
	public BancoMemoriaColeta(BancoDados bancoDados){
		this.bancoDados = bancoDados;
	}
	

	public BancoDados getBancoDados() {
		return bancoDados;
	}
	public void setBancoDados(BancoDados bancoDados) {
		this.bancoDados = bancoDados;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataColeta() {
		return dataColeta;
	}
	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Long getMemory() {
		return memory;
	}


	public void setMemory(Long memory) {
		this.memory = memory;
	}


	public Long getStolenMemory() {
		return stolenMemory;
	}


	public void setStolenMemory(Long stolenMemory) {
		this.stolenMemory = stolenMemory;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BancoMemoriaColeta other = (BancoMemoriaColeta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
	

}
