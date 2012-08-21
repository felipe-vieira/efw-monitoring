package br.com.fiap.monitor.bo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.monitor.dao.GenericDAO;

public class ServidorAplicacaoBO {

	private GenericDAO genericDAO;
	
	public ServidorAplicacaoBO(){
		this.genericDAO = new GenericDAO();
	}

	public ServidorAplicacao getServidorAplicacaoId(Integer id) {
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			ServidorAplicacao sa = (ServidorAplicacao) this.genericDAO.getById(No.class, id);
			t.commit();
			return sa;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}

}
