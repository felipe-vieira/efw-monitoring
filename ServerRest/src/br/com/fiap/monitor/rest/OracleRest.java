package br.com.fiap.monitor.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.monitor.bo.BancoDadosBO;
import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/oracle")
public class OracleRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveOracle(Oracle oracle, @QueryParam("thresholdId") Integer thresholdId){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveOracle(oracle,thresholdId);	
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateOracle(Oracle oracle, @PathParam("id") Long id, @QueryParam("thresholdId") Integer thresholdId){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveOracle(oracle,thresholdId);	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Oracle getOracle(@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return (Oracle) noBO.getNoId(id);
		}else{
			return null;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO desativaOracle(Oracle oracle,@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return noBO.desativaNo(id);
		}else{
			return null;
		}
	}
}

