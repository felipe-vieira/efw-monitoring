package br.com.fiap.monitor.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.monitor.entities.Usuario;
import br.com.fiap.monitor.util.dao.DBUtil;

public class UsuarioDAO {

	public Usuario validaUsuario(String login, String senha) {
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM Usuario WHERE login = :login and senha = :senha and ativo = :ativo");
			query.setString("login", login);
			query.setString("senha", senha);
			query.setBoolean("ativo", true);
			
			Usuario u = (Usuario) query.uniqueResult();
			t.commit();
			return u;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
		}
		return null;
	}
	
}
