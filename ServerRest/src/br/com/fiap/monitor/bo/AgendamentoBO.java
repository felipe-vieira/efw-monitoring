package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.ReturnTO;

public class AgendamentoBO {

	private GenericDAO genericDAO;

	public AgendamentoBO(){
		this.genericDAO = new GenericDAO();
	}
	
	public List<Agendamento> listAgendamentoLimit(Integer start, Integer limit){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM Agendamento WHERE ativo = :ativo");
			query.setBoolean("ativo", true);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<Agendamento> retorno = this.genericDAO.queryList(query);
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}

	public Long countAgendamentos(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("SELECT count(*) FROM Agendamento WHERE ativo = :ativo");
			query.setBoolean("ativo", true);
			Long retorno = (Long) query.uniqueResult();
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public ReturnTO saveUpdateAgendamento(Agendamento agendamento, Integer noId){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			if(noId == null || noId == 0){
				retorno.setMessage("Escolha um nó para esse agendamento");
			}else if (agendamento.getHoraInicio() == null || agendamento.getHoraInicio().equals("")){
				retorno.setMessage("O campo Hora Inicio é obrigatório");	
			}else if (agendamento.getHoraFim() == null || agendamento.getHoraFim().equals("")){
				retorno.setMessage("O campo Hora Fim é obrigatório");	
			}else if (agendamento.getIntervalo() == null){
				retorno.setMessage("O campo Intervalo é obrigatório");
			}else if (agendamento.getIntervalo() < 1){
				retorno.setMessage("O intervalo minimo é de 1 minuto.");
			}else if (!this.validaHora(agendamento.getHoraInicio())){
				retorno.setMessage("Hora Inicio: Hora inválida.");
			}else if (!this.validaHora(agendamento.getHoraFim())){
				retorno.setMessage("Hora Fim: Hora inválida.");
			}else{
				
				No no = (No) this.genericDAO.getById(No.class, noId);
				
				if(no == null){
					retorno.setMessage("Nó não existe");
				}else{
					
					agendamento.setAtivo(true);
					agendamento.setNo(no);
					
					if(agendamento.getId() == null || agendamento.getId() == 0){
						this.genericDAO.save(agendamento);
					}else{
						this.genericDAO.update(agendamento);
					}
					
					retorno.setSuccess(true);
				}
			}
			
			t.commit();
			
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
		}
		
		return retorno;
		
	}
	
	
	public ReturnTO deletaAgendamento(Agendamento agendamento){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			agendamento.setAtivo(false);
			this.genericDAO.update(agendamento);
			
			t.commit();
			
			retorno.setSuccess(true);
			
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
		}
		
		return retorno;
		
	}
	
	public Boolean validaHora(String strHora){
		String[] split = strHora.split(":");
		
		if(split.length != 2){
			return false;
		}
		
		try{
			Integer hora = Integer.parseInt(split[0]);
			Integer minuto = Integer.parseInt(split[1]);
			
			if(hora < 0 || hora > 23){
				return false;
			}
			
			if(minuto < 0 || minuto > 59){
				return false;
			}
			
		}catch(NumberFormatException ex){
			return false;
		}
		
		return true;
		
	}
	
	
}
