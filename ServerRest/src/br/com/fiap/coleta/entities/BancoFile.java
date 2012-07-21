package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class BancoFile implements Serializable {
	
	private static final long serialVersionUID = -1532054018006988702L;
	
	private BancoDados bancoDados;
	private Long id;
	private String file;
	private String filePath;
	private Long maxSize;
	private String growth;
	private String situacao;
	private String fileName;
	private Boolean ativo;
	private String databaseName;
	
	public BancoFile(){
		
	}
	
	public BancoFile(BancoDados bancoDados){
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

	public String getGrowth() {
		return growth;
	}

	public void setGrowth(String growth) {
		this.growth = growth;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
		
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
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
		BancoFile other = (BancoFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}