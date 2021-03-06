package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.coleta.entities.enumerators.TipoSla;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SlaCalculado implements Serializable{

	private static final long serialVersionUID = -1136506875375872258L;
	
	private Long id;
	private Sla sla;
	private TipoSla tipo;
	private No no;
	private Date controle;
	private BigDecimal percentual;
	private Long tempoTotal;
	private Long tempoIndisponivel;
	private Long tempoJanela;
	
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
	public TipoSla getTipo() {
		return tipo;
	}
	public void setTipo(TipoSla tipo) {
		this.tipo = tipo;
	}
	public No getNo() {
		return no;
	}
	public void setNo(No no) {
		this.no = no;
	}
	public Date getControle() {
		return controle;
	}
	public void setControle(Date controle) {
		this.controle = controle;
	}
	public BigDecimal getPercentual() {
		return percentual;
	}
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
	public Long getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(Long tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public Long getTempoIndisponivel() {
		return tempoIndisponivel;
	}
	public void setTempoIndisponivel(Long tempoIndisponivel) {
		this.tempoIndisponivel = tempoIndisponivel;
	}
	public Long getTempoJanela() {
		return tempoJanela;
	}
	public void setTempoJanela(Long tempoJanela) {
		this.tempoJanela = tempoJanela;
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
		SlaCalculado other = (SlaCalculado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
