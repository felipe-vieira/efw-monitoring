package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.BancoJobColeta;
import br.com.fiap.monitor.bo.BancoDadosBO;
import br.com.fiap.monitor.to.PagingTO;
@Path("/jobBancoDadosColetas")
public class BancoJobColetasRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PagingTO<BancoJobColeta> listaMetricas(@QueryParam("idJob") Integer idJob, @QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit) {
		
		PagingTO<BancoJobColeta> paging = new PagingTO<BancoJobColeta>();
		
		BancoDadosBO bdBO = new BancoDadosBO(); 
		List<BancoJobColeta> records = bdBO.listHistoricoJob(idJob, start, limit);
		Long total = bdBO.contaHistoricoJob(idJob);
		
		paging.setRecords(records);
		paging.setTotal(total);
		paging.setSuccess(true);

		return paging;
		
	}
	
}
	