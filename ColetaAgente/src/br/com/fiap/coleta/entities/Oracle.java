package br.com.fiap.coleta.entities;

import java.io.Serializable;

import br.com.fiap.coleta.cgt.coletas.OracleColeta;

public class Oracle extends BancoDados implements Serializable{
	
	private static final long serialVersionUID = -4029595958441788034L;
	
	private String InstanceName;
	
	private String File;
	
	public String getFile() {
		return File;
	}

	public void setFile(String file) {
		File = file;
	}

	public String getInstanceName() {
		return InstanceName;
	}

	public void setInstanceName(String instanceName) {
		InstanceName = instanceName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((File == null) ? 0 : File.hashCode());
		result = prime * result
				+ ((InstanceName == null) ? 0 : InstanceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oracle other = (Oracle) obj;
		if (File == null) {
			if (other.File != null)
				return false;
		} else if (!File.equals(other.File))
			return false;
		if (InstanceName == null) {
			if (other.InstanceName != null)
				return false;
		} else if (!InstanceName.equals(other.InstanceName))
			return false;
		return true;
	}

	@Override
	public void coleta(){
		OracleColeta coleta = new OracleColeta(this);
		coleta.initColeta();
	}
	
	
	

}
