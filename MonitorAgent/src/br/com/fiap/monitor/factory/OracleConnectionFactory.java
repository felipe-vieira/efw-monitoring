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
			System.exit(1);
		}
	}
	
	public static Connection getConnection() throws SQLException{
			
			//TODO parametrizar porta, usuario, senha e instancia
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String usuario = "SYSTEM";
			String senha = "password";
		
			registraDriver();
					
			return DriverManager.getConnection(url,usuario,senha);
	}
	
}
