package br.com.fiap.monitor.bo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.AvaliacaoSolucao;
import br.com.fiap.coleta.entities.Solucao;
import br.com.fiap.coleta.entities.Usuario;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;


public class AvaliacaoSolucaoBO {
	
	private GenericDAO genericDAO;
	
	public AvaliacaoSolucaoBO(){
		this.genericDAO = new GenericDAO();
	}
	
	public ReturnTO salvaAvaliacaoSolucao(AvaliacaoSolucao avaliacao, Long idUsuario, Long idSolucao){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			Usuario usuario = (Usuario) this.genericDAO.getById(Usuario.class, idUsuario);
			Solucao solucao = (Solucao) this.genericDAO.getById(Solucao.class, idSolucao);
			
			if(avaliacao.getTipoAvaliacao() == null){
				retorno.setMessage("Tipo de avaliac�o n�o pode ser nula");
			}else if(usuario == null){
				retorno.setMessage("Usu�rio inv�lido");
				t.rollback();
			}else if(solucao == null){
				retorno.setMessage("Solu��o inv�lida");
				t.rollback();
			}else{
				
				Integer valor = avaliacao.getTipoAvaliacao().getValue();
				
				avaliacao.setAvaliacao(valor);
				avaliacao.setUsuario(usuario);
				avaliacao.setSolucao(solucao);
				
				this.genericDAO.save(avaliacao);
				
				t.commit();
				retorno.setSuccess(true);			
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
		}
		
		return retorno;
		
	}
	
	
	public AvaliacaoSolucao pegaAvaliacaoUsuario(Long idUsuario, Long idSolucao){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM AvaliacaoSolucao where usuario.id = :idUsuario and solucao.id = :idSolucao");
			query.setLong("idUsuario", idUsuario);
			query.setLong("idSolucao", idSolucao);
				
			AvaliacaoSolucao avaliacao = (AvaliacaoSolucao) query.uniqueResult();
			t.commit();
			return avaliacao;
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}

	public ReturnTO deletaAvaliacao(Long id){

		Session session =  this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{

			AvaliacaoSolucao avaliacao = (AvaliacaoSolucao) session.get(AvaliacaoSolucao.class, id);
			if(avaliacao != null){
				session.delete(avaliacao);
			}
			
			t.commit();
			retorno.setSuccess(true);
			
		}catch(Exception ex){

			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
		}

		return retorno;
		
	}
	
	
	
}
