package br.com.fiap.monitor.to;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Threshold;
import br.com.fiap.monitor.entities.Usuario;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Alarme.class,BancoBackup.class,Threshold.class,Usuario.class,No.class})
public class PagingTO<T> implements Serializable{
	
	private static final long serialVersionUID = -8979816907608844943L;
	
	private Boolean success;
	
	private Long total;
	
	private List<T> records;
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public Boolean getSuccess() {
		return success;
	}
	public Long getTotal() {
		return total;
	}
	public List<T> getRecords() {
		return records;
	}

}
