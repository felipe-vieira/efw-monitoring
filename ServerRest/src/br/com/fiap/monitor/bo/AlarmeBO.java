package br.com.fiap.monitor.bo;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.Solucao;
import br.com.fiap.coleta.entities.enumerators.StatusAlarme;
import br.com.fiap.coleta.entities.enumerators.TipoSla;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;

public class AlarmeBO {
	
	private GenericDAO dao;

	public AlarmeBO(){
		this.dao = new GenericDAO();
	}
	
	
	public Alarme getAlarmeId(Long id){
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{

			Alarme alarme = (Alarme) session.get(Alarme.class, id);
			t.commit();
			
			return alarme;
			
		}catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return null;
		}
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
			
			List<Alarme> retorno =  (List<Alarme>) query.list();
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
	
	public ReturnTO updateAlarme(Long id, Alarme alarme){
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		retorno.setId(id);
		
		try{
			
			Alarme original = (Alarme) session.get(Alarme.class,id);
			
			Boolean dirty = false;
			
			if(alarme.getStatus() != null && !alarme.getStatus().equals(StatusAlarme.NAO_LIDO)){
				original.setStatus(alarme.getStatus());
				dirty = true;
			}
			
			if(dirty){
				session.merge(original);
				t.commit();
			}else{
				t.rollback();
			}
			
			retorno.setObj(original);
			retorno.setSuccess(true);
			
		}catch (Exception ex) {
			
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
			
		}
		
		return retorno;
	}
	
	public void updateSolucaoAlarme(Alarme alarme, Solucao solucao) throws Exception{
		Session session = dao.getSession();
		
		alarme.setSolucao(solucao);
		session.merge(alarme);
		
	}
	
	
	
	
}
