
package br.com.fiap.monitor.to;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MetricaTO {

	private Date data;
	private Long valor;
	private Long valorAlt;
	private Long max;
	private BigDecimal valorPercentual;
	private BigDecimal valorPercentualAlt;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	public Long getMax() {
		return max;
	}
	
	public void setMax(Long max) {
		this.max = max;
	}
	
	public void setValorPercentual(BigDecimal valorPercentual) {
		this.valorPercentual = valorPercentual;
	}
	
	public BigDecimal getValorPercentual() {
		
		if(this.valorPercentual != null){
			return valorPercentual.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}else{
			
			if(this.valor != null && this.max != null){
				Double valorDouble = this.valor.doubleValue();
				Double valorMax = this.max.doubleValue();
				return new BigDecimal((valorDouble/valorMax)*100).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			}else{
				return null;
			}
		}
		
	}

	public Long getValorAlt() {
		return valorAlt;
	}

	public void setValorAlt(Long valorAlt) {
		this.valorAlt = valorAlt;
	}
	
	public void setValorPercentualAlt(BigDecimal valorPercentualAlt) {
		this.valorPercentualAlt = valorPercentualAlt;
		
	}
	
	public BigDecimal getValorPercentualAlt() {
		if(this.valorPercentualAlt != null){
			return valorPercentualAlt.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}else{
			if(this.valorAlt != null && this.max != null){
				Double valorDouble = this.valorAlt.doubleValue();
				Double valorMax = this.max.doubleValue();
				return new BigDecimal((valorDouble/valorMax)*100).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			}else{
				return null;	
			}
			
		}
	}

	
}
