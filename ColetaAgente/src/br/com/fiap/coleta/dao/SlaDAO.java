package br.com.fiap.coleta.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.JanelaSla;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.coleta.entities.SlaCalculado;
import br.com.fiap.coleta.entities.enumerators.TipoSla;

public class SlaDAO extends GenericDAO{

	public List<No> listNosSla(Sla sla){
		
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		Query query = session.createQuery("FROM No where sla.id = :id and ativo = :ativo");
		query.setLong("id", sla.getId());
		query.setBoolean("ativo", true);
		
		List<No> lista =  (List<No>) query.list();
		t.commit();
		
		return lista;
		
	}
	
	public List<JanelaSla> listJanelasSla(Sla sla, Calendar dia){
		
		
		String strQuery = "FROM JanelaSla where sla.id = :id "+
						  " AND dataInicio <= :date "+
						  " AND (dataFim >= :date OR dataFim is null)"+
						  " AND ativo = :ativo";
				
				
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		Query query = session.createQuery(strQuery);
		
		
		query.setLong("id", sla.getId());
		query.setDate("date", dia.getTime());
		query.setBoolean("ativo", true);
		
		List<JanelaSla> janelas = (List<JanelaSla>) query.list();
		t.commit();
		
		return janelas;
		
	}
	
	/**
	 * Lista os SLAS não calculados referentes ao dia base
	 * @param dia base
	 * @return Lista com os SLAS
	 */
	public List<Sla> listSlaNaoRodados(Calendar dia){
		
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		Calendar calendar = Calendar.getInstance();
		
		Integer weekday = dia.get(Calendar.DAY_OF_WEEK);
		
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		
		String strQuery = "FROM Sla where (ultimaColeta is null OR ultimaColeta < :data) " +
				  	      " AND ativo = :ativo" +
				  	      " AND diasSemana.dia"+weekday+"=:dia";
		
		Query query = session.createQuery(strQuery);
		
		query.setDate("data",calendar.getTime());
		query.setBoolean("ativo",true);
		query.setBoolean("dia",true);
		
		List<Sla> lista = (List<Sla>) query.list();
		
		t.commit();
		
		return lista;
	}
	
	public List<Sla> listSlaNaoRodadosMes(Calendar dia) {
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		
		String strQuery = "FROM Sla where (ultimaColetaMes is null OR ultimaColetaMes < :data) " +
				  	      " AND ativo = :ativo";
		
		Query query = session.createQuery(strQuery);
		
		query.setDate("data",calendar.getTime());
		query.setBoolean("ativo",true);
		
		List<Sla> lista = (List<Sla>) query.list();
		
		t.commit();
		
		return lista;
	}
	
	public List<Indisponibilidade> listaIndisponibilidadesPeriodo(No no, Date dataInicio, Date dataFim){
		
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		StringBuilder queryStr  = new StringBuilder();
		
		queryStr.append(" FROM Indisponibilidade WHERE no.id = :id ");
		queryStr.append(" AND ");
		queryStr.append("     (");
		queryStr.append("      (inicio >= :dataInicio AND fim <= :dataFim)");
		queryStr.append("      OR  (inicio <= :dataFim AND fim is null)");
		queryStr.append("      OR  (inicio <= :dataInicio AND fim >= :dataInicio)");
		queryStr.append("      OR  (inicio <= :dataInicio AND fim >= :dataFim)");
		queryStr.append("     )");
		
		

		Query query = session.createQuery(queryStr.toString());
		query.setInteger("id", no.getId());
		query.setTimestamp("dataInicio", dataInicio);
		query.setTimestamp("dataFim", dataFim);
		 
		List<Indisponibilidade> lista = (List<Indisponibilidade>) query.list();
		
		t.commit();
		
		return lista;
		
	}

	public List<SlaCalculado> listSlasNoMes(No no, Calendar dataInicio, Calendar dataFim) {
		
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		String queryStr = " FROM SlaCalculado WHERE no.id = :id AND controle BETWEEN :dataInicio AND :dataFim AND tipo = :tipo";
		
		Query query = session.createQuery(queryStr.toString());
		query.setInteger("id", no.getId());
		query.setTimestamp("dataInicio", dataInicio.getTime());
		query.setTimestamp("dataFim", dataFim.getTime());
		query.setParameter("tipo", TipoSla.DIARIO);
		
		List<SlaCalculado> lista = (List<SlaCalculado>) query.list();
		
		t.commit();
		
		return lista;

	}
	
	public SlaCalculado getSlaNoMes(No no, Calendar dataInicio, Calendar dataFim) {
		
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		String queryStr = " FROM SlaCalculado WHERE no.id = :id AND controle BETWEEN :dataInicio AND :dataFim AND tipo = :tipo";
		
		Query query = session.createQuery(queryStr.toString());
		query.setInteger("id", no.getId());
		query.setTimestamp("dataInicio", dataInicio.getTime());
		query.setTimestamp("dataFim", dataFim.getTime());
		query.setParameter("tipo", TipoSla.MENSAL);
		
		SlaCalculado sla = (SlaCalculado) query.uniqueResult();
		
		t.commit();
		
		return sla;

	}


	
	
	
	
	
	
	
	
	
}
