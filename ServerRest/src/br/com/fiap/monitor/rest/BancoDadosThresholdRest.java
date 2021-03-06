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

import br.com.fiap.coleta.entities.BancoDadosThreshold;
import br.com.fiap.coleta.entities.Threshold;
import br.com.fiap.monitor.bo.ThresholdBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/bancoDadosThreshold")
public class BancoDadosThresholdRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public BancoDadosThreshold getThreshold(@PathParam("id") Integer id){
		ThresholdBO thresholdBO = new ThresholdBO();
		return (BancoDadosThreshold) thresholdBO.getById(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveThreshold(BancoDadosThreshold threshold){
		if(threshold != null){
			ThresholdBO thresholdBO = new ThresholdBO();
			return thresholdBO.saveUpdateThreshold(threshold);
		}else{
			return null;
		}	
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateThreshold(BancoDadosThreshold threshold, @PathParam("id") Integer id){
		if(threshold != null){
			ThresholdBO thresholdBO = new ThresholdBO();
			return thresholdBO.saveUpdateThreshold(threshold);
		}else{
			return null;
		}	
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO deleteThreshold(Threshold threshold, @PathParam("id") Integer id){
		if(threshold != null){
			ThresholdBO thresholdBO = new ThresholdBO();
			return thresholdBO.deleteThreshold(threshold);
		}else{
			return null;
		}	
	}
	
}
