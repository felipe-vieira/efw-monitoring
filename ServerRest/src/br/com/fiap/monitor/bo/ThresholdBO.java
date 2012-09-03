package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.BancoDadosThreshold;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreshold;
import br.com.fiap.coleta.entities.ServidorThreshold;
import br.com.fiap.coleta.entities.Threshold;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;

public class ThresholdBO {

	private GenericDAO genericDAO;

	public ThresholdBO(){
		this.genericDAO = new GenericDAO();
	}
	
	
	public List<Threshold> listThresholdsLimit(Integer start, Integer limit){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM Threshold");
			
			query.setFirstResult(start);
			query.setMaxResults(limit);
			
			List<Threshold> thresholds = this.genericDAO.queryList(query);
		
			for (Threshold threshold : thresholds) {
				if(threshold instanceof ServidorThreshold){
					threshold.setTipo("Servidor");
				}else if(threshold instanceof ServidorAplicacaoThreshold){
					threshold.setTipo("Servidor de Aplicação");
				}else if(threshold instanceof BancoDadosThreshold){
					threshold.setTipo("Banco de Dados");
				}
			}
			
			t.commit();
			return thresholds;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	public Long contaThresholds(){

		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{			
			Query query = session.createQuery("SELECT count(*) FROM Threshold");
			
			Long retorno =  (Long) query.uniqueResult();
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public Threshold getById(Integer id){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		try{
			Threshold threshold = null; 
			
			if(id!=null){
				threshold = (Threshold) this.genericDAO.getById(Threshold.class, id);
			}
			
			t.commit();
			return threshold;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	
	public ReturnTO saveUpdateThreshold(Threshold threshold){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			if(threshold.getNome() == null || threshold.getNome().equals("")){
				retorno.setMessage("O nome do threshold é obrigatório");
			}else{
				
				if(threshold.getId() != null && threshold.getId() != 0){
					this.genericDAO.update(threshold);
				}else{
					this.genericDAO.save(threshold);
				}
				
				t.commit();
				retorno.setSuccess(true);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
		}
		
		return retorno;
	}

	public ReturnTO deleteThreshold(Threshold threshold){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			threshold = (Threshold) this.genericDAO.getById(Threshold.class, threshold.getId());
			Query query = null;
			
			if(threshold instanceof ServidorThreshold){
				query = session.createQuery("UPDATE Servidor set threshold = null WHERE threshold.id = :id");
			}else if(threshold instanceof ServidorAplicacaoThreshold){
				query = session.createQuery("UPDATE ServidorAplicacao set threshold = null WHERE threshold.id = :id");
			}else if(threshold instanceof BancoDadosThreshold){
				query = session.createQuery("UPDATE BancoDados set threshold = null WHERE threshold.id = :id");
			}
			
			if(query != null){
				query.setInteger("id", threshold.getId());
				query.executeUpdate();
			}
			
			this.genericDAO.delete(threshold);
			t.commit();
			retorno.setSuccess(true);
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
		}
		
		return retorno;
	}
	
	public List<ServidorThreshold> listaThresholdsServidor(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
	
		try{
			Query query = session.createQuery("FROM ServidorThreshold");
			List<ServidorThreshold> results = this.genericDAO.queryList(query);
			t.commit();
			
			return results;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	public List<ServidorAplicacaoThreshold> listaThresholdsServidorAplicacao(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
	
		try{
			Query query = session.createQuery("FROM ServidorAplicacaoThreshold");
			List<ServidorAplicacaoThreshold> results = this.genericDAO.queryList(query);
			t.commit();
			
			return results;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	public List<BancoDadosThreshold> listaThresholdsBancoDados(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
	
		try{
			Query query = session.createQuery("FROM BancoDadosThreshold");
			List<BancoDadosThreshold> results = this.genericDAO.queryList(query);
			t.commit();
			
			return results;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
}