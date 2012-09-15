package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorThreshold;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;

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
	
	public List<Particao> getParticoesId(Integer id){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
				
		try{
			Query query = session.createQuery("FROM Particao where servidor.id = :id");
			
			query.setInteger("id", id);
			List<Particao> particoes = this.genericDAO.queryList(query);
			t.commit();
			
			return particoes;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}	
	}
	
	public List<Servidor> listServidores(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
				
		try{
			Query query = session.createQuery("FROM Servidor");
			
			List<Servidor> servidores = this.genericDAO.queryList(query);
			t.commit();
			
			return servidores;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}	
	}
	

	public ReturnTO saveServidor(Servidor servidor, Integer thresholdId, Long slaId) {
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			NoBO noBO = new NoBO();
			
			if(servidor.getNome() == null || servidor.getNome().equals("")){
				retorno.setMessage("O campo nome é obrigatório.");
			}else if(servidor.getHostname() == null || servidor.getHostname().equals("")){
				retorno.setMessage("O campo hostname é obrigatório.");
			}else if(servidor.getAgentPort() == null){
				retorno.setMessage("O campo porta do agente é obrigatório");
			}else if(noBO.verificaNoNome(servidor)){
				retorno.setMessage("Ja existe um nó com esse nome.");
			}else if(noBO.verificaIpPortaTipo(servidor)){
				retorno.setMessage("Ja existe um nó com essa combinação de hostname, porta e tipo.");
			}else{
				
				servidor.setAtivo(true);
				
				if(thresholdId != null && thresholdId != 0){
					ServidorThreshold threshold = (ServidorThreshold) this.genericDAO.getById(ServidorThreshold.class, thresholdId);
					servidor.setThreshold(threshold);
				}else{
					servidor.setThreshold(null);
				}
				
				if(slaId != null && slaId != 0){
					Sla sla = (Sla) this.genericDAO.getById(Sla.class, slaId);
					servidor.setSla(sla);
				}else{
					servidor.setSla(null);
				}
				
				
				if(servidor.getId() == null && servidor.getId() !=0){
					this.genericDAO.save(servidor);
				}else{
					this.genericDAO.update(servidor);
				}
				retorno.setSuccess(true);
				
			}
		
			t.commit();
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			
			retorno.setSuccess(false);
			retorno.setMessage(ex.getMessage());
		}
		
		return retorno;
		
	}
	
}
