package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;
import br.com.fiap.monitor.bo.ServidorBO;

@Path("/servidoresAplicacao")
public class ServidoresAplicacaoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ServidorAplicacao getServidorAplicacaoId(@PathParam("id") Integer id){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		
		ServidorAplicacao sa = bo.getServidorAplicacaoId(id);
		
		if(sa instanceof JBoss){
			sa.setTipoServidorAplicacao("Jboss");
		}else if(sa instanceof Glassfish){
			sa.setTipoServidorAplicacao("Glassfish");
		}
		
		
		return sa;
		
	}
	
}

