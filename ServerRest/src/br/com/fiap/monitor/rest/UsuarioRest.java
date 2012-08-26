package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.monitor.bo.UsuarioBO;
import br.com.fiap.monitor.entities.Usuario;


@Path("/usuarios")
public class UsuarioRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Usuario> listUsuarios(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){

		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.listaUsuarios(start,limit);
		
	}	
}
