package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class ServidorThreshold implements Serializable {

	private static final long serialVersionUID = -6426566287013692659L;
	private Long id;
	private String nome;
	private BigDecimal limiteMemoria;
	private BigDecimal limiteCpu;
	private BigDecimal limiteParticao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
		ServidorThreshold other = (ServidorThreshold) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
