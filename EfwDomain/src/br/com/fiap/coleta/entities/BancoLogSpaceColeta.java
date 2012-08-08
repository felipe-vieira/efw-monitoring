package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

public class BancoLogSpaceColeta implements Serializable {
	
	private static final long serialVersionUID = -1532054018006988702L;
	
	private BancoLogSpace bancoLogSpace;
	private Long id;
	private Date dataColeta;
	private Long logSize;
	private Long logSpaceUsed;
		
	public BancoLogSpaceColeta(){
		
	}
	
	public BancoLogSpaceColeta(BancoLogSpace bancoLogSpace){
		this.bancoLogSpace = bancoLogSpace;
	}

	public BancoLogSpace getBancoLogSpace() {
		return bancoLogSpace;
	}

	public void setBancoLogSpace(BancoLogSpace bancoLogSpace) {
		this.bancoLogSpace = bancoLogSpace;
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

	public Long getLogSize() {
		return logSize;
	}

	public void setLogSize(Long logSize) {
		this.logSize = logSize;
	}

	public Long getLogSpaceUsed() {
		return logSpaceUsed;
	}

	public void setLogSpaceUsed(Long logSpaceUsed) {
		this.logSpaceUsed = logSpaceUsed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bancoLogSpace == null) ? 0 : bancoLogSpace.hashCode());
		result = prime * result
				+ ((dataColeta == null) ? 0 : dataColeta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logSize == null) ? 0 : logSize.hashCode());
		result = prime * result
				+ ((logSpaceUsed == null) ? 0 : logSpaceUsed.hashCode());
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
		BancoLogSpaceColeta other = (BancoLogSpaceColeta) obj;
		if (bancoLogSpace == null) {
			if (other.bancoLogSpace != null)
				return false;
		} else if (!bancoLogSpace.equals(other.bancoLogSpace))
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
		if (logSize == null) {
			if (other.logSize != null)
				return false;
		} else if (!logSize.equals(other.logSize))
			return false;
		if (logSpaceUsed == null) {
			if (other.logSpaceUsed != null)
				return false;
		} else if (!logSpaceUsed.equals(other.logSpaceUsed))
			return false;
		return true;
	}
	
	
	
}