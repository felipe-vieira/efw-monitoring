package br.com.fiap.monitor.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import br.com.fiap.monitor.bo.UsuarioBO;
import br.com.fiap.monitor.entities.Usuario;


@Path("/usuarios")
public class UsuariosRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getLogin(@QueryParam("login") String login, @QueryParam("senha") String senha){

		UsuarioBO usuarioBO = new UsuarioBO();
		
		Usuario usuario = usuarioBO.validaUsuario(login,senha);
		
		
		//Tem que fazer isso pq o ext não aceita sem um root.
		JSONObject json = new JSONObject();
		json.put("usuario", usuario);
		
		return json.toString();
		
	}
	
}
