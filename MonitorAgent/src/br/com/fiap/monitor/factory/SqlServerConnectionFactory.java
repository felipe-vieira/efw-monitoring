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
			System.exit(1);
		}
	}
	
	public static Connection getConnection() throws SQLException{
			
			//TODO parametrizar porta, usuario, senha e instancia
			String url = "jdbc:jtds:sqlserver://192.168.1.108:1433/MSSQLSERVER";
			String usuario = "sa";
			String senha = "Gavalsa0512";
		
			registraDriver();
					
			return DriverManager.getConnection(url,usuario,senha);
	}
}
