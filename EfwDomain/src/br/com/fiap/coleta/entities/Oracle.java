package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class Oracle extends BancoDados implements Serializable{
	
	private static final long serialVersionUID = -4029595958441788034L;
	
	private String InstanceName;

	public String getInstanceName() {
		return InstanceName;
	}

	public void setInstanceName(String instanceName) {
		InstanceName = instanceName;
	}

}
