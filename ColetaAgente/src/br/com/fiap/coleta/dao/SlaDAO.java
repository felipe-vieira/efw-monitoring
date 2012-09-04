package br.com.fiap.coleta.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Sla;

public class SlaDAO extends GenericDAO{

	public List<No> listNosSla(Sla sla){
		
		Session session = this.getSession();
		Query query = session.createQuery("FROM No where sla.id = :id");
		query.setLong("id", sla.getId());
		
		return (List<No>) query.list();
		
	}
	
	public List<No> listJanelasSla(Sla sla){
		
		Session session = this.getSession();
		Query query = session.createQuery("FROM No where sla.id = :id");
		query.setLong("id", sla.getId());
		
		return (List<No>) query.list();
		
	}
	
	public List<Sla> listSlaNaoRodados(){
		
		Session session = this.getSession();
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		
		Query query = session.createQuery("FROM Sla where ultimaColeta < :date");
		query.setDate("date",calendar.getTime());
		
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
