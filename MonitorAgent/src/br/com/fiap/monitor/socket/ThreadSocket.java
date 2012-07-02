package br.com.fiap.monitor.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.com.fiap.monitor.comandos.Comando;

public class ThreadSocket implements Runnable {
	
	private Socket clientSocket;
	
	public ThreadSocket(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		this.handleCommands();	
	}
	
	/**
	 * @author Felipe 
	 * Responsável por receber e tratar comandos do cliente
	 * @param clientSocket Socket do Cliente
	 */
	private void handleCommands() {
		BufferedReader in;
		PrintWriter out;
		
		String command = "";
		String retorno;
		
		try {
			in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			out = new PrintWriter(this.clientSocket.getOutputStream(),true);
			
			
			while (!command.equals("close") && this.clientSocket !=null && this.clientSocket.isBound()){
				
				while(!in.ready()){
					Thread.sleep(100);
				}
				command = in.readLine();
				retorno = Comando.trataComando(command);
					
				if(retorno != null){
					out.println(retorno);
				}else{
					out.println("Comando invalido.");
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		closeSocket();
	}
	
	
	public void closeSocket(){
		try{
			this.clientSocket.close();
		}catch(IOException ex){
			System.out.println("Erro ao terminar a conexão...");
			ex.printStackTrace();
		}
	}
	
}
