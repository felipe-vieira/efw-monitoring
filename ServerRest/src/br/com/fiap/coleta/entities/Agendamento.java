package br.com.fiap.coleta.entities;


public class Agendamento {
	

	private Integer id;
	private Integer intervalo;
	private String horaInicio;
	private String horaFim;
	private Boolean agendado;
	private Boolean ativo;
	private No No;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public Boolean getAgendado() {
		return agendado;
	}
	public void setAgendado(Boolean agendado) {
		this.agendado = agendado;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public No getNo() {
		return No;
	}
	public void setNo(No no) {
		No = no;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((No == null) ? 0 : No.hashCode());
		result = prime * result
				+ ((agendado == null) ? 0 : agendado.hashCode());
		result = prime * result + ((horaFim == null) ? 0 : horaFim.hashCode());
		result = prime * result
				+ ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((intervalo == null) ? 0 : intervalo.hashCode());
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
		Agendamento other = (Agendamento) obj;
		if (No == null) {
			if (other.No != null)
				return false;
		} else if (!No.equals(other.No))
			return false;
		if (agendado == null) {
			if (other.agendado != null)
				return false;
		} else if (!agendado.equals(other.agendado))
			return false;
		if (horaFim == null) {
			if (other.horaFim != null)
				return false;
		} else if (!horaFim.equals(other.horaFim))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intervalo == null) {
			if (other.intervalo != null)
				return false;
		} else if (!intervalo.equals(other.intervalo))
			return false;
		return true;
	}
	  
	
	  
	
}
