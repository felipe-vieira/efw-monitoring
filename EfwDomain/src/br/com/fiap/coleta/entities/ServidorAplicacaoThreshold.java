package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class ServidorAplicacaoThreshold implements Serializable {

	private static final long serialVersionUID = -3429794152265330608L;
	public Integer id;
	public String nome;
	public BigDecimal thresholdHeap;
	public BigDecimal thresholdNonHeap;
	public BigDecimal thresholdCpuUserTime;
	public BigDecimal thresholdCpuCpuTime;
	
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
		ServidorAplicacaoThreshold other = (ServidorAplicacaoThreshold) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
