package br.com.fiap.monitor.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerConnectionFactory {
	
	
	public static void registraDriver(){
		try{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection(String usuario, String senha, String host, int porta, String instancia) throws SQLException{
			
			//TODO parametrizar porta, usuario, senha e instancia
			String url = "jdbc:jtds:sqlserver://" + host + "/master;instance=" + instancia;
			//String usuario = "sa";
			//String senha = "Gavalsa0512";
		
			registraDriver();
					
			return DriverManager.getConnection(url,usuario,senha);
	}
}
