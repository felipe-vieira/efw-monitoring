package br.com.fiap.coleta.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.dao.NoDAO;
import br.com.fiap.coleta.dao.SlaDAO;
import br.com.fiap.coleta.entities.JanelaSla;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Sla;

public class SlaBO {

	private SlaDAO slaDAO;
		
	
	public SlaBO(){
		this.slaDAO = new SlaDAO();
	}
	
	public void calculaSlaDiario(){
	
		Session session = this.slaDAO.getSession();
		Transaction t = session.beginTransaction();
		
		//Define o periodo do SLA Calculado (D-1)
		Calendar dtInicio = Calendar.getInstance();
		Calendar dtFim = Calendar.getInstance();
		
		dtInicio.set(Calendar.HOUR_OF_DAY, 0);
		dtInicio.set(Calendar.MINUTE, 0);
		dtInicio.set(Calendar.SECOND, 0);
		dtInicio.set(Calendar.DATE, (dtInicio.get(Calendar.DATE) -1) );
		
		dtFim.set(Calendar.HOUR_OF_DAY, 0);
		dtFim.set(Calendar.MINUTE, 0);
		dtFim.set(Calendar.SECOND, 0);
		
		System.out.println(dtInicio.getTime());
		System.out.println(dtFim.getTime());
		
		try{
			List<Sla> slas = this.slaDAO.listSlaNaoRodados(dtInicio);
			
			for (Sla sla : slas) {
				List<No> nos = this.slaDAO.listNosSla(sla);
				List<JanelaSla> janelas = this.slaDAO.listJanelasSla(sla,dtInicio);
			}
			
			t.commit();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
		}
		
	}
	
}
