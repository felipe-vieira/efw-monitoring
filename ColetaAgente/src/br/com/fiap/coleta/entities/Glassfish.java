package br.com.fiap.coleta.entities;

import java.io.Serializable;

import br.com.fiap.coleta.cgt.coletas.GlassFishColeta;


public class Glassfish extends ServidorAplicacao implements Serializable{

	private static final long serialVersionUID = -4029595958441788034L;
	
	private String user;
	
	private String senha;
	
	private String versao;
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	public void coleta(){
		System.out.println("OPA!");
		GlassFishColeta coleta = new GlassFishColeta(this);
		coleta.initColeta();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Glassfish other = (Glassfish) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}


	
	
}