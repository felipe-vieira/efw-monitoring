package br.com.fiap.monitor.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;

@Path("/saConfigMemory")
public class ServidorAplicacaoMemoriaRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ServidorAplicacaoMemoria> getMemoriasId(@QueryParam("id") Integer id){
		
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		
		List<ServidorAplicacaoMemoria> memorias = bo.getConfigMemorias(id);
				
		return memorias;
		
	}
	
}

