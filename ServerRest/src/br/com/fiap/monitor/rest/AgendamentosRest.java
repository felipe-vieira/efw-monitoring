package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.monitor.bo.AgendamentoBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/agendamentos")
public class AgendamentosRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<Agendamento> listAgendamentos(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		PagingTO<Agendamento> paging = new PagingTO<Agendamento>();
		AgendamentoBO bo = new AgendamentoBO();
		
		List<Agendamento> records = bo.listAgendamentoLimit(start, limit);
		Long total = bo.countAgendamentos();
		
		paging.setSuccess(true);
		paging.setRecords(records);
		paging.setTotal(total);
				
		return paging;
		
	}
	
}