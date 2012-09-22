package br.com.fiap.monitor.bo;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.MemoriaColeta;
import br.com.fiap.monitor.dao.GenericDAO;

public class MetricasBO {

	
	private GenericDAO genericDAO;

	public MetricasBO(){
		this.genericDAO = new GenericDAO();
	}
	
	public List<MemoriaColeta> listMemoriaColeta(Long idNo, Date inicio, Date fim){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM MemoriaColeta where memoria.id = :idNo AND (dataColeta BETWEEN :inicio AND :fim )");
			
			query.setLong("idNo", idNo);
			query.setTimestamp("inicio", inicio);
			query.setTimestamp("fim", fim);
			
			List<MemoriaColeta> lista = this.genericDAO.queryList(query);
			
			t.commit();
			
			return lista;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
}
