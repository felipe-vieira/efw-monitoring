package br.com.fiap.coleta.entities;

import java.io.Serializable;

public class DiasSemanaJanelaSla implements Serializable{

	private static final long serialVersionUID = -4784458913690583916L;
	
	private Sla sla;
	private Boolean dia1;
	private Boolean dia2;
	private Boolean dia3;
	private Boolean dia4;
	private Boolean dia5;
	private Boolean dia6;
	private Boolean dia7;
	
	public Sla getSla() {
		return sla;
	}
	public void setSla(Sla sla) {
		this.sla = sla;
	}
	public Boolean getDia1() {
		return dia1;
	}
	public void setDia1(Boolean dia1) {
		this.dia1 = dia1;
	}
	public Boolean getDia2() {
		return dia2;
	}
	public void setDia2(Boolean dia2) {
		this.dia2 = dia2;
	}
	public Boolean getDia3() {
		return dia3;
	}
	public void setDia3(Boolean dia3) {
		this.dia3 = dia3;
	}
	public Boolean getDia4() {
		return dia4;
	}
	public void setDia4(Boolean dia4) {
		this.dia4 = dia4;
	}
	public Boolean getDia5() {
		return dia5;
	}
	public void setDia5(Boolean dia5) {
		this.dia5 = dia5;
	}
	public Boolean getDia6() {
		return dia6;
	}
	public void setDia6(Boolean dia6) {
		this.dia6 = dia6;
	}
	public Boolean getDia7() {
		return dia7;
	}
	public void setDia7(Boolean dia7) {
		this.dia7 = dia7;
	}
	
}
