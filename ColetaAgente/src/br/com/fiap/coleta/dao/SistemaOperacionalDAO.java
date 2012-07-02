package br.com.fiap.coleta.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.coleta.util.dao.DBUtil;

public class SistemaOperacionalDAO {

	public void salvarSistemaOperacional(SistemaOperacional sistemaOperacional){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.saveOrUpdate(sistemaOperacional);
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}		
	}
	
	public SistemaOperacional pegaSistemaOperacionalServidor(Servidor servidor){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		SistemaOperacional retorno = null;
		
		
		try{
			retorno = (SistemaOperacional) session.get(SistemaOperacional.class,servidor.getId());
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
		
		return retorno;
	}
	
	
}
