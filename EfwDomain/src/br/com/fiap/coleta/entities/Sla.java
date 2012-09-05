package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Sla implements Serializable {

	private static final long serialVersionUID = -2704033263436503927L;
	
	private Long id;
	private String nome;
	private Date horaInicio;
	private Date horaFim;
	private BigDecimal meta;
	private DiasSemanaSla diasSemana;
	private Date ultimaColeta;
	private Boolean ativo;
	
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
	public BigDecimal getMeta() {
		return meta;
	}
	public void setMeta(BigDecimal meta) {
		this.meta = meta;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	public DiasSemanaSla getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(DiasSemanaSla diasSemana) {
		this.diasSemana = diasSemana;
	}
	public Date getUltimaColeta() {
		return ultimaColeta;
	}
	public void setUltimaColeta(Date ultimaColeta) {
		this.ultimaColeta = ultimaColeta;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		Sla other = (Sla) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
	
}
