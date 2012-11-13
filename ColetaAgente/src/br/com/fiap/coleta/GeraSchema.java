package br.com.fiap.coleta;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.fiap.coleta.util.dao.ConnectionInfo;

public class GeraSchema {

	
	public static void main(String[] args) {
		
		Properties properties = new Properties();
		
		try{
			
			properties.load(new FileInputStream("config.properties"));
			ConnectionInfo.hostname = properties.getProperty("hostname");
			ConnectionInfo.port = Integer.parseInt(properties.getProperty("port"));
			ConnectionInfo.database = properties.getProperty("database");
			ConnectionInfo.user = properties.getProperty("user");
			ConnectionInfo.password = properties.getProperty("password");
			
		}catch(Exception ex){
		
			ConnectionInfo.hostname = "127.0.0.1";
			ConnectionInfo.port = 3306;
			ConnectionInfo.database = "monitor";
			ConnectionInfo.user = "efwdbusr";
			ConnectionInfo.password = "4c4b@Tcc";
			
		}
		
		try {
			
			String urlConexao = "jdbc:mysql://"+ConnectionInfo.hostname+"/"+ConnectionInfo.database;
			
			Configuration configuration = new Configuration();
			
			configuration.configure("resource/hibernate.cfg.xml")
				.setProperty("hibernate.connection.url", urlConexao)
				.setProperty("hibernate.connection.username", ConnectionInfo.user)
				.setProperty("hibernate.connection.password", ConnectionInfo.password);
			
						
			SchemaExport ex = new SchemaExport(configuration);
			ex.create(true, false);
			
		} catch (Throwable ex) {
			System.err.println("Erro na inicializacao da SessionFactory" + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}
}
