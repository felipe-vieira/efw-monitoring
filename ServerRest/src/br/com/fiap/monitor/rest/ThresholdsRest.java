package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Threshold;
import br.com.fiap.monitor.bo.ThresholdBO;

@Path("/thresholds")
public class ThresholdsRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Threshold> listaThresholds(){
		ThresholdBO thresholdBO = new ThresholdBO();
		return thresholdBO.listAllThresholds();
	}
	
}
