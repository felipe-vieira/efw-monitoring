package br.com.fiap.coleta.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.util.dao.DBUtil;

public class AgendamentoDAO {
	
	/**
	 * Lista todos os agendamentos ativos
	 * @return Lista de Agendamentos
	 */
	public List<Agendamento> listaAgendamentosAtivos(){
		
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		List<Agendamento> listaAgendamentos = sessao
				.createQuery("SELECT agendamento FROM Agendamento AS agendamento WHERE ativo=:ativo")
				.setBoolean("ativo", true)
				.list();
		
		t.commit();
		
		return listaAgendamentos;
		
	}
	
	/**
	 * Lista todos os agendamentos
	 * @return Lista de Agendamentos
	 */
	public List<Agendamento> listaAgendamentosNovos(){
		
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		List<Agendamento> listaAgendamentos = sessao.createQuery("SELECT agendamento FROM Agendamento AS agendamento where agendado=:agendado and ativo=:ativo")
				.setBoolean("agendado",false)
				.setBoolean("ativo", true)
				.list();
		
		t.commit();
		
		return listaAgendamentos;
		
	}
	
	/**
	 *	Inicializa a tabela de agendamentos 
	 */
	public void initAgendamentos(){
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		String update = "UPDATE Agendamento a SET a.agendado = :agendado";
		sessao.createQuery(update)
			.setBoolean("agendado", false)
			.executeUpdate();
		
		t.commit();
		
	}
	
	/**
	 * Atualiza um agendamento...
	 * @param agendamento
	 */
	public void updateAgendamento(Agendamento agendamento){
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		sessao.update(agendamento);
		
		t.commit();
		
	}
	
	
}
