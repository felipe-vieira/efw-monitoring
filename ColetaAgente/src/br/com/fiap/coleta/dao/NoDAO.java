package br.com.fiap.coleta.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.util.dao.DBUtil;

public class NoDAO {
	
	public No getNoById(Integer id){
		No no = null;
		try{
			Session sessao = DBUtil.getCurrentSession();
			Transaction t = sessao.beginTransaction();
			
		
			 no = (No) sessao.get(No.class, id);
			
			t.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return no;
		
	}	
	
	
}
