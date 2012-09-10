package br.com.fiap.monitor.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.SlaCalculado;
import br.com.fiap.monitor.bo.SlaBO;

@Path("/slaCalculado")
public class SlaCalculadoRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public SlaCalculado pegaUltimoSlaCalulculado(@PathParam("id") Integer noId, @QueryParam("tipo") String tipo){
		
		SlaBO bo = new SlaBO();
		
		if(noId != null && tipo != null){
			
			if(tipo.equals("mensal")){
				return bo.ultimoSlaMensalNo(noId);
			}else{
				return bo.ultimoSlaDiarioNo(noId);
			}
			
		}else{
			return null;
		}
	}
	
}
