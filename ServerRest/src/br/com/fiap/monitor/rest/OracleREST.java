package br.com.fiap.monitor.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.monitor.bo.BancoDadosBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/oracle")
public class OracleREST {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveOracle(Oracle oracle){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveOracle(oracle);	
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateOracle(Oracle oracle, @PathParam("id") Long id){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveOracle(oracle);	
	}
	
}

