package br.com.fiap.coleta.entities;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServidorAplicacaoThreshold extends Threshold{

	private static final long serialVersionUID = -3429794152265330608L;
	public BigDecimal thresholdHeap;
	public BigDecimal thresholdNonHeap;
	public BigDecimal thresholdCpuUserTime;
	public BigDecimal thresholdCpuCpuTime;
	
	public BigDecimal getThresholdHeap() {
		return thresholdHeap;
	}
	public void setThresholdHeap(BigDecimal thresholdHeap) {
		this.thresholdHeap = thresholdHeap;
	}
	public BigDecimal getThresholdNonHeap() {
		return thresholdNonHeap;
	}
	public void setThresholdNonHeap(BigDecimal thresholdNonHeap) {
		this.thresholdNonHeap = thresholdNonHeap;
	}
	public BigDecimal getThresholdCpuUserTime() {
		return thresholdCpuUserTime;
	}
	public void setThresholdCpuUserTime(BigDecimal thresholdCpuUserTime) {
		this.thresholdCpuUserTime = thresholdCpuUserTime;
	}
	public BigDecimal getThresholdCpuCpuTime() {
		return thresholdCpuCpuTime;
	}
	public void setThresholdCpuCpuTime(BigDecimal thresholdCpuCpuTime) {
		this.thresholdCpuCpuTime = thresholdCpuCpuTime;
	}

	
	
	
}
