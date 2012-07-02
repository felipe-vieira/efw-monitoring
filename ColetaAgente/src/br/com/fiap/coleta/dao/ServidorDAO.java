package br.com.fiap.coleta.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.util.dao.DBUtil;

public class ServidorDAO {

	public void updateServidor(Servidor servidor){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		session.update(servidor);
		
		t.commit();
	}
	
}
