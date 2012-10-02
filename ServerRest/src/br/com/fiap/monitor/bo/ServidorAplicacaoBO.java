package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreshold;
import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;

public class ServidorAplicacaoBO {

	private GenericDAO genericDAO;
	private NoBO noBO;
	
	public ServidorAplicacaoBO(){
		this.genericDAO = new GenericDAO();
		this.noBO = new NoBO();
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
	
	public List<ServidorAplicacaoMemoria> getConfigMemorias(Integer id) {
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query q =session.createQuery("FROM ServidorAplicacaoMemoria WHERE servidorAplicacao.id = :id");
			q.setInteger("id", id);
			List<ServidorAplicacaoMemoria> memorias = (List<ServidorAplicacaoMemoria>) q.list();
			t.commit();
			return memorias;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}

	public ReturnTO saveGlassfish(Glassfish glassfish, Integer thresholdId, Long slaId) {
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			if(glassfish.getNome() == null || glassfish.getNome().equals("")){
				retorno.setMessage("O campo nome é obrigatório.");
			}else if(glassfish.getHostname() == null || glassfish.getHostname().equals("")){
				retorno.setMessage("O campo hostname é obrigatório.");
			}else if(glassfish.getAgentPort() == null){
				retorno.setMessage("O campo porta do agente é obrigatório");
			}else if(glassfish.getPort() == null){
				retorno.setMessage("O campo porta do HTTP é obrigatório");
			}else if(glassfish.getJmxPort() == null){
				retorno.setMessage("O campo porta do JMX é obrigatório");
			}else if(glassfish.getJmxUser() == null || glassfish.getJmxUser().equals("")){
				retorno.setMessage("O campo usuário do JMX é obrigatório.");
			}else if(glassfish.getJmxSenha() == null || glassfish.getJmxSenha().equals("")){
				retorno.setMessage("O campo senha do JMX  é obrigatório.");
			}else if(this.noBO.verificaNoNome(glassfish)){
				retorno.setMessage("Ja existe um nó com esse nome.");
			}else if(this.noBO.verificaIpPortaTipo(glassfish)){
				retorno.setMessage("Ja existe um nó com essa combinação de hostname, porta e tipo.");
			}else{
				
				glassfish.setAtivo(true);
				
				if(thresholdId != null && thresholdId != 0){
					ServidorAplicacaoThreshold threshold = (ServidorAplicacaoThreshold) this.genericDAO.getById(ServidorAplicacaoThreshold.class, thresholdId);
					glassfish.setThreshold(threshold);
				}else{
					glassfish.setThreshold(null);
				}
				
				if(slaId != null && slaId != 0){
					Sla sla = (Sla) this.genericDAO.getById(Sla.class, slaId);
					glassfish.setSla(sla);
				}else{
					glassfish.setSla(null);
				}
				
				if(glassfish.getId() == null && glassfish.getId() != 0){
					this.genericDAO.save(glassfish);
				}else{
					this.genericDAO.update(glassfish);
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
	
	public ReturnTO saveJBoss(JBoss jboss,  Integer thresholdId, Long slaId) {
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			if(jboss.getNome() == null || jboss.getNome().equals("")){
				retorno.setMessage("O campo nome é obrigatório.");
			}else if(jboss.getHostname() == null || jboss.getHostname().equals("")){
				retorno.setMessage("O campo hostname é obrigatório.");
			}else if(jboss.getAgentPort() == null){
				retorno.setMessage("O campo porta do agente é obrigatório");
			}else if(jboss.getPort() == null){
				retorno.setMessage("O campo porta do HTTP é obrigatório");
			}else if(jboss.getJmxPort() == null){
				retorno.setMessage("O campo porta do JMX é obrigatório");
			}else if(this.noBO.verificaNoNome(jboss)){
				retorno.setMessage("Ja existe um nó com esse nome.");
			}else if(this.noBO.verificaIpPortaTipo(jboss)){
				retorno.setMessage("Ja existe um nó com essa combinação de hostname, porta e tipo.");
			}else{
				
				jboss.setAtivo(true);
				
				if(thresholdId != null && thresholdId != 0){
					ServidorAplicacaoThreshold threshold = (ServidorAplicacaoThreshold) this.genericDAO.getById(ServidorAplicacaoThreshold.class, thresholdId);
					jboss.setThreshold(threshold);
				}else{
					jboss.setThreshold(null);
				}
				
				if(slaId != null && slaId != 0){
					Sla sla = (Sla) this.genericDAO.getById(Sla.class, slaId);
					jboss.setSla(sla);
				}else{
					jboss.setSla(null);
				}
				
				if(jboss.getId() == null && jboss.getId() != 0){
					this.genericDAO.save(jboss);
				}else{
					this.genericDAO.update(jboss);
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
	
	public List<ServidorAplicacao> listServidoresAplicacao(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
				
		try{
			Query query = session.createQuery("FROM ServidorAplicacao");
			
			List<ServidorAplicacao> servidores = this.genericDAO.queryList(query);
			t.commit();
			
			return servidores;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}	
	}

}


