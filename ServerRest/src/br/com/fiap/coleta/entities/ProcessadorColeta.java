package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;


public class ProcessadorColeta implements Serializable{

	private static final long serialVersionUID = 7004800322923695256L;
	
	private Long id;
	private Processador processador;
	private Date dataColeta;
	private Double usado;
	
	public ProcessadorColeta() {
	}
	
	public ProcessadorColeta(Processador processador) {
		this.processador = processador;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Processador getProcessador() {
		return processador;
	}
	public void setProcessador(Processador processador) {
		this.processador = processador;
	}
	public Date getDataColeta() {
		return dataColeta;
	}
	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}
	public Double getUsado() {
		return usado;
	}
	public void setUsado(Double usado) {
		this.usado = usado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataColeta == null) ? 0 : dataColeta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((processador == null) ? 0 : processador.hashCode());
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
		ProcessadorColeta other = (ProcessadorColeta) obj;
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
		if (processador == null) {
			if (other.processador != null)
				return false;
		} else if (!processador.equals(other.processador))
			return false;
		if (usado == null) {
			if (other.usado != null)
				return false;
		} else if (!usado.equals(other.usado))
			return false;
		return true;
	}
	
	
	
	
	
}
