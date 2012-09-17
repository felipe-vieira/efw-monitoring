package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.bo.ServidorBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/servidor")
public class ServidorRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Servidor getServerId(@PathParam("id") Integer id){
		ServidorBO bo = new ServidorBO();
		
		Servidor servidor = bo.getServidorId(id);
		SistemaOperacional so = bo.getSistemaOperacionalId(id);
		Memoria memoria = bo.getMemoriaId(id);
		Processador processador = bo.getProcessadorId(id);
		List<Particao> particoes = bo.getParticoesId(id);
		
		for(Particao p: particoes){
			p.setServidor(null);
		}
		
		servidor.setSistemaOperacional(so);
		servidor.setMemoria(memoria);
		servidor.setProcessador(processador);
		servidor.setParticoes(particoes);
		
		return servidor;
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveServidor(Servidor servidor, @QueryParam("thresholdId") @DefaultValue("0") Integer thresholdId, 
				@QueryParam("slaId") @DefaultValue("0") Long slaId){
		ServidorBO bo = new ServidorBO();
		return bo.saveServidor(servidor,thresholdId,slaId);		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateServidor(Servidor servidor, @PathParam("id") Long id, 
			@QueryParam("thresholdId") Integer thresholdId, @QueryParam("slaId") Long slaId){
		ServidorBO bo = new ServidorBO();
		return bo.saveServidor(servidor,thresholdId,slaId);	
	}
	
}

