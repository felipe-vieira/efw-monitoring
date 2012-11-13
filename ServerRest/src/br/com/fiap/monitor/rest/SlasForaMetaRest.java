package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.SlaCalculado;
import br.com.fiap.monitor.bo.SlaBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/slasForaMeta")
public class SlasForaMetaRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<SlaCalculado> pegaSlasForaMeta(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		
		PagingTO<SlaCalculado> retorno = new PagingTO<SlaCalculado>();
		
		SlaBO bo = new SlaBO();
		
		List<SlaCalculado> records = bo.listaSlaAbaixoMeta(start, limit);
		Long total = bo.contaSlasAbaixoMeta();
		
		retorno.setRecords(records);
		retorno.setTotal(total);
		retorno.setSuccess(true);
		
		return retorno;
			
	}
	
}
