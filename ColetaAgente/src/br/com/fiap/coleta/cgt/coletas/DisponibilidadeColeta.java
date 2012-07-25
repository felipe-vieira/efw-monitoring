package br.com.fiap.coleta.cgt.coletas;

import java.io.Serializable;
import java.util.Date;

import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class DisponibilidadeColeta extends ServidorAplicacao implements Serializable {

	private SocketUtil socket;
	private Date data;
	private No no = this.getServidor();
	
	public void verificaDisponibilidade(){
		
		socket = new SocketUtil(this.getHostname(), 9090);
		
		try{
			socket.openSocket();
	
			no.setDisponivel(true);
			no.setGerenciavel(true);
			no.setUltimaColeta(data);
			
			socket.close();
		}catch(Exception e){
	
			no.setDisponivel(false);
			no.setGerenciavel(false);
			no.setUltimaColeta(data);
			
			e.printStackTrace();
		}
		
	}
	
}
