package br.com.fiap.monitor.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.monitor.bo.BancoDadosBO;
import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/sqlserver")
public class SQLServerRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO saveSQLServer(SQLServer sqlserver, @QueryParam("thresholdId") Integer thresholdId, 
			@QueryParam("slaId") Long slaId){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveSQLServer(sqlserver, thresholdId,slaId);	
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateSQLServer(SQLServer sqlserver, @QueryParam("thresholdId") Integer thresholdId,
			@QueryParam("slaId") Long slaId){
		BancoDadosBO bo = new BancoDadosBO();
		return bo.saveSQLServer(sqlserver, thresholdId,slaId);	
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public SQLServer getSQLServerId(@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return (SQLServer) noBO.getNoId(id);
		}else{
			return null;
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO desativaSQLServer(SQLServer sqlserver,@PathParam("id") Integer id){
		
		NoBO noBO = new NoBO();
		if(id!=null){
			return noBO.desativaNo(id);
		}else{
			return null;
		}
	}
}

