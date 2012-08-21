package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.monitor.bo.ServidorAplicacaoBO;

@Path("/deployments")
public class DeploymentsRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ServidorAplicacaoDeployment> getDeploymentsId(@QueryParam("id") Integer id){
		ServidorAplicacaoBO bo = new ServidorAplicacaoBO();
		
		List<ServidorAplicacaoDeployment> deploy = bo.getDeploymentsId(id);
		
		return deploy;		
	}
}
