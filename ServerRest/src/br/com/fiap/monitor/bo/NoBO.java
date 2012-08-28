package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.dao.NoDAO;
import br.com.fiap.monitor.to.ReturnTO;
import br.com.fiap.coleta.entities.No;

public class NoBO {

	private NoDAO noDao;
	private GenericDAO genericDao;
	
	public NoBO(){
		this.noDao = new NoDAO();
		this.genericDao = new GenericDAO();
	}
	
	public List<No> listAllNos(){
		return this.noDao.listAllNos();
	}

	public No getNoId(Integer id) {
		return this.noDao.getNoById(id);
	}

	public ReturnTO saveNo(No no) {
		
		Session session = this.genericDao.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		
		try{
			if(this.verificaNoNome(no)){
				retorno.setSuccess(false);
				retorno.setMessage("Ja existe um nó com esse nome.");
				
			}else if(this.verificaIpPortaTipo(no)){
				retorno.setSuccess(false);
				retorno.setMessage("Ja existe um nó com essa combinação de hostname, porta e tipo.");
			}else{
				no.setAtivo(true);
				session.save(no);
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
	
	/**
	 * Verifica se já existe um nó com esse nome.
	 * @param no
	 * @return
	 */
	public Boolean verificaNoNome(No no){
		Session session = this.genericDao.getSession();
		
		try{
			Query query = session.createQuery("FROM No where nome = :nome");
			query.setString("nome",no.getNome());
			List<No> results = this.genericDao.queryList(query);
			
			if(results != null && results.size() > 0){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	

	/**
	 * Verifica se já existe um nó que tenha a mesma combinação de Ip,Porta e tipo
	 * @param no
	 * @return
	 */
	public Boolean verificaIpPortaTipo(No no){
		Session session = this.genericDao.getSession();
		
		try{
			Query query = session.createQuery("FROM No where hostname = :hostname AND agentPort = :agentPort");
			query.setString("hostname",no.getHostname());
			query.setInteger("agentPort",no.getAgentPort());
			List<No> results = this.genericDao.queryList(query);
			
			if(results != null && results.size() > 0){
				Boolean retorno = false;
				
				for (No noAtual : results) {
					if(noAtual.getClass() == no.getClass()){
						retorno = true;
					}					
				}
				
				return retorno;
			}else{
				return false;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	
}
