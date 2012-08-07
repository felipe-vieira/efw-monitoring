package br.com.fiap.coleta.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Servidor extends No{
	
	private String hostname;
	private ServidorThreshold threshold;
	
	private SistemaOperacional sistemaOperacional;
	private Processador processador;
	private Memoria memoria;
	private List<Particao> particoes;
	
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((hostname == null) ? 0 : hostname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servidor other = (Servidor) obj;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		return true;
	}


}
