package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

public class Sla implements Serializable {

	private static final long serialVersionUID = -2704033263436503927L;
	
	private Long id;
	private String nome;
	private Time horaInicio;
	private Time horaFim;
	private BigDecimal meta;
	private DiasSemanaSla diasSemana;
	private Date ultimaColeta;
	
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
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Time getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}
	public BigDecimal getMeta() {
		return meta;
	}
	public void setMeta(BigDecimal meta) {
		this.meta = meta;
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
