package br.com.fiap.monitor.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.monitor.bo.BancoDadosBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/sqlserver")
public class SQLServerRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveSQLServer(SQLServer sqlserver){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveSQLServer(sqlserver);	
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateSQLServer(SQLServer sqlserver){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveSQLServer(sqlserver);	
	}
}

