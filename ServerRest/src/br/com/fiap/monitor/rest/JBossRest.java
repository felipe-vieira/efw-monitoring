package br.com.fiap.monitor.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/jboss")
public class JBossRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveJboss(JBoss jboss){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveJBoss(jboss);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateJboss(JBoss jboss, @PathParam("id") Long id){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveJBoss(jboss);	
	}
	
}

