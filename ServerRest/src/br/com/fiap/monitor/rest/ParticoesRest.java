package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.monitor.bo.ServidorBO;

@Path("/particoes")
public class ParticoesRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Particao> listaMetricas(@QueryParam("idNo") Integer idNo) {
		
		ServidorBO servidorBO = new ServidorBO();
		return servidorBO.listParticoesServidor(idNo);
		
	}
	
}
