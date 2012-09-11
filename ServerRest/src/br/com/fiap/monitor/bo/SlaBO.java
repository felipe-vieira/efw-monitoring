package br.com.fiap.monitor.bo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.tree.QueryNode;

import br.com.fiap.coleta.entities.DiasSemanaJanelaSla;
import br.com.fiap.coleta.entities.DiasSemanaSla;
import br.com.fiap.coleta.entities.JanelaSla;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.coleta.entities.SlaCalculado;
import br.com.fiap.coleta.entities.enumerators.TipoSla;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.PagingTO;
import br.com.fiap.monitor.to.ReturnTO;

public class SlaBO {

	private GenericDAO genericDAO;

	public SlaBO(){
		this.genericDAO = new GenericDAO();
	}
	
	public Sla getSlaById(Long id){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		try{
			Sla sla = (Sla) this.genericDAO.getById(Sla.class, id);
			t.commit();
			return sla;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<Sla> listSlasAtivos(Integer start, Integer limit){
		 
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM Sla where ativo = :ativo");
			
			query.setBoolean("ativo", true);
			
			if(start!=null){
				query.setFirstResult(start);
			}
			if(limit!=null){
				query.setMaxResults(limit);
			}
			
			List<Sla> lista = this.genericDAO.queryList(query);
			
			t.commit();
			
			return lista;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	
	
	public Long contaSlasAtivos(){
		 
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("SELECT count(*) FROM Sla where ativo = :ativo");
			
			query.setBoolean("ativo", true);
			
			Long total = (Long) query.uniqueResult();
			
			t.commit();
			
			return total;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public ReturnTO saveUpdateSla(Sla sla){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction(); 
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			if(sla.getNome() == null || sla.getNome().equals("")){
				retorno.setMessage("O campo Nome � obrigat�rio.");
				t.rollback();
			}else if(sla.getMeta() == null){
				retorno.setMessage("O campo Meta � obrigat�rio.");
				t.rollback();
			}else if(sla.getHoraInicio() == null){
				retorno.setMessage("O campo Hora Inicio � obrigat�rio.");
				t.rollback();
			}else if(sla.getHoraFim() == null){
				retorno.setMessage("O campo Hora Fim � obrigat�rio.");
				t.rollback();
			}else{
				
				sla.setAtivo(true);
				Long id = sla.getId();
				
				if(sla.getId() == null || sla.getId() == 0){
					id = (Long) this.genericDAO.save(sla);
				}else{
					this.genericDAO.update(sla);
				}
				
				t.commit();
				
				retorno.setId(id);
				retorno.setSuccess(true);
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();			
		}

		return retorno;
		
	}
	
	
	public ReturnTO desativaSla(Sla sla){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{

			Query queryNos = session.createQuery("FROM No where sla.id = :id");
			queryNos.setLong("id",sla.getId());
			
			List<No> nos = this.genericDAO.queryList(queryNos);
			
			for (No no : nos) {
				no.setSla(null);
			}
			
			
			sla.setAtivo(false);
			this.genericDAO.merge(sla);
			t.commit();
			retorno.setSuccess(true);
			
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();			
		}

		return retorno;
		
	}
	
	
	public DiasSemanaSla getDiasSemanaSla(Long id){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		try{
			DiasSemanaSla dias = (DiasSemanaSla) this.genericDAO.getById(DiasSemanaSla.class, id);
			t.commit();
			return dias;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public ReturnTO saveDiasSemanaSla(DiasSemanaSla dias){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			DiasSemanaSla diaOld = (DiasSemanaSla) this.genericDAO.getById(DiasSemanaSla.class, dias.getId());
						
			if(diaOld == null){	
				this.genericDAO.save(dias);
			}else{
				this.genericDAO.merge(dias);
			}
			
			t.commit();
			retorno.setSuccess(true);
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
			
		}
		
		return retorno;
		
	}
	
	public List<JanelaSla> listaJanelasSla(Integer start, Integer limit){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		
		try{
			
			Query query = session.createQuery("FROM JanelaSla WHERE ativo = :ativo");
			query.setBoolean("ativo", true);
			
			if(start != null){
				query.setFirstResult(start);
			}
			
			if(limit != null){
				query.setMaxResults(limit);
			}
			
			List<JanelaSla> lista = this.genericDAO.queryList(query);
			t.commit();
			return lista;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public ReturnTO saveUpdateJanelaSla(JanelaSla janela,Long slaId){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("k:m:s");
		
		Long inicio = Time.valueOf(timeFormat.format(janela.getHoraInicio())).getTime();
		Long fim = Time.valueOf(timeFormat.format(janela.getHoraFim())).getTime();
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			Sla sla = (Sla) this.genericDAO.getById(Sla.class, slaId);
			
			if(sla==null){
				retorno.setMessage("O campo SLA � obrigat�rio");
				t.rollback();
			}else if(janela.getDescricao() == null || janela.getDescricao().equals("")){
				retorno.setMessage("O campo Descricao � obrigat�rio");
				t.rollback();
			}else if(janela.getDataInicio() == null){
				retorno.setMessage("O campo Data Inicio � obrigat�rio");
				t.rollback();				
			}else if(janela.getDataFim() == null){
				retorno.setMessage("O campo Data Fim � obrigat�rio");
				t.rollback();
			}else if (janela.getHoraInicio() == null){
				retorno.setMessage("O campo Hora Inicio � obrigat�rio");
				t.rollback();
			}else if(inicio < sla.getHoraInicio().getTime()){
				retorno.setMessage("O campo Hora inicio � anterior ao inicio do periodo do SLA selecionado.");
				t.rollback();
			}else if (janela.getHoraFim() == null){
				retorno.setMessage("O campo Hora Fim � obrigat�rio");
				t.rollback();
			}else if(fim > sla.getHoraFim().getTime()){
				retorno.setMessage("O campo Hora fim � superior ao termino do periodo do SLA selecionado.");
				t.rollback();
			}else{
				
				janela.setAtivo(true);
				janela.setSla(sla);
				
				Long id = janela.getId();
				
				if(janela.getId() == null || janela.getId() == 0){
					id = (Long) this.genericDAO.save(janela);
				}else{
					this.genericDAO.merge(janela);
				}
				
				t.commit();
				retorno.setSuccess(true);
				retorno.setId(id);
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
		}
		
		
		return retorno;
		
		
	}
	
	public ReturnTO desativaJanelaSla(JanelaSla janela){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			janela.setAtivo(false);
			this.genericDAO.merge(janela);
			t.commit();
			retorno.setSuccess(true);
			
		}catch(Exception ex){
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();			
		}

		return retorno;
		
	}
	
	public DiasSemanaJanelaSla getDiasSemanaJanelaSla(Long id){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		try{
			DiasSemanaJanelaSla dias = (DiasSemanaJanelaSla) this.genericDAO.getById(DiasSemanaJanelaSla.class, id);
			t.commit();
			return dias;
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public ReturnTO saveDiasSemanaJanelaSla(DiasSemanaJanelaSla dias){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();	
		
		ReturnTO retorno = new ReturnTO();
		retorno.setSuccess(false);
		
		try{
			
			DiasSemanaJanelaSla diaOld = (DiasSemanaJanelaSla) this.genericDAO.getById(DiasSemanaJanelaSla.class, dias.getId());
			
			if(diaOld == null){
				this.genericDAO.save(dias);
			}else{
				this.genericDAO.merge(dias);
			}
			
			t.commit();
			retorno.setSuccess(true);
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			retorno.setMessage(ex.getMessage());
			t.rollback();
			
		}
		
		return retorno;
		
	}
	
	public SlaCalculado ultimoSlaMensalNo(Integer noId){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM SlaCalculado WHERE no.id = :noId AND tipo = :tipo ORDER BY controle DESC");
			query.setInteger("noId", noId);
			query.setParameter("tipo", TipoSla.MENSAL);
			
			query.setMaxResults(1);
			
			SlaCalculado sla = (SlaCalculado) query.uniqueResult();
			
			t.commit();
			
			return sla;		
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public SlaCalculado ultimoSlaDiarioNo(Integer noId){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM SlaCalculado WHERE no.id = :noId AND tipo = :tipo ORDER BY controle DESC");
			query.setInteger("noId", noId);
			query.setParameter("tipo", TipoSla.DIARIO);
			
			query.setMaxResults(1);
			
			SlaCalculado sla = (SlaCalculado) query.uniqueResult();
			
			t.commit();
			
			return sla;		
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<SlaCalculado> listaSlasCalculados(Long id, Date dataInicio, Date dataFim, Integer start, Integer limit, TipoSla tipo){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM SlaCalculado WHERE no.id = :noId AND tipo = :tipo" +
											  " AND controle BETWEEN :dataInicio AND :dataFim"+
											  " ORDER BY controle ASC");
			
			query.setLong("noId", id);
			query.setParameter("tipo", tipo);
			query.setDate("dataInicio", dataInicio);
			query.setDate("dataFim", dataFim);
			
			query.setFirstResult(0);
			query.setMaxResults(limit);
			
			List<SlaCalculado> lista = this.genericDAO.queryList(query);
			
			t.commit();
			
			return lista;		
		
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	public Long countSlaCalculados(Long id, Date dataInicio, Date dataFim, TipoSla tipo){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("SELECT count(*) FROM SlaCalculado WHERE no.id = :noId AND tipo = :tipo" +
											  " AND controle BETWEEN :dataInicio AND :dataFim"+
											  " ORDER BY controle ASC");
			
			query.setLong("noId", id);
			query.setParameter("tipo", tipo);
			query.setDate("dataInicio", dataInicio);
			query.setDate("dataFim", dataFim);
			
			Long total = (Long) query.uniqueResult();
			
			t.commit();
			
			return total;		
		
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
	public List<SlaCalculado> listaSlaAbaixoMeta(Integer start, Integer limit){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Calendar lastDay = Calendar.getInstance();
			
			Calendar firstDay = Calendar.getInstance();
			firstDay.roll(Calendar.DAY_OF_YEAR, -15);
			
			Query query = session.createQuery("FROM SlaCalculado where percentual < sla.meta "+
												" AND controle BETWEEN :dataInicio and :dataFim");
			
			query.setParameter("dataInicio", firstDay.getTime());
			query.setParameter("dataFim", lastDay.getTime());
			
			query.setFirstResult(start);
			query.setMaxResults(limit);
			
			List<SlaCalculado> lista = this.genericDAO.queryList(query);
			t.commit();
			
			return lista;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public Long contaSlasAbaixoMeta(){
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Calendar firstDay = Calendar.getInstance();
			firstDay.set(Calendar.DATE, 1);
			
			Calendar lastDay = Calendar.getInstance();
			lastDay.set(Calendar.DATE, lastDay.getActualMaximum(Calendar.DATE));
			
			Query query = session.createQuery("SELECT count(*) FROM SlaCalculado where tipo = :tipo AND percentual < sla.meta "+
												" AND controle BETWEEN :dataInicio and :dataFim");
			
			query.setParameter("tipo", TipoSla.MENSAL);
			query.setParameter("dataInicio", firstDay.getTime());
			query.setParameter("dataFim", lastDay.getTime());
			
			
			Long total = (Long) query.uniqueResult();
			t.commit();
			
			return total;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}
	
}