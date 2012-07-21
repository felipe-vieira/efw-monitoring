package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;


public class ServidorAplicacaoMemoriaColeta implements Serializable{

	private static final long serialVersionUID = -1532054018006988702L;
	
	private ServidorAplicacaoMemoria memoria;
	private Long id;
	private Date dataColeta;
	private Long used;
	private Long commited;
	
	
	public ServidorAplicacaoMemoriaColeta(){
		
	}
	
	public ServidorAplicacaoMemoriaColeta(ServidorAplicacaoMemoria memoria) {
		this.memoria = memoria;
	}
	public ServidorAplicacaoMemoria getMemoria() {
		return memoria;
	}
	public void setMemoria(ServidorAplicacaoMemoria memoria) {
		this.memoria = memoria;
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
	public Long getUsed() {
		return used;
	}
	public void setUsed(Long used) {
		this.used = used;
	}
	public Long getCommited() {
		return commited;
	}
	public void setCommited(Long commited) {
		this.commited = commited;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commited == null) ? 0 : commited.hashCode());
		result = prime * result
				+ ((dataColeta == null) ? 0 : dataColeta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memoria == null) ? 0 : memoria.hashCode());
		result = prime * result + ((used == null) ? 0 : used.hashCode());
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
		ServidorAplicacaoMemoriaColeta other = (ServidorAplicacaoMemoriaColeta) obj;
		if (commited == null) {
			if (other.commited != null)
				return false;
		} else if (!commited.equals(other.commited))
			return false;
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
		if (used == null) {
			if (other.used != null)
				return false;
		} else if (!used.equals(other.used))
			return false;
		return true;
	}
	
	
}
