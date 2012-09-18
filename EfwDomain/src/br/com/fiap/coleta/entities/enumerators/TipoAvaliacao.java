package br.com.fiap.coleta.entities.enumerators;

public enum TipoAvaliacao {
	
	NEGATIVO(-1),POSITIVO(1);
	
	private Integer value;
	
	private TipoAvaliacao(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
}
