package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class JanelaSla implements Serializable {

	private static final long serialVersionUID = 2000347320296365654L;
	private Long id;
	private Sla sla;
	private String descricao;
	private Date dataInicio;
	private Date dateFim;
	private Time horaInicio;
	private Time horaFim;
	private DiasSemanaJanelaSla diasSemana;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sla getSla() {
		return sla;
	}
	public void setSla(Sla sla) {
		this.sla = sla;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDateFim() {
		return dateFim;
	}
	public void setDateFim(Date dateFim) {
		this.dateFim = dateFim;
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
	public DiasSemanaJanelaSla getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(DiasSemanaJanelaSla diasSemana) {
		this.diasSemana = diasSemana;
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
		JanelaSla other = (JanelaSla) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
