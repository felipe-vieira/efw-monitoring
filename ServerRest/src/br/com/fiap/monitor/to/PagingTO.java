package br.com.fiap.monitor.to;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.coleta.entities.Alarme;

@XmlRootElement
public class PagingTO implements Serializable{
	
	private static final long serialVersionUID = -8979816907608844943L;
	
	@XmlElement
	private Boolean success;
	
	@XmlElement
	private Long total;
	
	@XmlElement
	private List<Alarme> records;
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public void setRecords(List<Alarme> records) {
		this.records = records;
	}
	
	
	

}
