package br.com.fiap.coleta.bo;

import br.com.fiap.coleta.dao.AgendamentoDAO;
import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.entities.No;

public class AgendamentoBO {

	private AgendamentoDAO agendamentoDAO;
	
	public AgendamentoBO(){
		this.agendamentoDAO = new AgendamentoDAO();
	}
	
	public Agendamento pegaAgendamentoNo(No no){
		return this.agendamentoDAO.pegaAgendamentoNo(no);
	}
	
	public Agendamento pegaAgendamento(Integer id){
		return this.agendamentoDAO.terminaAgendamento(id);
	}
	
	
}
