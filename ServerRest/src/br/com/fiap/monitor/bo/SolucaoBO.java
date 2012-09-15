package br.com.fiap.monitor.bo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Solucao;
import br.com.fiap.coleta.entities.TipoAlarme;
import br.com.fiap.coleta.entities.Usuario;
import br.com.fiap.coleta.entities.enumerators.SubTipoNo;
import br.com.fiap.coleta.entities.enumerators.TipoNo;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;

public class SolucaoBO {
	
	private NoBO noBO;
	
	private GenericDAO genericDAO;
	
	private AlarmeBO alarmeBO;
	
	public SolucaoBO(){
		this.genericDAO = new GenericDAO();
		this.noBO = new NoBO();
		this.alarmeBO = new AlarmeBO();
	}
	
	public ReturnTO saveSolucao(Solucao solucao, Long idAlarme, Long idUsuario ){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			Alarme alarme = (Alarme) this.genericDAO.getById(Alarme.class, idAlarme);
			Usuario usuario = (Usuario) this.genericDAO.getById(Usuario.class, idUsuario);
			
			if (alarme == null){
				retorno.setMessage("Alarme inválido");
				t.rollback();
			}else if(usuario == null){
				retorno.setMessage("Usuário inválido.");
			}else if(solucao.getDescricao() == null || solucao.getDescricao().equals("")){
				retorno.setMessage("O campo descrição é obrigatório");
				t.rollback();
			}else if(solucao.getTitulo() == null || solucao.getTitulo().equals("")){
				retorno.setMessage("O campo titulo é obrigatório");
				t.rollback();
			}else{
				No no = alarme.getNo();
				TipoAlarme tipoAlarme = alarme.getTipo();
				
				TipoNo tipo = this.noBO.verificaTipoNo(no);
				SubTipoNo subTipo = this.noBO.verificaSubTipoNo(no);
				Date data = new Date();
				
				solucao.setData(data);
				solucao.setNo(no);
				solucao.setTipoAlarme(tipoAlarme);
				solucao.setUsuario(usuario);
				solucao.setTipo(tipo);
				solucao.setSubTipo(subTipo);
				
				this.genericDAO.save(solucao);
				
				this.alarmeBO.updateSolucaoAlarme(alarme, solucao);
				
				t.commit();
				retorno.setSuccess(true);
			}
			
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			
		}
		
		return retorno;
		
	}
	
}
