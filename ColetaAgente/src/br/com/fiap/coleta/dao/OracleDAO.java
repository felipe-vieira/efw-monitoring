package br.com.fiap.coleta.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.util.dao.DBUtil;

public class OracleDAO {
	public Long pegaUltimoSetBackup(Oracle oracle) {
		
		Session session = DBUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		try{
			Query query = session.createQuery("SELECT max(setCount) as ultimoId FROM BancoBackup where bancoDados.id = :id");
			query.setLong("id",oracle.getId());
			Long retorno = (Long) query.uniqueResult();
			t.commit();
			
			if(retorno==null){
				return 0l;
			}else{
				return retorno;
			}			
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return 0l;
		}
		
	}
}
