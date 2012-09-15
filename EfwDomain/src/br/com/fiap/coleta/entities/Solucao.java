package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.coleta.entities.enumerators.SubTipoNo;
import br.com.fiap.coleta.entities.enumerators.TipoNo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Solucao implements Serializable{

	private static final long serialVersionUID = -5090523114279419184L;
	private Long id;
	private Date data;
	private String titulo;
	private String descricao;
	private TipoAlarme tipoAlarme;
	private Usuario usuario;
	private No no;	
	private TipoNo tipo;
	private SubTipoNo subTipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoAlarme getTipoAlarme() {
		return tipoAlarme;
	}
	public void setTipoAlarme(TipoAlarme tipoAlarme) {
		this.tipoAlarme = tipoAlarme;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public No getNo() {
		return no;
	}
	public void setNo(No no) {
		this.no = no;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public TipoNo getTipo() {
		return tipo;
	}
	public void setTipo(TipoNo tipo) {
		this.tipo = tipo;
	}
	public SubTipoNo getSubTipo() {
		return subTipo;
	}
	public void setSubTipo(SubTipoNo subTipo) {
		this.subTipo = subTipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Solucao other = (Solucao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
