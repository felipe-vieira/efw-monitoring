package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.coleta.entities.enumerators.CriticidadeAlarme;
import br.com.fiap.coleta.entities.enumerators.StatusAlarme;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Alarme implements Serializable{

	private static final long serialVersionUID = -3433864969574318639L;
	private Long id;
	private No no;
	private Date data;
	private TipoAlarme tipo;
	private StatusAlarme status;
	private BigDecimal valor;
	private BigDecimal valorLimite;
	private String parametro;
	private Integer contagem;
	private CriticidadeAlarme criticidade;
	private Solucao solucao;
		
	public Alarme(){
		this.status = StatusAlarme.NAO_LIDO;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public No getNo() {
		return no;
	}
	public void setNo(No no) {
		this.no = no;
	}
	public Date getData() {
		
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoAlarme getTipo() {
		return tipo;
	}
	public void setTipo(TipoAlarme tipo) {
		this.tipo = tipo;
	}
	public StatusAlarme getStatus() {
		return status;
	}
	public void setStatus(StatusAlarme status) {
		this.status = status;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorLimite() {
		return valorLimite;
	}

	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}

	public Integer getContagem() {
		return contagem;
	}

	public void setContagem(Integer contagem) {
		this.contagem = contagem;
	}

	public CriticidadeAlarme getCriticidade() {
		return criticidade;
	}

	public void setCriticidade(CriticidadeAlarme criticidade) {
		this.criticidade = criticidade;
	}

	public Solucao getSolucao() {
		return solucao;
	}

	public void setSolucao(Solucao solucao) {
		this.solucao = solucao;
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
		Alarme other = (Alarme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	
	
	
	
	
}
