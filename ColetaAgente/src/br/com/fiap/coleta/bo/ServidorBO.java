package br.com.fiap.coleta.bo;

import java.util.List;

import br.com.fiap.coleta.dao.MemoriaDAO;
import br.com.fiap.coleta.dao.ParticaoDAO;
import br.com.fiap.coleta.dao.ProcessadorDAO;
import br.com.fiap.coleta.dao.ServidorColetaDAO;
import br.com.fiap.coleta.dao.ServidorDAO;
import br.com.fiap.coleta.dao.SistemaOperacionalDAO;
import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.MemoriaColeta;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.ParticaoColeta;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.ProcessadorColeta;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.SistemaOperacional;

public class ServidorBO {
	
	
	private ServidorDAO servidorDAO;
	private SistemaOperacionalDAO sistemaOperacionalDAO;
	private ProcessadorDAO processadorDAO;
	private MemoriaDAO memoriaDAO;
	private ParticaoDAO particaoDAO;
	private ServidorColetaDAO servidorColetaDAO;
	
	public ServidorBO(){
		this.servidorDAO = new ServidorDAO();
		this.sistemaOperacionalDAO = new SistemaOperacionalDAO();
		this.processadorDAO = new ProcessadorDAO();
		this.memoriaDAO = new MemoriaDAO();
		this.particaoDAO = new ParticaoDAO();
		this.servidorColetaDAO = new ServidorColetaDAO();
	}
	
	
	public void updateServidorColeta(Servidor servidor){
		
		if(servidor.getDisponivel() && servidor.getGerenciavel()){
			
			this.sistemaOperacionalDAO.salvarSistemaOperacional(servidor.getSistemaOperacional());
			this.processadorDAO.salvarProcessador(servidor.getProcessador());
			this.memoriaDAO.salvarMemoria(servidor.getMemoria());
			this.particaoDAO.salvaListaParticoes(servidor.getParticoes());
		}	
		
		this.servidorDAO.updateServidor(servidor);
		

	}
	
	
	public SistemaOperacional pegaSistemaOperacionalServidor(Servidor servidor){
		return sistemaOperacionalDAO.pegaSistemaOperacionalServidor(servidor);
	}
	
	public Processador pegaProcessadorServidor(Servidor servidor){
		return processadorDAO.pegaProcessadorServidor(servidor);
	}
	
	public Memoria pegaMemoriaServidor(Servidor servidor){
		return memoriaDAO.pegaMemoriaServidor(servidor);		
	}
	
	public Particao pegaParticaoServidor(Servidor servidor, String nome){
		return particaoDAO.pegaParticaoServidor(servidor, nome);
	}
	
	public void saveColetaMemoria(MemoriaColeta coleta){
		if(coleta != null){
			this.servidorColetaDAO.saveColetaMemoria(coleta);
		}
	}
	
	public void saveColetaProcessador(ProcessadorColeta coleta){
		if(coleta != null){
			this.servidorColetaDAO.saveColetaProcessador(coleta);
		}
	}


	public void saveListaColetaParticao(List<ParticaoColeta> listaParticaoColeta) {
		if(listaParticaoColeta != null && listaParticaoColeta.size() > 0){
			this.servidorColetaDAO.saveListaColetaParticao(listaParticaoColeta);
		}
		
	}
}
