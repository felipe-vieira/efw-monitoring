package br.com.fiap.coleta.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.util.dao.DBUtil;

public class ParticaoDAO {
	
	public void salvarParticao(Particao particao){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.saveOrUpdate(particao);
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
	}
	
	
	public void salvaListaParticoes(List<Particao> listParticao){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			for (Particao particao : listParticao) {
				if(particao.getId() == null){
					session.save(particao);
				}else{
					session.merge(particao);
				}
			}
			
			t.commit();
			
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
	}
	
	public Particao pegaParticaoServidor(Servidor servidor, String nome){
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		Particao particao = null;
		
		try{
			List<Particao> results =  session.createQuery("SELECT particao from Particao particao where servidor.id = :servidor and nome = :nome")
				.setInteger("servidor", servidor.getId())
				.setString("nome",nome)
				.list();
			
			if(results != null && results.size() > 0){
				particao = results.get(0);
			}
			
			t.commit();
			
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
		
		return particao;
	}
}
