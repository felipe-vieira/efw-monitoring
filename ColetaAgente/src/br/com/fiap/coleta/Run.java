package br.com.fiap.coleta;

import java.io.FileInputStream;
import java.util.Properties;

import br.com.fiap.coleta.cgt.GerenciadorFila;
import br.com.fiap.coleta.util.dao.ConnectionInfo;

public class Run {
	
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
		
		System.out.println("Iniciando o coletor...");
		GerenciadorFila gerenciador = new GerenciadorFila();
		gerenciador.iniciaGerenciador();
		
	}
	
}
