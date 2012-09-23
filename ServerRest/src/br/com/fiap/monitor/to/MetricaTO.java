
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
	private Long max;
	private BigDecimal valorPercentual;

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
	
	public BigDecimal getValorPercentual() {
		
		if(this.valorPercentual != null){
			return valorPercentual.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}else{
			Double valorDouble = this.valor.doubleValue();
			Double valorMax = this.max.doubleValue();
			return new BigDecimal((valorDouble/valorMax)*100).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			 
		}
		
	}
	
	public void setValorPercentual(BigDecimal valorPercentual) {
		this.valorPercentual = valorPercentual;
	}
	
}
