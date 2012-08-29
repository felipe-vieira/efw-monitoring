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

	private NoDAO noDAO;
	private GenericDAO genericDAO;
	
	public NoBO(){
		this.noDAO = new NoDAO();
		this.genericDAO = new GenericDAO();
	}
	
	public List<No> listAllNos(){
		return this.noDAO.listAllNos();
	}

	public No getNoId(Integer id) {
		return this.noDAO.getNoById(id);
	}

	public ReturnTO saveNo(No no) {
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			if(no.getNome() == null || no.getNome().equals("")){
				retorno.setMessage("O campo nome é obrigatório.");
			}else if(no.getHostname() == null || no.getHostname().equals("")){
				retorno.setMessage("O campo hostname é obrigatório.");
			}else if(no.getAgentPort() == null){
				retorno.setMessage("O campo porta do agente é obrigatório");
			}else if(this.verificaNoNome(no)){
				retorno.setMessage("Ja existe um nó com esse nome.");
			}else if(this.verificaIpPortaTipo(no)){
				retorno.setMessage("Ja existe um nó com essa combinação de hostname, porta e tipo.");
			}else{
				
				no.setAtivo(true);
				
				if(no.getId() == null && no.getId() !=0){
					this.genericDAO.save(no);
				}else{
					this.genericDAO.update(no);
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
	
	/**
	 * Verifica se já existe um nó com esse nome.
	 * @param no
	 * @return
	 */
	public Boolean verificaNoNome(No no){
		Session session = this.genericDAO.getSession();
		
		try{
			Query query = session.createQuery("FROM No where nome = :nome");
			query.setString("nome",no.getNome());
			List<No> results = this.genericDAO.queryList(query);
			
			if(no.getId() != null && no.getId() != 0){
				No noOld = (No) this.genericDAO.getById(No.class, no.getId());
				
				if(noOld.getNome().equals(no.getNome())){
					return false;
				}
				
			}
			
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
		Session session = this.genericDAO.getSession();
		
		try{
			Query query = session.createQuery("FROM No where hostname = :hostname AND agentPort = :agentPort");
			query.setString("hostname",no.getHostname());
			query.setInteger("agentPort",no.getAgentPort());
			List<No> results = this.genericDAO.queryList(query);
			
			if(no.getId() != null && no.getId() != 0){
				No noOld = (No) this.genericDAO.getById(No.class, no.getId());
				
				if(noOld.getHostname().equals(no.getHostname()) && noOld.getAgentPort().equals(no.getAgentPort())){
					return false;
				}
				
			}
			
			if(results != null && results.size() > 0){
				for (No noAtual : results) {
					if(noAtual.getClass() == no.getClass()){
						return true;
					}					
				}
				
				return false;
				
			}else{
				return false;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	
}
