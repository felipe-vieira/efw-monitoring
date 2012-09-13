package br.com.fiap.monitor.rest;

import java.util.List;

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

import br.com.fiap.coleta.entities.Usuario;
import br.com.fiap.monitor.bo.UsuarioBO;
import br.com.fiap.monitor.to.PagingTO;
import br.com.fiap.monitor.to.ReturnTO;


@Path("/usuarios")
public class UsuariosRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PagingTO<Usuario> listUsuarios(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit){

		UsuarioBO usuarioBO = new UsuarioBO();
		
		PagingTO<Usuario> paging = new PagingTO<Usuario>();
			
		List<Usuario> records = usuarioBO.listaUsuarios(start,limit);
		Long total = usuarioBO.contaUsuarios();
		
		paging.setSuccess(true);
		paging.setRecords(records);
		paging.setTotal(total);
		
		return paging;
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReturnTO createUsuario(Usuario usuario){
		
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.saveUsuario(usuario);
		
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO updateUsuario(Usuario usuario, @PathParam("id") Long id){
	
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.updateUsuario(id,usuario);
		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO deleteUsuario(Usuario usuario, @PathParam("id") Long id){
		
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.desativaUsuario(usuario);
		
	}
}
