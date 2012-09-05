package br.com.fiap.coleta.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.JanelaSla;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Sla;

public class SlaDAO extends GenericDAO{

	public List<No> listNosSla(Sla sla){
		
		Session session = this.getSession();
		Query query = session.createQuery("FROM No where sla.id = :id");
		query.setLong("id", sla.getId());
		
		return (List<No>) query.list();
		
	}
	
	public List<JanelaSla> listJanelasSla(Sla sla, Calendar dia){
		
		
		String strQuery = "FROM JanelaSla where sla.id = :id "+
						  " where dataInicio <= :date "+
						  " AND (dataFim >= :date OR dataFim is null)";
				
				
		Session session = this.getSession();
		Query query = session.createQuery(strQuery);
		
		query.setLong("id", sla.getId());
		query.setDate("date", dia.getTime());
		
		return (List<JanelaSla>) query.list();
		
	}
	
	/**
	 * Lista os SLAS não calculados referentes ao dia base
	 * @param dia base
	 * @return Lista com os SLAS
	 */
	public List<Sla> listSlaNaoRodados(Calendar dia){
		
		Session session = this.getSession();
		
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
		
		return (List<Sla>) query.list();
	}
	
	public List<Indisponibilidade> listaIndisponibilidadesPeriodo(No no, Date dataInicio, Date dataFim){
		
		StringBuilder queryStr  = new StringBuilder();
		
		queryStr.append(" FROM Indisponibilidade WHERE No.id = :noId ");
		queryStr.append(" AND ");
		queryStr.append("     (");
		queryStr.append("      (inicio >= :dataInicio AND fim <= :dataFim) OR");
		queryStr.append("      (inicio <= :dataFim AND fim is null) OR");
		queryStr.append("      (inicio <= :dataInicio AND fim >= :dataInicio) OR");
		queryStr.append("      (inicio <= :dataInicio AND fim >= :dataFim) OR");
		queryStr.append("     )");
		
		Session session = this.getSession();
		Query query = session.createQuery(queryStr.toString());
		query.setInteger("noId", no.getId());
		query.setDate("dataInicio", dataInicio);
		query.setDate("dataFim", dataFim);
		
		return (List<Indisponibilidade>) query.list();
		
	}
	
	
	
	
	
	
	
	
	
}
