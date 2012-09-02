package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.BancoDadosThreshold;
import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.coleta.entities.Oracle;
import br.com.fiap.coleta.entities.SQLServer;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreshold;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;

public class BancoDadosBO {

	
	private GenericDAO genericDAO;
	
	private NoBO noBO;

	public BancoDadosBO(){
		this.genericDAO = new GenericDAO();
		this.noBO = new NoBO();
	}
	
	public List<BancoJob> getJobsBanco(Integer id){
		
		Session session = genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		
		try{
			Query query = session.createQuery("FROM BancoJob where bancoDados.id  = :id ORDER BY jobName");
			query.setInteger("id", id);
			List<BancoJob> list = genericDAO.queryList(query);
			t.commit();
			return list;		
			
		}catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	} 
	
	public List<BancoFile> getFilesBanco(Integer id){
		
		Session session = genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		
		try{
			Query query = session.createQuery("FROM BancoFile where bancoDados.id  = :id and ativo = :ativo ORDER BY file ");
			query.setInteger("id", id);
			query.setBoolean("ativo", true);
			List<BancoFile> list = genericDAO.queryList(query);
			t.commit();
			return list;		
			
		}catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	} 
	
	public List<BancoBackup> getBackupsBancoLimit(Integer id, Integer start, Integer limit){
		
		Session session = genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		
		try{
			Query query = session.createQuery("FROM BancoBackup where bancoDados.id  = :id ORDER BY backupStartDate desc");
			query.setInteger("id", id);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<BancoBackup> list = genericDAO.queryList(query);
			t.commit();
			return list;		
			
		}catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	
	public Long contaBackupsBanco(Integer id){

		Session session = genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{			
			Query query = session.createQuery("SELECT count(*) FROM BancoBackup where bancoDados.id = :id");
			query.setInteger("id", id);
			
			Long retorno =  (Long) query.uniqueResult();
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}

	public ReturnTO saveSQLServer(SQLServer sqlserver, Integer thresholdId) {
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			if(sqlserver.getNome() == null || sqlserver.getNome().equals("")){
				retorno.setMessage("O campo nome é obrigatório.");
			}else if(sqlserver.getHostname() == null || sqlserver.getHostname().equals("")){
				retorno.setMessage("O campo hostname é obrigatório.");
			}else if(sqlserver.getAgentPort() == null){
				retorno.setMessage("O campo porta do agente é obrigatório");
			}else if(sqlserver.getPort() == null){
				retorno.setMessage("O campo porta é obrigatório");
			}else if(sqlserver.getUsuario() == null || sqlserver.getUsuario().equals("")){
				retorno.setMessage("O campo usuário é obrigatório");
			}else if(sqlserver.getSenha() == null || sqlserver.getSenha().equals("")){
				retorno.setMessage("O campo senha é obrigatório");
			}else if(sqlserver.getInstanceName() == null || sqlserver.getInstanceName().equals("")){
				retorno.setMessage("O campo nome da instância é obrigatório");
			}else if(sqlserver.getDatabase() == null || sqlserver.getDatabase().equals("")){
				retorno.setMessage("O campo database é obrigatório");
			}else if(this.noBO.verificaNoNome(sqlserver)){
				retorno.setMessage("Ja existe um nó com esse nome.");
			}else if(this.noBO.verificaIpPortaTipo(sqlserver)){
				retorno.setMessage("Ja existe um nó com essa combinação de hostname, porta e tipo.");
			}else{
				
				sqlserver.setAtivo(true);
				
				if(thresholdId != null && thresholdId != 0){
					BancoDadosThreshold threshold = (BancoDadosThreshold) this.genericDAO.getById(BancoDadosThreshold.class, thresholdId);
					sqlserver.setThreshold(threshold);
				}else{
					sqlserver.setThreshold(null);
				}
				
				if(sqlserver.getId() == null){
					this.genericDAO.save(sqlserver);
				}else{
					this.genericDAO.update(sqlserver);
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
	
	public ReturnTO saveOracle(Oracle oracle, Integer thresholdId) {
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			if(oracle.getNome() == null || oracle.getNome().equals("")){
				retorno.setMessage("O campo nome é obrigatório.");
			}else if(oracle.getHostname() == null || oracle.getHostname().equals("")){
				retorno.setMessage("O campo hostname é obrigatório.");
			}else if(oracle.getAgentPort() == null){
				retorno.setMessage("O campo porta do agente é obrigatório");
			}else if(oracle.getPort() == null){
				retorno.setMessage("O campo porta é obrigatório");
			}else if(oracle.getUsuario() == null || oracle.getUsuario().equals("")){
				retorno.setMessage("O campo usuário é obrigatório");
			}else if(oracle.getSenha() == null || oracle.getSenha().equals("")){
				retorno.setMessage("O campo senha é obrigatório");
			}else if(oracle.getInstanceName() == null || oracle.getInstanceName().equals("")){
				retorno.setMessage("O campo nome da instância é obrigatório");
			}else if(this.noBO.verificaNoNome(oracle)){
				retorno.setMessage("Ja existe um nó com esse nome.");
			}else if(this.noBO.verificaIpPortaTipo(oracle)){
				retorno.setMessage("Ja existe um nó com essa combinação de hostname, porta e tipo.");
			}else{
				
				oracle.setAtivo(true);
				
				if(thresholdId != null && thresholdId != 0){
					BancoDadosThreshold threshold = (BancoDadosThreshold) this.genericDAO.getById(BancoDadosThreshold.class, thresholdId);
					oracle.setThreshold(threshold);
				}else{
					oracle.setThreshold(null);
				}
				
				if(oracle.getId() == null){
					this.genericDAO.save(oracle);
				}else{
					this.genericDAO.update(oracle);
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
	
	public List<BancoDados> listBancosDados(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
				
		try{
			Query query = session.createQuery("FROM BancoDados");
			
			List<BancoDados> bancos = this.genericDAO.queryList(query);
			t.commit();
			
			return bancos;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}	
	}
	
	
}
