package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class Memoria implements Serializable{

	private static final long serialVersionUID = -8799450777766710973L;

	public Long totalMemoria;
	
	public Integer id;
	
	public Servidor servidor;
	
	public Memoria(){
		
	}
	
	public Memoria(Servidor servidor){
		this.servidor = servidor;
	}
	
	public Long getTotalMemoria() {
		return totalMemoria;
	}

	public void setTotalMemoria(Long totalMemoria) {
		this.totalMemoria = totalMemoria;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
		result = prime * result
				+ ((totalMemoria == null) ? 0 : totalMemoria.hashCode());
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
		Memoria other = (Memoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		if (totalMemoria == null) {
			if (other.totalMemoria != null)
				return false;
		} else if (!totalMemoria.equals(other.totalMemoria))
			return false;
		return true;
	}
	
	
	
}

