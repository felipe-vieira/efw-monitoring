package br.com.fiap.monitor;

import br.com.fiap.monitor.socket.AgentSocket;


public class Run {
	
	public static void main(String[] args) {
		System.out.println("Iniciando o agente");
		AgentSocket socket = new AgentSocket();
		socket.openSocket();
		
		
		while(true){
			socket.handleRequest();
		}
		
	}
	
	
}
