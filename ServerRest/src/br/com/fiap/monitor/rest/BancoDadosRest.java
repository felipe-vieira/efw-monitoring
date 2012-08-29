package br.com.fiap.monitor.rest;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.monitor.bo.NoBO;

@Path("/bancoDados")
public class BancoDadosRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public BancoDados getBancoDadosId(@PathParam("id") Integer id){
		NoBO bo = new NoBO();
		
		BancoDados bd = (BancoDados) bo.getNoId(id);
		return bd;
		
	}
	
}

