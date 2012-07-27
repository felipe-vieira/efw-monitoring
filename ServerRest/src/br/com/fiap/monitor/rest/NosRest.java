package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;


import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;

//Caminho da pagina, nesse caso ficaria: localhost:8080/ServerRest/rest/nos
@Path("/nos")
public class NosRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<No> listNos(){
		NoBO bo = new NoBO();
		List<No> nos = bo.listAllNos();
		
		for(No no: nos){
			if(no instanceof Servidor){
				no.setTipo("Servidor");
			}else if (no instanceof ServidorAplicacao){
				no.setTipo("Servidor de Aplicacao");
			}else if (no instanceof BancoDados){
				no.setTipo("Banco de Dados");
			}else{
				no.setTipo("Desconhecido");
			}
		}
		
		return nos;
	}
	
	/*
	 * Requsição do tipo PUT (Update), recebe e produz JSON, 
	 * o primeiro parametro é o ID que vem na url ex: localhost/nos/1
	 * o segundo é todo o json gerado pelo ext.
	 */
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String updateNos(@PathParam("id") Integer id, String x){
		JSONObject json = JSONObject.fromObject(x);
		
		//Pega na base o No com essa ID.
		NoBO bo = new NoBO();
		No no = bo.getNoId(id);
		
		//Nesse caso ele só atualiza o nome o nome
		no.setNome(json.getString("nome"));
		
		//Salva no banco
		bo.saveOrUpdateNo(no);
		
		System.out.println(x);
		return "{success: true}";
	}
}
