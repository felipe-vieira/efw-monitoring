package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.monitor.bo.SlaBO;
import br.com.fiap.monitor.to.ReturnTO;


@Path("/sla")
public class SlaRest {

	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Sla getSla(@PathParam("id") Long id){
		SlaBO bo = new SlaBO();
		return bo.getSlaById(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnTO createSla(Sla sla) {
		SlaBO bo = new SlaBO();
		return bo.saveUpdateSla(sla);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateSla(Sla sla, @PathParam("id") Long id){
		SlaBO bo = new SlaBO();
		return bo.saveUpdateSla(sla);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO deleteSla(Sla sla, @PathParam("id") Long id){
		SlaBO bo = new SlaBO();
		return bo.desativaSla(sla);
	}
	
}
