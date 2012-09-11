package br.com.fiap.monitor.bo;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.enumerators.StatusAlarme;
import br.com.fiap.coleta.entities.enumerators.TipoSla;
import br.com.fiap.monitor.dao.GenericDAO;

public class AlarmeBO {
	
	private GenericDAO dao;

	public AlarmeBO(){
		this.dao = new GenericDAO();
	}
	
	public List<Alarme> listaAlarmesNo(Integer id){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{			
			Query query = session.createQuery("FROM Alarme where no.id = :id");
			query.setInteger("id", id);
			
			List<Alarme> retorno =  query.list();
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<Alarme> listaLimitAlarmesNo(Integer id, Integer start, Integer max){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{			
			Query query = session.createQuery("FROM Alarme where no.id = :id ORDER BY data desc");
			query.setInteger("id", id);
			query.setFirstResult(start);
			query.setMaxResults(max);
			
			List<Alarme> retorno =  query.list();
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public Long contaAlarmesNos(Integer id){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{			
			Query query = session.createQuery("SELECT count(*) FROM Alarme where no.id = :id");
			query.setInteger("id", id);
			
			Long retorno =  (Long) query.uniqueResult();
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	
	public List<Alarme> listaLimitAlarmesNaoLidos(Integer start, Integer max){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM Alarme where status = :status ORDER BY data desc");
			query.setParameter("status", StatusAlarme.NAO_LIDO);
			query.setFirstResult(start);
			query.setMaxResults(max);
			
			List<Alarme> retorno = query.list();
			
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public Long contaAlarmesNaoLidos(){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("SELECT count(*) FROM Alarme where status = :status ORDER BY data desc");
			query.setParameter("status", StatusAlarme.NAO_LIDO);
			
			Long retorno = (Long) query.uniqueResult();
			t.commit();
			
			return retorno;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
}
