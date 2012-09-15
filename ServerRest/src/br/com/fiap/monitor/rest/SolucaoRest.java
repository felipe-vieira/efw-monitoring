package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Solucao;
import br.com.fiap.monitor.bo.SolucaoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/solucao")
public class SolucaoRest {
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnTO saveSolucao(Solucao solucao, @QueryParam("idAlarme") Long idAlarme, 
			@QueryParam("idUsuario") Long idUsuario){
		
		SolucaoBO solucaoBO = new SolucaoBO();
		return solucaoBO.saveSolucao(solucao, idAlarme,idUsuario);
		
	}
	
	
}
