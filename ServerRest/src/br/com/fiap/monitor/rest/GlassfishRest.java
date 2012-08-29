package br.com.fiap.monitor.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/glassfish")
public class GlassfishRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveGlassfish(Glassfish glassfish){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveGlassfish(glassfish);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateGlassfish(Glassfish glassfish, @PathParam("id") Long id){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveGlassfish(glassfish);	
	}
	
}

