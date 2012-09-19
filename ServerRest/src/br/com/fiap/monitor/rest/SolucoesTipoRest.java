package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Solucao;
import br.com.fiap.monitor.bo.SolucaoBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/solucoesTipo")
public class SolucoesTipoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<Solucao> listaSolucoesNo(@QueryParam("idNo") Long idNo, @QueryParam("idTipoAlarme") Integer idTipoAlarme,
			@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		
		PagingTO<Solucao> paging = new PagingTO<Solucao>();
		
		SolucaoBO solucaoBO = new SolucaoBO();
		List<Solucao> records = solucaoBO.listaSolucoesTipo(idNo, idTipoAlarme, start, limit);
		Long total = solucaoBO.contaSolucoesTipo(idNo, idTipoAlarme);
		
		paging.setRecords(records);
		paging.setTotal(total);
		paging.setSuccess(true);
		
		return paging;
		
	}
	
	
	
}
