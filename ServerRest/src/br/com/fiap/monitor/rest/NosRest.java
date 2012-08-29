package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;

@Path("/nos")
public class NosRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<No> listNos(){
		NoBO bo = new NoBO();
		List<No> nos = bo.listAllNos();
		
		for(No no: nos){
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
		
		return nos;
	}
	
}
