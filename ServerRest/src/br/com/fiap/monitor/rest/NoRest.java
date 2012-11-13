package br.com.fiap.monitor.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/no")
public class NoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public No listNos(@PathParam("id") Integer id){
		
		NoBO bo = new NoBO();
		No no = bo.getNoId(id);
		
		if(no != null){
			if(no instanceof Servidor){
				no.setTipo("Servidor");
			}else if (no instanceof ServidorAplicacao){
				no.setTipo("Servidor de Aplicacao");
			}else if (no instanceof BancoDados){
				no.setTipo("Banco de Dados");
			}else{
				no.setTipo("Desconhecido");
			}
		}
		
		return no;
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO deleteNo(@PathParam("id") Integer id){
		NoBO bo = new NoBO();
		return bo.desativaNo(id);
	}
	
}

