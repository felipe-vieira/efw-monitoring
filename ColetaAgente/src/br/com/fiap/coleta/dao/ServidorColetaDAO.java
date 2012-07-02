package br.com.fiap.coleta.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.MemoriaColeta;
import br.com.fiap.coleta.entities.ParticaoColeta;
import br.com.fiap.coleta.entities.ProcessadorColeta;
import br.com.fiap.coleta.util.dao.DBUtil;

public class ServidorColetaDAO {

	public void saveColetaMemoria(MemoriaColeta coleta) {
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.save(coleta);
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
	}

	public void saveColetaProcessador(ProcessadorColeta coleta) {
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		
		try{
			session.save(coleta);
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
	}

	public void saveListaColetaParticao(List<ParticaoColeta> listaParticaoColeta) {
		Session session = DBUtil.getCurrentSession();
		
		Transaction t = session.beginTransaction();
		
		try{
			for(ParticaoColeta coleta : listaParticaoColeta){
				session.save(coleta);
			}
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
		}
		
	}

}
