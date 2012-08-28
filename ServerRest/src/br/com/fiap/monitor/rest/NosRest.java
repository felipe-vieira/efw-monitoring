package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.No;
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
			}else if (no instanceof ServidorAplicacao){
				no.setTipo("Servidor de Aplicacao");
			}else if (no instanceof BancoDados){
				no.setTipo("Banco de Dados");
			}else{
				no.setTipo("Desconhecido");
			}
		}
		
		return nos;
	}
	
}
