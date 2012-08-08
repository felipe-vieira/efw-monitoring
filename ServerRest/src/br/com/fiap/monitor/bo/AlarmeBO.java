package br.com.fiap.monitor.bo;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Alarme;
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
	
}
