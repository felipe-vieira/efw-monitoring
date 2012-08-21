package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
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

	public List<ServidorAplicacaoDeployment> getDeploymentsId(Integer id) {
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query q =session.createQuery("FROM ServidorAplicacaoDeployment WHERE servidorAplicacao.id = :id ORDER BY nome");
			q.setInteger("id", id);
			List<ServidorAplicacaoDeployment> deployments = (List<ServidorAplicacaoDeployment>) q.list();
			t.commit();
			return deployments;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}

}
