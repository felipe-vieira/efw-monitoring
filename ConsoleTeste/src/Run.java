import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Run {
	public static void main(String[] args) {
		SocketUtil socket = new SocketUtil();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String ip = "127.0.0.1";
		
		if(args != null && args.length > 0 && args[0] != null && !args[0].trim().equals("")){
			ip = args[0];
		}
		
		
		System.out.print("Conectando em " + ip  );
		socket.openSocket(ip);
		System.out.println("OK!");
		
		while(true){
			try{
				String comando = "";
				System.out.print(">");
				comando = reader.readLine();
				socket.send(comando);
				socket.read();
				
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		
	
	}
}
