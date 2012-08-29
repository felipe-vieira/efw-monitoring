package br.com.fiap.coleta.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Glassfish extends ServidorAplicacao implements Serializable{

	private static final long serialVersionUID = -4029595958441788034L;
	
	private String jmxUser;
	
	private String jmxSenha;

	public String getJmxUser() {
		return jmxUser;
	}

	public void setJmxUser(String jmxUser) {
		this.jmxUser = jmxUser;
	}

	public String getJmxSenha() {
		return jmxSenha;
	}

	public void setJmxSenha(String jmxSenha) {
		this.jmxSenha = jmxSenha;
	}
	


	
	
}