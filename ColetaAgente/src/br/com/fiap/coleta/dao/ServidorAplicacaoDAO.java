package br.com.fiap.coleta.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.util.dao.DBUtil;

public class ServidorAplicacaoDAO {

	public void updateServidorAplicacao(ServidorAplicacao servidorAplicacao) {
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
	
		session.update(servidorAplicacao);
		t.commit();
		
	}

	
	
}
