package br.com.fiap.monitor.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.MemoriaColeta;
import br.com.fiap.coleta.entities.ParticaoColeta;
import br.com.fiap.coleta.entities.ProcessadorColeta;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoriaColeta;
import br.com.fiap.coleta.entities.enumerators.TipoMemoriaServidorAplicacao;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.to.MetricaTO;

public class MetricasBO {

	
	private GenericDAO genericDAO;

	public MetricasBO(){
		this.genericDAO = new GenericDAO();
	}
	
	public List<MetricaTO> listMemoriaColeta(Long idNo, Date inicio, Date fim){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM MemoriaColeta where memoria.id = :idNo AND (dataColeta BETWEEN :inicio AND :fim )");
			
			query.setLong("idNo", idNo);
			query.setTimestamp("inicio", inicio);
			query.setTimestamp("fim", fim);
			
			List<MemoriaColeta> lista = this.genericDAO.queryList(query);
			List<MetricaTO> listaMetricas = new ArrayList<MetricaTO>();
			
			
			for (MemoriaColeta memoriaColeta : lista) {
				
				MetricaTO metrica = new MetricaTO();
				
				metrica.setData(memoriaColeta.getDataColeta());
				metrica.setValor(memoriaColeta.getUsado());
				metrica.setMax(memoriaColeta.getMemoria().getTotalMemoria());
				
				listaMetricas.add(metrica);
			}
			
			t.commit();
			
			return listaMetricas;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<MetricaTO> listProcessadorColeta(Long idNo, Date inicio, Date fim){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM ProcessadorColeta where processador.id = :idNo AND (dataColeta BETWEEN :inicio AND :fim )");
			
			query.setLong("idNo", idNo);
			query.setTimestamp("inicio", inicio);
			query.setTimestamp("fim", fim);
			
			List<ProcessadorColeta> lista = this.genericDAO.queryList(query);
			List<MetricaTO> listaMetricas = new ArrayList<MetricaTO>();
			

			for (ProcessadorColeta processadorColeta : lista) {
				MetricaTO metrica = new MetricaTO();
				
				metrica.setData(processadorColeta.getDataColeta());
				metrica.setValorPercentual(new BigDecimal(processadorColeta.getUsado()));
				
				listaMetricas.add(metrica);
			}
			
			t.commit();
			
			return listaMetricas;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<MetricaTO> listParticaoColetas(Integer idParticao, Date inicio, Date fim){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM ParticaoColeta where particao.id = :idParticao AND (dataColeta BETWEEN :inicio AND :fim )");
			
			query.setLong("idParticao", idParticao);
			query.setTimestamp("inicio", inicio);
			query.setTimestamp("fim", fim);
			
			List<ParticaoColeta> lista = this.genericDAO.queryList(query);
			List<MetricaTO> listaMetricas = new ArrayList<MetricaTO>();
			

			for (ParticaoColeta particaoColeta : lista) {
				MetricaTO metrica = new MetricaTO();
				
				metrica.setData(particaoColeta.getDataColeta());
				metrica.setValor(particaoColeta.getUsado());
				metrica.setMax(particaoColeta.getParticao().getTamanho());
				
				listaMetricas.add(metrica);
			}
			
			t.commit();
			
			return listaMetricas;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<MetricaTO> listMemoriaHeapServidorAplicacaoColetas(Integer idNo, Date inicio, Date fim){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM ServidorAplicacaoMemoriaColeta where memoria.servidorAplicacao.id = :idNo" +
					" AND memoria.tipo = :tipo (dataColeta BETWEEN :inicio AND :fim )");
			
			query.setLong("idParticao", idNo);
			query.setParameter("tipo", TipoMemoriaServidorAplicacao.HEAP);
			query.setTimestamp("inicio", inicio);
			query.setTimestamp("fim", fim);
			
			List<ServidorAplicacaoMemoriaColeta> lista = this.genericDAO.queryList(query);
			List<MetricaTO> listaMetricas = new ArrayList<MetricaTO>();

			for (ServidorAplicacaoMemoriaColeta memoriaColeta : lista) {
				MetricaTO metrica = new MetricaTO();
				
				metrica.setData(memoriaColeta.getDataColeta());
				metrica.setValor(memoriaColeta.getUsed());
				metrica.setValorAlt(memoriaColeta.getCommited());
							
				listaMetricas.add(metrica);
			}
			
			t.commit();
			
			return listaMetricas;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	
	public List<MetricaTO> listMemoriaNonHeapServidorAplicacaoColetas(Integer idNo, Date inicio, Date fim){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM ServidorAplicacaoMemoriaColeta where memoria.servidorAplicacao.id = :idNo" +
					" AND memoria.tipo = :tipo (dataColeta BETWEEN :inicio AND :fim )");
			
			query.setLong("idParticao", idNo);
			query.setParameter("tipo", TipoMemoriaServidorAplicacao.NONHEAP);
			query.setTimestamp("inicio", inicio);
			query.setTimestamp("fim", fim);
			
			List<ServidorAplicacaoMemoriaColeta> lista = this.genericDAO.queryList(query);
			List<MetricaTO> listaMetricas = new ArrayList<MetricaTO>();

			for (ServidorAplicacaoMemoriaColeta memoriaColeta : lista) {
				MetricaTO metrica = new MetricaTO();
				
				metrica.setData(memoriaColeta.getDataColeta());
				metrica.setValor(memoriaColeta.getUsed());
				metrica.setValorAlt(memoriaColeta.getCommited());
							
				listaMetricas.add(metrica);
			}
			
			t.commit();
			
			return listaMetricas;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public List<MetricaTO> listProcessadorServidorAplicacaoColetas(Integer idNo, TipoMemoriaServidorAplicacao tipo, Date inicio, Date fim){
		
		Session session = this.genericDAO.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			
			Query query = session.createQuery("FROM ServidorAplicacaoMemoriaColeta where memoria.servidorAplicacao.id = :idNo" +
					" AND memoria.tipo = :tipo (dataColeta BETWEEN :inicio AND :fim )");
			
			query.setLong("idParticao", idNo);
			query.setParameter("tipo", tipo);
			query.setTimestamp("inicio", inicio);
			query.setTimestamp("fim", fim);
			
			List<ParticaoColeta> lista = this.genericDAO.queryList(query);
			List<MetricaTO> listaMetricas = new ArrayList<MetricaTO>();

			for (ParticaoColeta particaoColeta : lista) {
				MetricaTO metrica = new MetricaTO();
				
				metrica.setData(particaoColeta.getDataColeta());
				metrica.setValor(particaoColeta.getUsado());
				metrica.setMax(particaoColeta.getParticao().getTamanho());
				
				listaMetricas.add(metrica);
			}
			
			t.commit();
			
			return listaMetricas;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
}
