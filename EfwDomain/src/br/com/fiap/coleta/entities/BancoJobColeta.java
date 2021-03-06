	package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BancoJobColeta implements Serializable {

	private static final long serialVersionUID = -1532054018006988702L;
	
	private BancoJob bancoJob;
	private Long id;
	private Long logId;
	private Date dataColeta;
	private Date dataExecucao;
	private Long executionTime;
	private Integer status;
	private String statusDescr;
	private String sqlMsg;
		
	public BancoJobColeta(){
		
	}
	
	public BancoJobColeta(BancoJob bancoJob){
		this.bancoJob = bancoJob;
	}

	public BancoJob getBancoJob() {
		return bancoJob;
	}

	public void setBancoJob(BancoJob bancoJob) {
		this.bancoJob = bancoJob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}
	
	

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDescr() {
		return statusDescr;
	}

	public void setStatusDescr(String statusDescr) {
		this.statusDescr = statusDescr;
	}

	public String getSqlMsg() {
		return sqlMsg;
	}

	public void setSqlMsg(String sqlMsg) {
		this.sqlMsg = sqlMsg;
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
		BancoJobColeta other = (BancoJobColeta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
	
}