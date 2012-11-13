package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.monitor.bo.AlarmeBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/alarme")
public class AlarmeRest {
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateAlarme(@PathParam("id") Long id, Alarme alarme, @QueryParam("idSolucao") Long idSolucao){
		AlarmeBO alarmeBO = new AlarmeBO();
		return alarmeBO.updateAlarme(id,alarme,idSolucao);
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Alarme getAlarme(@PathParam("id") Long id){
		AlarmeBO alarmeBO = new AlarmeBO();
		return alarmeBO.getAlarmeId(id);
	}
	
	
}
