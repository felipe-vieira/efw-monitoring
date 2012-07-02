package br.com.fiap.coleta.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.util.dao.DBUtil;

public class ProcessadorDAO {

	public void salvarProcessador(Processador processador){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.saveOrUpdate(processador);
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
		
		
		
	}
	
	
	public Processador pegaProcessadorServidor(Servidor servidor){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		Processador retorno = null;
		
		
		try{
			retorno = (Processador) session.get(Processador.class,servidor.getId());
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
		
		return retorno;
	}
}
