package br.com.fiap.monitor.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionFactory {
	
	public static void registraDriver(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	   
	
	//public static Connection getConnection() throws SQLException{
	 public static Connection getConnection(String usuario, String senha, String host, int porta) throws SQLException{
			
			//TODO parametrizar porta, usuario, senha e instancia
			String url = "jdbc:oracle:thin:@" + host + ":" + porta + ":xe";
			//String usuario = "system";
			//String senha = "Gavalsa0512";
		
			registraDriver();
					
			return DriverManager.getConnection(url,usuario,senha);
	}
	
}
