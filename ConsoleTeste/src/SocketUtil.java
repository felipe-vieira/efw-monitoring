import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtil {

		public static final String  HOST = "127.0.0.1";
		public static final Integer PORT = 9090;
	
		private BufferedReader in;
		private PrintWriter out;
		private Socket socket;
	
		public void openSocket(String ip){
			try{
				this.socket = new Socket(ip,SocketUtil.PORT);
				
				if(this.socket != null && this.socket.isBound()){
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(),true);
				}
				
			}catch(IOException ex){
				ex.printStackTrace();
				System.out.println("Erro de conexão, flw!");
				System.exit(1);
			}	
		}
		
		public void send(String s){
			out.println(s);
		}
		
		public void read(){
			try{
				System.out.println(in.readLine());
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		
	
	
}
