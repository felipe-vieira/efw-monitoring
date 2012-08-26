package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.monitor.dao.GenericDAO;

public class BancoDadosBO {

	
	private GenericDAO dao;

	public BancoDadosBO(){
		this.dao = new GenericDAO();
	}
	
	public List<BancoJob> getJobsBanco(Integer id){
		
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		
		try{
			Query query = session.createQuery("FROM BancoJob where bancoDados.id  = :id ORDER BY jobName");
			query.setInteger("id", id);
			List<BancoJob> list = dao.queryList(query);
			t.commit();
			return list;		
			
		}catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	} 
	
	public List<BancoFile> getFilesBanco(Integer id){
		
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		
		try{
			Query query = session.createQuery("FROM BancoFile where bancoDados.id  = :id and ativo = :ativo ORDER BY file ");
			query.setInteger("id", id);
			query.setBoolean("ativo", true);
			List<BancoFile> list = dao.queryList(query);
			t.commit();
			return list;		
			
		}catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	} 
	
	public List<BancoBackup> getBackupsBancoLimit(Integer id, Integer start, Integer limit){
		
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		
		try{
			Query query = session.createQuery("FROM BancoBackup where bancoDados.id  = :id ORDER BY backupStartDate desc");
			query.setInteger("id", id);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<BancoBackup> list = dao.queryList(query);
			t.commit();
			return list;		
			
		}catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	
	public Long contaBackupsBanco(Integer id){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{			
			Query query = session.createQuery("SELECT count(*) FROM BancoBackup where bancoDados.id = :id");
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
	
	
}
