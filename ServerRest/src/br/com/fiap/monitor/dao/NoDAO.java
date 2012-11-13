package br.com.fiap.monitor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.No;
import br.com.fiap.monitor.util.dao.DBUtil;

public class NoDAO {
	
	public List<No> listAllNos(Integer start, Integer limit){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("from No where ativo = :ativo ORDER BY nome");
			query.setBoolean("ativo", true);
			
			if(start != null && limit != null){
				query.setFirstResult(start);
				query.setMaxResults(limit);
			}
			
			List<No> retorno = query.list();
			t.commit();
			return retorno;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
		
	}
	
	public No getNoById(Integer id){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			No no = (No) session.get(No.class, id);
			t.commit();
			return no;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}

	public void saveOrUpdateNo(No no) {
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.saveOrUpdate(no);
			t.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
		}
	}

	
}
