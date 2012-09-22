package br.com.fiap.monitor.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.MemoriaColeta;
import br.com.fiap.monitor.bo.MetricasBO;

@Path("/memoriaColetas")
public class MemoriaColetasRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<MemoriaColeta> listaMetricas(@QueryParam("idNo") Long idNo,  @QueryParam("inicio") String strInicio, @QueryParam("fim") String strFim ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/y H:m");
		
		try{
			Date inicio = sdf.parse(strInicio);
			Date fim = sdf.parse(strFim);
			
			MetricasBO bo = new MetricasBO();
			
			
			List<MemoriaColeta> lista =  bo.listMemoriaColeta(idNo, inicio, fim);
			
			if(lista != null){
				for (MemoriaColeta memoriaColeta : lista) {
					memoriaColeta.setMemoria(null);
				}
			}
			
			return lista;
			
		}catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
