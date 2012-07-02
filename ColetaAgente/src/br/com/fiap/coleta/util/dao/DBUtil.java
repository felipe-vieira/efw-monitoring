package br.com.fiap.coleta.util.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * O papel desta classe � vincular a aplica��o � camada de persist�ncia.
 * 
 * @author fabianmartins
 * 
 */
public class DBUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Este m�todo inicializa o Hibernate, fornecendo a ele o arquivo de
	 * configura��o 'resource/hibernate.cfg.xml' que inicializara' a
	 * SessionFactory
	 * 
	 * @return
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("resource/hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Erro na inicializacao da SessionFactory" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Solicita � SessionFactory que produza uma sess�o, solicitando-a ao
	 * ConnectionProvider. A classe ConnectionProvider, que conhece as
	 * configura��es as caracter�sticas do SGBD cuida de gerar uma nova conex�o
	 * ou buscar uma conex�o no pool, entregando-a dentro do escopo de uma
	 * sess�o.
	 * 
	 * O trabalho da SessionFactory e da ConnectionProvider � transparente para
	 * as classes que conversam com elas.
	 * 
	 * � importante lembrar que sempre que for realizado um commit ou rollback,
	 * a sess�o � fechada.
	 * 
	 * @return
	 */
	public static Session getCurrentSession() {
		Session session = sessionFactory.getCurrentSession();
		if (!session.isOpen())
			session = sessionFactory.openSession();
		return session;
	}

}
