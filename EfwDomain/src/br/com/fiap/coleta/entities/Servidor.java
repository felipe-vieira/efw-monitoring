package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="servidor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Servidor extends No implements Serializable{
	
	private static final long serialVersionUID = 684732961548387095L;
	private ServidorThreshold threshold;
	
	private SistemaOperacional sistemaOperacional;
	private Processador processador;
	private Memoria memoria;
	private List<Particao> particoes;
	
	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}
	
	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}
	
	public Processador getProcessador() {
		return processador;
	}
	
	public void setProcessador(Processador processador) {
		this.processador = processador;
	}
	
	public Memoria getMemoria() {
		return memoria;
	}
	
	public void setMemoria(Memoria memoria) {
		this.memoria = memoria;
	}
	
	public List<Particao> getParticoes() {
		return particoes;
	}
	
	public void setParticoes(List<Particao> particoes) {
		this.particoes = particoes;
	}
		
	public ServidorThreshold getThreshold() {
		return threshold;
	}

	public void setThreshold(ServidorThreshold threshold) {
		this.threshold = threshold;
	}




}
