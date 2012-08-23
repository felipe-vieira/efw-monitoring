package br.com.fiap.monitor.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;

@Path("/servidoresAplicacao")
public class ServidoresAplicacaoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ServidorAplicacao getServidorAplicacaoId(@PathParam("id") Integer id){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		
		ServidorAplicacao sa = bo.getServidorAplicacaoId(id);
		List<ServidorAplicacaoMemoria> memorias = bo.getConfigMemorias(id);
		
		
		if(sa instanceof JBoss){
			sa.setTipoServidorAplicacao("JBoss");
		}else if(sa instanceof Glassfish){
			sa.setTipoServidorAplicacao("Glassfish");
		}
		
		
		return sa;
		
	}
	
}
