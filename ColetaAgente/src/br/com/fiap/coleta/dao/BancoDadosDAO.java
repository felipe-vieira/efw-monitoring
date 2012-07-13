package br.com.fiap.coleta.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.coleta.entities.BancoJobColeta;
import br.com.fiap.coleta.util.dao.DBUtil;

public class BancoDadosDAO {

	public List<BancoFile> listaFilesBancoDados(BancoDados bd) {
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM BancoFile file where bancoDados.id = :bdId");
			query.setLong("id", bd.getId());
			return (List<BancoFile>) query.list(); 
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}

	public List<BancoJob> listaJobsBancoDados(BancoDados bd) {
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM BancoJob job files where bancoDados.id = :bdId");
			query.setLong("id", bd.getId());
			return (List<BancoJob>) query.list(); 
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}

	public BancoJobColeta pegaColetaJobLogId(BancoJob job, Long logId) {
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM BancoJobColeta coleta files where bancoJob.id = :jobId AND logId = :logId");
			query.setLong("jobId", job.getId());
			query.setLong("logId", logId);
			return (BancoJobColeta) query.uniqueResult();
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
		
	}

}
