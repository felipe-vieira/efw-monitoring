package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;


public class ParticaoColeta implements Serializable{
	
	
	private static final long serialVersionUID = 2910718394652571542L;

	private Long id ;
	
	private Particao particao;
	
	private Date dataColeta;

	private Long usado;

	public ParticaoColeta(){
		
	}
	
	public ParticaoColeta(Particao particao) {
		this.particao = particao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Particao getParticao() {
		return particao;
	}

	public void setParticao(Particao particao) {
		this.particao = particao;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Long getUsado() {
		return usado;
	}

	public void setUsado(Long usado) {
		this.usado = usado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataColeta == null) ? 0 : dataColeta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((particao == null) ? 0 : particao.hashCode());
		result = prime * result + ((usado == null) ? 0 : usado.hashCode());
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
		ParticaoColeta other = (ParticaoColeta) obj;
		if (dataColeta == null) {
			if (other.dataColeta != null)
				return false;
		} else if (!dataColeta.equals(other.dataColeta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (particao == null) {
			if (other.particao != null)
				return false;
		} else if (!particao.equals(other.particao))
			return false;
		if (usado == null) {
			if (other.usado != null)
				return false;
		} else if (!usado.equals(other.usado))
			return false;
		return true;
	}
	

	
}
