package br.com.fiap.coleta.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JBoss extends ServidorAplicacao implements Serializable {

	private static final long serialVersionUID = 5637561467657665987L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
