package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Threshold;
import br.com.fiap.monitor.bo.ThresholdBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/thresholds")
public class ThresholdsRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<Threshold> listaThresholds(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		
		PagingTO<Threshold> retorno = new PagingTO<Threshold>();
		
		try{
			ThresholdBO thresholdBO = new ThresholdBO();
			List<Threshold> records = thresholdBO.listThresholdsLimit(start,limit);
			Long count = thresholdBO.contaThresholds();
			
			retorno.setTotal(count);
			retorno.setRecords(records);
			retorno.setSuccess(true);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			retorno.setSuccess(false);
		}
		
		return retorno;
	}
	
}
