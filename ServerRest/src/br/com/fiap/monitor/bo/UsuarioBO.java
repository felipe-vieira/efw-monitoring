package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.dao.UsuarioDAO;
import br.com.fiap.monitor.entities.Usuario;

public class UsuarioBO {

	
	private UsuarioDAO usuarioDAO;
	private GenericDAO dao;
	
	public UsuarioBO(){
		this.usuarioDAO = new UsuarioDAO();
		this.dao = new GenericDAO();
	}
	
	public Usuario validaUsuario(String login, String senha) {
		return this.usuarioDAO.validaUsuario(login,senha);
	}
	
	public List<Usuario> listaUsuarios(Integer start, Integer limit){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM Usuario WHERE ativo = :ativo");
			query.setBoolean("ativo", true);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<Usuario> list = dao.queryList(query);
			t.commit();
			return list;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}

}
