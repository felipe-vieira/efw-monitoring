package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.monitor.bo.BancoDadosBO;

@Path("/bdFiles")
public class BancoFilesRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<BancoFile> getBancoJobs(@QueryParam("id") Integer id) {
		BancoDadosBO bo = new BancoDadosBO();

		if (id != null) {
			List<BancoFile> list = bo.getFilesBanco(id);
			return list;

		} else {
			return null;
		}

	}

}
