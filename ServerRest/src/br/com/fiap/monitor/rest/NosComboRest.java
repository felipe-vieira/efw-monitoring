package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.monitor.bo.NoBO;
import br.com.fiap.monitor.to.ReturnTO;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;

@Path("/nosCombo")
public class NosComboRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<No> listNos(){
		NoBO bo = new NoBO();
		List<No> nos = bo.listAllNos();
				
		return nos;
	}

}
