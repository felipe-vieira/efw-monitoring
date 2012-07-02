package br.com.fiap.coleta.entities;

import java.io.Serializable;

import br.com.fiap.coleta.cgt.coletas.JBossColeta;

public class JBoss extends ServidorAplicacao implements Serializable {
	
	private static final long serialVersionUID = 5637561467657665987L;
	
	public String versao;
	
	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
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
		JBoss other = (JBoss) obj;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}
	
	
	public void coleta(){
		
		JBossColeta coleta =  new JBossColeta(this);
		coleta.initColeta();
		
	}
	

}
