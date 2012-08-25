package br.com.fiap.coleta.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.util.dao.DBUtil;

public class IndisponibilidadeDAO {

	public Indisponibilidade pegaUltimaInstanciaIndisponibilidade(No no) {
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
			
		try{
			Query query = session.createQuery("FROM Indisponibilidade WHERE no.id = :noId and fim is null");
			query.setInteger("noId", no.getId());
			
			Indisponibilidade d = (Indisponibilidade) query.uniqueResult();
			t.commit();
			return d;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
		
		
	}
	
	
	
}
