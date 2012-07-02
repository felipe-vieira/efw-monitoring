package br.com.fiap.monitor.coletas.estrutura;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class ReturnObject{
	
	/**
	 * Classe responsável por padronizar o retorno...
	 */
	private Map<String,Object> values;
	
	public ReturnObject(){
		this.values = new HashMap<String,Object>();
	}
	
	/**
	 * Coloca no map da clase
	 * @param Descrição da Metrica
	 * @param Valor
	 */
	public void putValue(String key, Object value){
		this.values.put(key, value);
	}
	
	
	/**
	 * Pega os valores e transforma em JSON
	 * @return String em JSON
	 */	
	public String toJSON(){
		JSONObject json = new JSONObject();
		json.putAll(this.values);
		return json.toString();
	}
	
}
