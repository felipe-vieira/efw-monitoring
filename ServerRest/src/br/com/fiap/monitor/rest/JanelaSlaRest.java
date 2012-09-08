package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.JanelaSla;
import br.com.fiap.monitor.bo.SlaBO;
import br.com.fiap.monitor.to.ReturnTO;


@Path("/janelaSla")
public class JanelaSlaRest {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnTO createJanelaSla(JanelaSla janela, @QueryParam("slaId") Long slaId) {
		SlaBO bo = new SlaBO();
		return bo.saveUpdateJanelaSla(janela, slaId);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateJanelaSla(JanelaSla janela, @QueryParam("slaId") Long slaId) {
		SlaBO bo = new SlaBO();
		return bo.saveUpdateJanelaSla(janela, slaId);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO deleteJanelaSla(JanelaSla janela, @PathParam("id") Long id){
		SlaBO bo = new SlaBO();
		return bo.desativaJanelaSla(janela);
	}
	
}
