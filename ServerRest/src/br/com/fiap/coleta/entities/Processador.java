package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class Processador implements Serializable {
	
	private static final long serialVersionUID = -706858618963319345L;

	public Integer id;
	
	public Servidor servidor;
	
	public String fabricante;
	
	public String modelo;
	
	public Integer cores;
	
	public Long clock;
	
	public Processador(){
		
	}
	
	public Processador(Servidor servidor){
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

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getCores() {
		return cores;
	}

	public void setCores(Integer cores) {
		this.cores = cores;
	}

	public Long getClock() {
		return clock;
	}

	public void setClock(Long clock) {
		this.clock = clock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
		result = prime * result + ((clock == null) ? 0 : clock.hashCode());
		result = prime * result + ((cores == null) ? 0 : cores.hashCode());
		result = prime * result
				+ ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
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
		Processador other = (Processador) obj;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		if (clock == null) {
			if (other.clock != null)
				return false;
		} else if (!clock.equals(other.clock))
			return false;
		if (cores == null) {
			if (other.cores != null)
				return false;
		} else if (!cores.equals(other.cores))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		return true;
	}




}
