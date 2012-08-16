package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

public class ServidorAplicacao extends No implements Serializable{

	private static final long serialVersionUID = 5540687870732500393L;
	
	private Servidor servidor;
	private String hostname;	
	private Integer port;
	private Integer jmxPort;
	private Date startTime;
	private Long uptime;
	private ServidorAplicacaoThreshold threshold;
	
	public Servidor getServidor() {
		return servidor;
	}
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((jmxPort == null) ? 0 : jmxPort.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((uptime == null) ? 0 : uptime.hashCode());
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
		ServidorAplicacao other = (ServidorAplicacao) obj;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (jmxPort == null) {
			if (other.jmxPort != null)
				return false;
		} else if (!jmxPort.equals(other.jmxPort))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (uptime == null) {
			if (other.uptime != null)
				return false;
		} else if (!uptime.equals(other.uptime))
			return false;
		return true;
	}
	
	public void coleta(){
		System.out.println("Servidor de aplicação invalido, usar Glassfish ou JBoss");
	};
	
	
		
	
	
	
}
