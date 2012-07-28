package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.monitor.bo.ServidorBO;

@Path("/servidores")
public class ServidoresRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getServerId(@PathParam("id") Integer id){
		ServidorBO bo = new ServidorBO();
		
		Servidor servidor = bo.getServidorId(id);
		SistemaOperacional so = bo.getSistemaOperacionalId(id);
		Memoria memoria = bo.getMemoriaId(id);
		Processador processador = bo.getProcessadorId(id);
		List<Particao> particoes = bo.getParticoesId(id);
		
		//Pra não gerar referencia ciclica no json...
		so.setServidor(null);
		memoria.setServidor(null);
		processador.setServidor(null);
		for(Particao p: particoes){
			p.setServidor(null);
		}
		
		servidor.setSistemaOperacional(so);
		servidor.setMemoria(memoria);
		servidor.setProcessador(processador);
		servidor.setParticoes(particoes);
				
		JSONObject json = new JSONObject();
		json.put("servidor", servidor);
		
		return json.toString();
		
	}
	
}

