package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;

@Path("/servidorAplicacao")
public class ServidorAplicacaoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ServidorAplicacao getServidorAplicacaoId(@PathParam("id") Integer id){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		
		ServidorAplicacao sa = bo.getServidorAplicacaoId(id);
		
		if(sa instanceof Glassfish){
			sa.setSubTipo("Glassfish");
		}else if(sa instanceof JBoss){
			sa.setSubTipo("JBoss");
		}
		
		return sa;
	}
	
}

