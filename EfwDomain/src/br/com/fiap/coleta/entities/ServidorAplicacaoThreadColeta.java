package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

public class ServidorAplicacaoThreadColeta implements Serializable{

	private static final long serialVersionUID = 7969759077495385310L;

	private ServidorAplicacao servidorAplicacao;
	
	private Long id;
	
	private Date dataColeta;
	
	private Long threadCount;
	
	private Long cpuTime;
	
	private Long userTime;

	public ServidorAplicacao getServidorAplicacao() {
		return servidorAplicacao;
	}

	public void setServidorAplicacao(ServidorAplicacao servidorAplicacao) {
		this.servidorAplicacao = servidorAplicacao;
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

	public Long getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(Long threadCount) {
		this.threadCount = threadCount;
	}

	public Long getCpuTime() {
		return cpuTime;
	}

	public void setCpuTime(Long cpuTime) {
		this.cpuTime = cpuTime;
	}

	public Long getUserTime() {
		return userTime;
	}

	public void setUserTime(Long userTime) {
		this.userTime = userTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpuTime == null) ? 0 : cpuTime.hashCode());
		result = prime * result
				+ ((dataColeta == null) ? 0 : dataColeta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((servidorAplicacao == null) ? 0 : servidorAplicacao
						.hashCode());
		result = prime * result
				+ ((threadCount == null) ? 0 : threadCount.hashCode());
		result = prime * result
				+ ((userTime == null) ? 0 : userTime.hashCode());
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
		ServidorAplicacaoThreadColeta other = (ServidorAplicacaoThreadColeta) obj;
		if (cpuTime == null) {
			if (other.cpuTime != null)
				return false;
		} else if (!cpuTime.equals(other.cpuTime))
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
		if (servidorAplicacao == null) {
			if (other.servidorAplicacao != null)
				return false;
		} else if (!servidorAplicacao.equals(other.servidorAplicacao))
			return false;
		if (threadCount == null) {
			if (other.threadCount != null)
				return false;
		} else if (!threadCount.equals(other.threadCount))
			return false;
		if (userTime == null) {
			if (other.userTime != null)
				return false;
		} else if (!userTime.equals(other.userTime))
			return false;
		return true;
	}
	
	
	
	
}
