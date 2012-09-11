package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/naoGerenciaveis")
public class NaoGerenciaveisRest {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<No> listNos(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){
		
		PagingTO<No> retorno = new PagingTO<No>();
		
		NoBO bo = new NoBO();
		List<No> records = bo.listaNosNaoGerenciaveis(start, limit);
		Long total = bo.contaNosNaoGerenciaveis();
		
		if(records != null && records.size()>0){
			for(No no: records){
				if(no instanceof Servidor){
					no.setTipo("Servidor");
					no.setSubTipo("");
				}else if (no instanceof ServidorAplicacao){
					no.setTipo("Servidor de Aplicacao");
					
					if(no instanceof Glassfish){
						no.setSubTipo("Glassfish");
					}else if(no instanceof JBoss){
						no.setSubTipo("JBoss");
					}else{
						no.setSubTipo("");
					}
					
				}else if (no instanceof BancoDados){
					no.setTipo("Banco de Dados");
					
					if(no instanceof Oracle){
						no.setSubTipo("Oracle");
					}else if(no instanceof SQLServer){
						no.setSubTipo("SQL Server");
					}else{
						no.setSubTipo("");
					}
					
				}else{
					no.setTipo("Desconhecido");
				}
			}
		}
		
		retorno.setRecords(records);
		retorno.setTotal(total);
		retorno.setSuccess(true);
		
		return retorno;
	}
	
}
