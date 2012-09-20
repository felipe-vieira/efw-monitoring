package br.com.fiap.coleta.bo;

import java.util.List;

import br.com.fiap.coleta.dao.UsuarioDAO;
import br.com.fiap.coleta.entities.Usuario;

public class UsuarioBO {

	private UsuarioDAO usuarioDAO;
	
	public UsuarioBO(){
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public List<Usuario> listaUsuariosEmail(){
		return this.usuarioDAO.listaUsuariosEmail();
	}
}
