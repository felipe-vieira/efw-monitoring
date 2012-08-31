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
	
	
	public List<Threshold> listAllThresholds(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM Threshold");
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
	
	public Threshold getById(Integer id){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		try{
			Threshold threshold = null; 
			
			if(id!=null){
				this.genericDAO.getById(Threshold.class, id);
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
}