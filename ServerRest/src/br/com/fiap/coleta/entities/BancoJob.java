package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class BancoJob implements Serializable {
	
	private static final long serialVersionUID = -1532054018006988702L;
	
	private BancoDados bancoDados;
	private Long id;
	private String owner;
	private String jobName;
	
	public BancoJob(BancoDados bancoDados){
		this.bancoDados = bancoDados;
	}
	
	public BancoJob(){
		
	}
	
	public BancoDados getBancoDados() {
		return bancoDados;
	}
	public void setBancoDados(BancoDados bancoDados) {
		this.bancoDados = bancoDados;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
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
		BancoJob other = (BancoJob) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}