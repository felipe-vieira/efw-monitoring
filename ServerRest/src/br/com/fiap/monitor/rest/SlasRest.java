package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.monitor.bo.SlaBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/slas")
public class SlasRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<Sla> listSlas(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		
		
		PagingTO<Sla> retorno = new PagingTO<Sla>();
		
		SlaBO sla = new SlaBO();
		
		List<Sla> records = sla.listSlasAtivos(start,limit);
		Long total = sla.contaSlasAtivos();
		
		retorno.setSuccess(true);
		retorno.setTotal(total);
		retorno.setRecords(records);
		
		return retorno;
	}


}
