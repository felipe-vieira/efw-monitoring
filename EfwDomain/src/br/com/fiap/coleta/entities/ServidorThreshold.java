package br.com.fiap.coleta.entities;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServidorThreshold extends Threshold{

	private static final long serialVersionUID = -6426566287013692659L;
	private BigDecimal limiteMemoria;
	private BigDecimal limiteCpu;
	private BigDecimal limiteParticao;
	
	public BigDecimal getLimiteMemoria() {
		return limiteMemoria;
	}
	public void setLimiteMemoria(BigDecimal limiteMemoria) {
		this.limiteMemoria = limiteMemoria;
	}
	public BigDecimal getLimiteCpu() {
		return limiteCpu;
	}
	public void setLimiteCpu(BigDecimal limiteCpu) {
		this.limiteCpu = limiteCpu;
	}
	public BigDecimal getLimiteParticao() {
		return limiteParticao;
	}
	public void setLimiteParticao(BigDecimal limiteParticao) {
		this.limiteParticao = limiteParticao;
	}

}
