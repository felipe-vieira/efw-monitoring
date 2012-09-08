package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.DiasSemanaSla;
import br.com.fiap.monitor.bo.SlaBO;
import br.com.fiap.monitor.to.ReturnTO;


@Path("/diasSemanaSla")
public class DiasSemanaSlaRest {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO createSla(DiasSemanaSla dias, @PathParam("id") Long id) {
		SlaBO bo = new SlaBO();
		return bo.saveDiasSemanaSla(dias);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateSla(DiasSemanaSla dias, @PathParam("id") Long id){
		SlaBO bo = new SlaBO();
		return bo.saveDiasSemanaSla(dias);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public DiasSemanaSla updateSla(@PathParam("id") Long id){
		SlaBO bo = new SlaBO();
		return bo.getDiasSemanaSla(id);
	}
	
}
