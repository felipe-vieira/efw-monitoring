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

import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/glassfish")
public class GlassfishRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveGlassfish(Glassfish glassfish, @QueryParam("thresholdId") Integer thresholdId, 
			@QueryParam("slaId") Long slaId){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveGlassfish(glassfish,thresholdId,slaId);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateGlassfish(Glassfish glassfish, @PathParam("id") Long id, 
			@QueryParam("thresholdId") Integer thresholdId, @QueryParam("slaId") Long slaId){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		return bo.saveGlassfish(glassfish,thresholdId,slaId);	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Glassfish getSqlServerID(@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return (Glassfish) noBO.getNoId(id);
		}else{
			return null;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO desativaGlassgish(Glassfish glassfish,@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return noBO.desativaNo(id);
		}else{
			return null;
		}
	}
	
	
}

