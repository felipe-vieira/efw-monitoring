package br.com.fiap.monitor.bo;

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
				retorno.setMessage("Tipo de avaliacão não pode ser nula");
			}else if(usuario == null){
				retorno.setMessage("Usuário inválido");
				t.rollback();
			}else if(solucao == null){
				retorno.setMessage("Solução inválida");
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
	
	
	
	
	
}
