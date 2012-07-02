package br.com.fiap.coleta.util.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtil {
	
		private BufferedReader in;
		private PrintWriter out;
		private Socket socket;
		private String host;
		private Integer port;
			
		public SocketUtil(String host, Integer port){
			this.host = host;
			this.port = port;
		}
		
		
		public void openSocket() throws IOException{
			this.socket = new Socket(this.host,this.port);
			
			if(this.socket != null && this.socket.isBound()){
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(),true);
			}
		}
		
		
		private void send(String s) throws IOException{
			out.println(s);
		}
		
		private String read() throws IOException,InterruptedException{
			
			while(!in.ready()){
				Thread.sleep(100);
			}
			
			return in.readLine(); 
		}
		
		public String enviaComando(String s) throws IOException,InterruptedException{
			this.send(s);
			return this.read();
		}
		
		public void close() throws IOException{
			this.send("close");
			this.socket.close();
		}
	
}
