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
			query.setLong("bdId", bd.getId());
			
			List<BancoFile> lista =  (List<BancoFile>) query.list();
			t.commit();
			
			return lista;
			
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
			Query query = session.createQuery("FROM BancoJob job where bancoDados.id = :bdId");
			query.setLong("bdId", bd.getId());
			
			List<BancoJob> lista = (List<BancoJob>) query.list(); 
			t.commit();
			
			return lista;
			
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
			Query query = session.createQuery("FROM BancoJobColeta coleta where bancoJob.id = :jobId AND logId = :logId");
			query.setLong("jobId", job.getId());
			query.setLong("logId", logId);
			
			BancoJobColeta coleta = (BancoJobColeta) query.uniqueResult(); 
			t.commit();
			
			return coleta;
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
		
	}

}
