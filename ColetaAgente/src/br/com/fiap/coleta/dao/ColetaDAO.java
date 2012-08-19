package br.com.fiap.coleta.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.util.dao.DBUtil;

public class ColetaDAO {
	
	public void salvaColeta(Object obj){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.saveOrUpdate(obj);
			t.commit();
		}catch(Exception e){
			e.printStackTrace();
			t.rollback();
		}
			
	}
	
	
	public void deletaColeta(Object obj){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.delete(obj);
			t.commit();
		}catch(Exception e){
			e.printStackTrace();
			t.rollback();
		}
			
	}
	
	
	public <E> void salvaListaColetas(List<E> listObj){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		try{
			for (Object obj : listObj) {
				session.saveOrUpdate(obj);
			}
			t.commit();
		}catch(Exception e){
			e.printStackTrace();
			t.rollback();
		}
	}
	
	public void updateColeta(Object obj){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.update(obj);
			t.commit();
		}catch(Exception e){
			e.printStackTrace();
			t.rollback();
		}
			
	}
	
	public <K,V> void salvaMapColeta(Map<K,V> map){
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		try{
			Set<K> keys = map.keySet();
			
			for(K key:keys){
				V obj = map.get(key);
				session.saveOrUpdate(obj);
			}	
			
			t.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			t.rollback();
		}

	}
	
	public <E> List<E> listAll(Class<E> classe){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			List<E> lista = session.createQuery("FROM "+classe.getName()).list();
			t.commit();
			return lista;
			
		}catch(Exception e){
			e.printStackTrace();
			t.rollback();
			return null;
		}
	}

	
}
