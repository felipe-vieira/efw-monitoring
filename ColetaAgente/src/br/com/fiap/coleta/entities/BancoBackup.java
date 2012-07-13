package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

public class BancoBackup implements Serializable {
	
	private static final long serialVersionUID = -1532054018006988702L;
	
	private BancoDados bancoDados;
	private Long id;
	private String fileName;
	private Date backupStartDate;
	private Long tempoExecucao;
	private Long tamanho;
	private String recoveryModel;
	private Long setCount;
	
	public BancoBackup(){
		
	}
	
	public BancoBackup(BancoDados bancoDados){
		this.bancoDados = bancoDados;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getBackupStartDate() {
		return backupStartDate;
	}
	public void setBackupStartDate(Date backupStartDate) {
		this.backupStartDate = backupStartDate;
	}
	public String getRecoveryModel() {
		return recoveryModel;
	}
	public void setRecoveryModel(String recoveryModel) {
		this.recoveryModel = recoveryModel;
	}
	public Long getSetCount() {
		return setCount;
	}
	public void setSetCount(Long setCount) {
		this.setCount = setCount;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Long getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(Long tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
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
		BancoBackup other = (BancoBackup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
