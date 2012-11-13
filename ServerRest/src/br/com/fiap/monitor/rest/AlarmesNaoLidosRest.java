package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.monitor.bo.AlarmeBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/alarmesNaoLidos")
public class AlarmesNaoLidosRest {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<Alarme> getAlarmesNo(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		
		PagingTO<Alarme> retorno = new PagingTO<Alarme>();
		
		AlarmeBO alarmeBO = new AlarmeBO();
		
		List<Alarme> alarmes = alarmeBO.listaLimitAlarmesNaoLidos(start, limit);
		Long total = alarmeBO.contaAlarmesNaoLidos();
			
		retorno.setSuccess(true);
		retorno.setTotal(total);
		retorno.setRecords(alarmes);
		
		return retorno;		

	} 
	
}
