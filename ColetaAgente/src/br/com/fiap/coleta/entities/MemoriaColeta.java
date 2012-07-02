package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;


public class MemoriaColeta implements Serializable {
	
	private static final long serialVersionUID = -3869570568840043059L;

	private Long id ;
	
	private Memoria memoria;
	
	private Date dataColeta;

	private Long usado;
	
	public MemoriaColeta(){
		
	}
	
	public MemoriaColeta(Memoria memoria){
		this.memoria = memoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Memoria getMemoria() {
		return memoria;
	}

	public void setMemoria(Memoria memoria) {
		this.memoria = memoria;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Long getUsado() {
		return usado;
	}

	public void setUsado(Long usado) {
		this.usado = usado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataColeta == null) ? 0 : dataColeta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memoria == null) ? 0 : memoria.hashCode());
		result = prime * result + ((usado == null) ? 0 : usado.hashCode());
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
		MemoriaColeta other = (MemoriaColeta) obj;
		if (dataColeta == null) {
			if (other.dataColeta != null)
				return false;
		} else if (!dataColeta.equals(other.dataColeta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memoria == null) {
			if (other.memoria != null)
				return false;
		} else if (!memoria.equals(other.memoria))
			return false;
		if (usado == null) {
			if (other.usado != null)
				return false;
		} else if (!usado.equals(other.usado))
			return false;
		return true;
	}

	
	
	
	
	
}
