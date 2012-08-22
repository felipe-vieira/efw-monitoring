package br.com.fiap.coleta.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.coleta.entities.enumerators.TipoMemoriaServidorAplicacao;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServidorAplicacaoMemoria implements Serializable{
		
	private static final long serialVersionUID = -4848871582739761986L;
	
	private Long id;
	private Long init;
	private Long max;
	
	private ServidorAplicacao servidorAplicacao;
	
	private TipoMemoriaServidorAplicacao tipo;
	
	public ServidorAplicacaoMemoria(ServidorAplicacao servidorAplicacao) {
		this.servidorAplicacao = servidorAplicacao;
	}
	
	public ServidorAplicacaoMemoria(){
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInit() {
		return init;
	}
	public void setInit(Long init) {
		this.init = init;
	}
	public Long getMax() {
		return max;
	}
	public void setMax(Long max) {
		this.max = max;
	}
	
	public ServidorAplicacao getServidorAplicacao() {
		return servidorAplicacao;
	}
	public void setServidorAplicacao(ServidorAplicacao servidorAplicacao) {
		this.servidorAplicacao = servidorAplicacao;
	}
	public TipoMemoriaServidorAplicacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoMemoriaServidorAplicacao tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((init == null) ? 0 : init.hashCode());
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime
				* result
				+ ((servidorAplicacao == null) ? 0 : servidorAplicacao
						.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ServidorAplicacaoMemoria other = (ServidorAplicacaoMemoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (init == null) {
			if (other.init != null)
				return false;
		} else if (!init.equals(other.init))
			return false;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (servidorAplicacao == null) {
			if (other.servidorAplicacao != null)
				return false;
		} else if (!servidorAplicacao.equals(other.servidorAplicacao))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
		
}
