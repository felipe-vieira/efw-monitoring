package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.util.Date;


public class Indisponibilidade implements Serializable {
	
	private static final long serialVersionUID = -3461539056126528876L;

	  private Integer id;

	  private Date inicio;

	  private Date fim;

	  private No no;

	  
	  
	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}
	  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public No getNo() {
		return no;
	}

	public void setNo(No no) {
		this.no = no;
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
		Indisponibilidade other = (Indisponibilidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	  
}
