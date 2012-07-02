package br.com.fiap.coleta.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.util.dao.DBUtil;

public class MemoriaDAO {

	public void salvarMemoria(Memoria memoria){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.saveOrUpdate(memoria);
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
		
		
		
	}
	
	public Memoria pegaMemoriaServidor(Servidor servidor){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		Memoria retorno = null;
		
		
		try{
			retorno = (Memoria) session.get(Memoria.class,servidor.getId());
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
		
		return retorno;
	}
	
}
