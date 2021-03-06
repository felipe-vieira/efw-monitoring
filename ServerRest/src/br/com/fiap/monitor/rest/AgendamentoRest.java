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

import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.monitor.bo.AgendamentoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/agendamento")
public class AgendamentoRest {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveAgendamento(Agendamento agendamento, @QueryParam("noId") Integer noId){
		AgendamentoBO bo = new AgendamentoBO();
		return bo.saveUpdateAgendamento(agendamento, noId);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateAgendamento(Agendamento agendamento, @PathParam("id") Integer id, @QueryParam("noId") Integer noId){
		AgendamentoBO bo = new AgendamentoBO();
		return bo.saveUpdateAgendamento(agendamento, noId);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO deleteAgendamento(@PathParam("id") Integer id){
		AgendamentoBO bo = new AgendamentoBO();
		return bo.deletaAgendamento(id);
	}
}
