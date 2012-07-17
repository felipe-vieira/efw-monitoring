package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

public class BancoFileColeta implements Serializable {
	
	private static final long serialVersionUID = -1532054018006988702L;
	
	private BancoFile file;
	private Long id;
	private Date dataColeta;
	private Long size;
	
	public BancoFileColeta(){
		
	}
	
	public BancoFileColeta(BancoFile file){
		this.file = file;
	}
	
	public BancoFile getFile() {
		return file;
	}
	public void setFile(BancoFile file) {
		this.file = file;
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
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
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
		BancoFileColeta other = (BancoFileColeta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}