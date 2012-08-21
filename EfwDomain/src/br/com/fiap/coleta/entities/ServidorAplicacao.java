package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServidorAplicacao extends No implements Serializable{

	private static final long serialVersionUID = 5540687870732500393L;
	
	private Integer port;
	private Integer jmxPort;
	private Date startTime;
	private Long uptime;
	private ServidorAplicacaoThreshold threshold;
	private String tipoServidorAplicacao;
	
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getJmxPort() {
		return jmxPort;
	}
	
	public void setJmxPort(Integer jmxPort) {
		this.jmxPort = jmxPort;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Long getUptime() {
		return uptime;
	}
	public void setUptime(Long uptime) {
		this.uptime = uptime;
	}
	
	public ServidorAplicacaoThreshold getThreshold() {
		return threshold;
	}
	public void setThreshold(ServidorAplicacaoThreshold threshold) {
		this.threshold = threshold;
	}
	
	public String getTipoServidorAplicacao() {
		return tipoServidorAplicacao;
	}
	public void setTipoServidorAplicacao(String tipoServidorAplicacao) {
		this.tipoServidorAplicacao = tipoServidorAplicacao;
	}
		
	
	
	
}
