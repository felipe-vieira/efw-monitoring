package br.com.fiap.coleta.entities;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BancoDadosThreshold extends Threshold {
	
	private static final long serialVersionUID = 3429935439447652960L;
	private Integer id;
	private String nome;
	private BigDecimal limiteMemoria;
	private BigDecimal limiteFile;
	private Long limiteTempoBackup;
	private Long limiteTempoJob;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getLimiteMemoria() {
		return limiteMemoria;
	}
	public void setLimiteMemoria(BigDecimal limiteMemoria) {
		this.limiteMemoria = limiteMemoria;
	}
	public Long getLimiteTempoBackup() {
		return limiteTempoBackup;
	}
	public void setLimiteTempoBackup(Long limiteTempoBackup) {
		this.limiteTempoBackup = limiteTempoBackup;
	}
	public Long getLimiteTempoJob() {
		return limiteTempoJob;
	}
	public void setLimiteTempoJob(Long limiteTempoJob) {
		this.limiteTempoJob = limiteTempoJob;
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
		BancoDadosThreshold other = (BancoDadosThreshold) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public BigDecimal getLimiteFile() {
		return limiteFile;
	}
	public void setLimiteFile(BigDecimal limiteFile) {
		this.limiteFile = limiteFile;
	}
	
	
	
}
