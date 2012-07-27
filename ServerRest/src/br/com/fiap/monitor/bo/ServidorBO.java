package br.com.fiap.monitor.bo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.monitor.dao.GenericDAO;

public class ServidorBO {

	private GenericDAO genericDAO;
	
	public ServidorBO(){
		this.genericDAO = new GenericDAO();
	}
	
	public Servidor getServidorId(Integer id){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Servidor servidor = (Servidor) this.genericDAO.getById(No.class, id);
			t.commit();
			return servidor;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public SistemaOperacional getSistemaOperacionalId(Integer id){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
				
		try{
			SistemaOperacional so = (SistemaOperacional) this.genericDAO.getById(SistemaOperacional.class, id);
			t.commit();
			return so;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}	
	}
	
	public Memoria getMemoriaId(Integer id){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
				
		try{
			Memoria memoria = (Memoria) this.genericDAO.getById(Memoria.class, id);
			t.commit();
			return memoria;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}	
	}
	
	public Processador getProcessadorId(Integer id){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
				
		try{
			Processador processador = (Processador) this.genericDAO.getById(Processador.class, id);
			t.commit();
			return processador;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}	
	}
	
}
