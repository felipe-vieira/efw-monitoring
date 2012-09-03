package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.BancoDadosThreshold;
import br.com.fiap.monitor.bo.ThresholdBO;

@Path("/bancoDadosThresholds")
public class BancoDadosThresholdsRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BancoDadosThreshold> listThresholds(){
		
		ThresholdBO bo = new ThresholdBO();
		return bo.listaThresholdsBancoDados();
		
	}
	
	
	
	
}
