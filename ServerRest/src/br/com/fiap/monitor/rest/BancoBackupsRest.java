package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.monitor.bo.BancoDadosBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/bdBackups")
public class BancoBackupsRest {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<BancoBackup> getAlarmesNo(@QueryParam("id") Integer id, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		
		PagingTO<BancoBackup> retorno = new PagingTO<BancoBackup>();
		
		if(id != null){
		
			BancoDadosBO bo = new BancoDadosBO();
			List<BancoBackup> records = bo.getBackupsBancoLimit(id, start, limit);
			Long total = bo.contaBackupsBanco(id);
			
			retorno.setSuccess(true);
			retorno.setRecords(records);
			retorno.setTotal(total);
			
			return retorno;
			
			
		}else{
			retorno.setSuccess(false);			
		}
		
		return retorno;		
							
	} 
	
}
