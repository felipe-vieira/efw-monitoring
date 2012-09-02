package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Threshold;
import br.com.fiap.monitor.bo.ThresholdBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/threshold")
public class ThresholdRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Threshold getThreshold(@PathParam("id") Integer id){
		ThresholdBO thresholdBO = new ThresholdBO();
		return thresholdBO.getById(id);
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
