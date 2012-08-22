package br.com.fiap.coleta.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.coleta.entities.enumerators.TipoMemoriaServidorAplicacao;
import br.com.fiap.coleta.util.dao.DBUtil;

public class ServidorAplicacaoDAO {

	public ServidorAplicacaoMemoria getMemoriaTipo(ServidorAplicacao servidor,
			TipoMemoriaServidorAplicacao tipo) {
		
		ServidorAplicacaoMemoria retorno = null;
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM ServidorAplicacaoMemoria where servidorAplicacao.id = :id AND tipo = :tipo ");
			query.setLong("id", servidor.getId());
			query.setParameter("tipo", tipo);
			retorno = (ServidorAplicacaoMemoria) query.uniqueResult();
			t.commit();
			return retorno;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<ServidorAplicacaoDeployment> getDeploymentsServidor(ServidorAplicacao sa) {
		
		List<ServidorAplicacaoDeployment> retorno = null;
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM ServidorAplicacaoDeployment where servidorAplicacao.id = :id");
			query.setLong("id", sa.getId());
			retorno = (List<ServidorAplicacaoDeployment>) query.list();
			t.commit();
			
			return retorno;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<ServidorAplicacaoMemoria> getConfigMemorias(ServidorAplicacao sa) {
		
		List<ServidorAplicacaoMemoria> retorno = null;
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM ServidorAplicacaoMemoria where servidorAplicacao.id = :id");
			query.setLong("id", sa.getId());
			retorno = (List<ServidorAplicacaoMemoria>) query.list();
			t.commit();
			
			return retorno;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}


	

	
	
}
