package br.com.fiap.coleta.entities;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BancoDadosThreshold extends Threshold {
	
	private static final long serialVersionUID = 3429935439447652960L;
	private BigDecimal limiteMemoria;
	private BigDecimal limiteFile;
	private Long limiteTempoBackup;
	private Long limiteTempoJob;
	
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
	public BigDecimal getLimiteFile() {
		return limiteFile;
	}
	public void setLimiteFile(BigDecimal limiteFile) {
		this.limiteFile = limiteFile;
	}
	
	
	
}
