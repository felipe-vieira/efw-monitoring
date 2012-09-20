package br.com.fiap.coleta.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.Usuario;
import br.com.fiap.coleta.util.dao.DBUtil;

public class UsuarioDAO {

	public List<Usuario> listaUsuariosEmail(){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		
		try{
			
			Query query = session.createQuery("FROM Usuario WHERE enviarEmail = :enviarEmail " +
					" AND ativo = :ativo");
			
			
			query.setBoolean("enviarEmail", true);
			query.setBoolean("ativo", true);
			
			List<Usuario> lista = query.list();
			t.commit();
			
			return lista;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
}
