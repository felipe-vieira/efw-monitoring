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

import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/jboss")
public class JBossRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveJboss(JBoss jboss, @QueryParam("thresholdId") Integer thresholdId,
			@QueryParam("slaId") Long slaId){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveJBoss(jboss,thresholdId,slaId);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateJboss(JBoss jboss, @PathParam("id") Long id, @QueryParam("thresholdId") Integer thresholdId,
			@QueryParam("slaId") Long slaId){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveJBoss(jboss,thresholdId,slaId);	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public JBoss getSqlServerID(@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return (JBoss) noBO.getNoId(id);
		}else{
			return null;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO desativaJboss(JBoss jboss,@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return noBO.desativaNo(id);
		}else{
			return null;
		}
	}
	
}

