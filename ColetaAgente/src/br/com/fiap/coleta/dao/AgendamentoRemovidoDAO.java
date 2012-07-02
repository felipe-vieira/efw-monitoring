package br.com.fiap.coleta.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.entities.AgendamentoRemovido;
import br.com.fiap.coleta.util.dao.DBUtil;

public class AgendamentoRemovidoDAO {

	public List<AgendamentoRemovido> listaAgendamentosRemovidos(){
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		String hql = "SELECT agendamento from AgendamentoRemovido as agendamento WHERE status = :status";
		
		List<AgendamentoRemovido> agendamentos = sessao
				.createQuery(hql)
				.setBoolean("status", false)
				.list();
		
		t.commit();
		
		return agendamentos;
		
	}
	
	public void updateAgendamentoRemovido(AgendamentoRemovido agendamento){
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		sessao.update(agendamento);
		
		t.commit();
	}
	
	/**
	 * Pega o grupo do job marcado para remoção 
	 * @param agendamento
	 * @return String com o nome do grupo
	 */
	public String pegaGrupoAgendamentoRemovido(Integer id)
	{
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		AgendamentoRemovido agendamento = (AgendamentoRemovido) sessao.get(AgendamentoRemovido.class,id);
		
		String retorno = String.valueOf(agendamento.getAgendamento().getNo().getId());
		
		t.commit();
		
		return retorno;
		
	}
	
	/**
	 * Pega o objeto agendamento do job marcado para remoção 
	 * @param agendamento
	 * @return String com o nome do grupo
	 */
	public Agendamento pegaAgendamentoAgendamentoRemovido(Integer id)
	{
		Session sessao = DBUtil.getCurrentSession();
		Transaction t = sessao.beginTransaction();
		
		AgendamentoRemovido agendamentoRemovido = (AgendamentoRemovido) sessao.get(AgendamentoRemovido.class,id);
		
		//previnir lazy exception >_> preciso pesquisar uma forma melhor
		Agendamento retorno = (Agendamento) sessao.get(Agendamento.class,agendamentoRemovido.getAgendamento().getId());
		
		t.commit();
		
		return retorno;
		
	}
	
	
}
