package br.com.fiap.monitor.bo;

import br.com.fiap.monitor.dao.UsuarioDAO;
import br.com.fiap.monitor.entities.Usuario;

public class UsuarioBO {

	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBO(){
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public Usuario validaUsuario(String login, String senha) {
		return this.usuarioDAO.validaUsuario(login,senha);
	}

}
