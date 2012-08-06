package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.AlarmeBO;
import br.com.fiap.coleta.bo.ServidorBO;
import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.Memoria;
import br.com.fiap.coleta.entities.MemoriaColeta;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.ParticaoColeta;
import br.com.fiap.coleta.entities.Processador;
import br.com.fiap.coleta.entities.ProcessadorColeta;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorThreshold;
import br.com.fiap.coleta.entities.SistemaOperacional;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class ServidorColeta {
	
	private Servidor servidor;
	
	private ServidorBO servidorBO;
	
	private AlarmeBO alarmeBO;
		
	private SocketUtil socket;
	
	private Date dataColeta;
	
	
	public ServidorColeta(No no){
		this.servidor = (Servidor) no;
		this.servidorBO = new ServidorBO();
		this.alarmeBO = new AlarmeBO();
	}
	
	public void initColeta(){

		//Movido pra ca para conseguir marcar data caso falhe
		this.dataColeta = new Date();
		socket = new SocketUtil(this.servidor.getHostname(), 9090);
		
		Boolean ultimoStatus = servidor.getDisponivel();
		
		
		try{
			
			//Abre o socket
			socket.openSocket();
			
			//Lista os alarmes de coleta
			List<Alarme> listaAlarmes = new ArrayList<Alarme>();
			
			MemoriaColeta memoriaColeta = null;
			ProcessadorColeta processadorColeta = null;
			List<ParticaoColeta> listaParticaoColeta = new ArrayList<ParticaoColeta>();
			
			ServidorThreshold t = servidor.getThreshold();
			
			// Disponivel
			
			this.servidor.setGerenciavel(true);
			this.servidor.setDisponivel(true);
			
			//Atualiza os itens de configura��o
			this.servidor.setSistemaOperacional(this.getConfigOs());
			this.servidor.setProcessador(this.getConfigProcessor());
			this.servidor.setMemoria(this.getConfigMemory());
			this.servidor.setParticoes(this.getConfigPartitions());
			
			//Faz as coletas
			memoriaColeta = this.getOsMemory();
			processadorColeta = this.getOsProcessor();								
			
			for(Particao p : this.servidor.getParticoes()){
				listaParticaoColeta.add(this.getOsPartition(p));
			}
			
			// Ultima coleta
			this.servidor.setUltimaColeta(dataColeta);
			
			//Fecha o socket
			socket.close();

			//Persiste tudo
			this.servidorBO.updateServidorColeta(this.servidor);
			this.servidorBO.saveColetaMemoria(memoriaColeta);
			this.servidorBO.saveColetaProcessador(processadorColeta);
			this.servidorBO.saveListaColetaParticao(listaParticaoColeta);
			
			
		}catch (IOException e) {
			this.servidor.setDisponivel(false);
			this.servidor.setGerenciavel(false);
			this.servidor.setUltimaColeta(dataColeta);
			this.servidorBO.updateServidorColeta(this.servidor);
			System.out.println("Imposs�vel abrir o socket. Verifique se o agente est� instalado no servidor.");
			
			this.alarmeBO.geraAlarmeIndsiponibilidade(this.servidor, ultimoStatus);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	
			
		
		
	}
	
	private SistemaOperacional getConfigOs(){
		
		SistemaOperacional so = servidorBO.pegaSistemaOperacionalServidor(servidor);
		
		if(so == null){
			so = new SistemaOperacional(this.servidor);
		}
		
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get config.os"));
			//System.out.println(json.toString());
			
			so.setNome(json.getString("name"));
			so.setDescricao(json.getString("descr"));
			so.setArquitetura(json.getString("arch"));
			so.setVersao(json.getString("version"));
			so.setPatch(json.getString("patch"));
			so.setFornecedor(json.getString("vendor"));
			
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return so;
		
	}
	
	private Processador getConfigProcessor(){
		Processador processador = servidorBO.pegaProcessadorServidor(this.servidor);
		
		if(processador == null){
			processador = new Processador(this.servidor);
		}
		
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get config.processor"));
			//System.out.println(json.toString());
			
			processador.setFabricante(json.getString("vendor"));
			processador.setModelo(json.getString("model"));
			processador.setCores((Integer) json.get("totalCores"));
			processador.setClock(json.getLong("freq"));
			
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return processador;
	
	}
	
	private Memoria getConfigMemory(){
		Memoria memoria = servidorBO.pegaMemoriaServidor(servidor);
		
		if(memoria == null){
			memoria = new Memoria(this.servidor);
		}
		
		try{
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get config.memory"));
			System.out.println(json.toString());
			
			//pega em bytes
			memoria.setTotalMemoria(json.getLong("total"));
			
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return memoria;
	}
		
	private List<Particao> getConfigPartitions(){
		List<Particao> listaParticoes = new ArrayList<Particao>();
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get config.partitions"));
			JSONArray jsonArray = json.getJSONArray("partitions");
			 	
			for (Object object : jsonArray) {
				
				JSONObject i = (JSONObject) object;
				String nome = i.getString("name");
				
				Particao p = servidorBO.pegaParticaoServidor(this.servidor, nome);
				
				if(p==null){
					p = new Particao(this.servidor);
					p.setNome(nome);
				}
				
				p.setSistemaArquivo(i.getString("fileSystem"));
				//Pega em KBytes
				p.setTamanho(i.getLong("totalSize"));
				
				listaParticoes.add(p);
				
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return listaParticoes;
		
	}
	
	
	//Metodos de coleta
	private MemoriaColeta getOsMemory(){
		
		MemoriaColeta coleta = null;
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get os.memory"));
			//System.out.println(json.toString());
			
			coleta = new MemoriaColeta(this.servidor.getMemoria());
			
			//pega em bytes
			coleta.setDataColeta(this.dataColeta);
			coleta.setUsado(json.getLong("memUsed"));
			
			BigDecimal utilizacao = new BigDecimal((coleta.getUsado() / this.servidor.getMemoria().getTotalMemoria())*100);
			
			this.alarmeBO.geraAlarmeMemoria(servidor, utilizacao);
								
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return coleta;
	}
	
	private ProcessadorColeta getOsProcessor(){
		
		ProcessadorColeta coleta = null;
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get os.processor"));
			//System.out.println(json.toString());
			
			coleta = new ProcessadorColeta(this.servidor.getProcessador());
			
			//pega em bytes
			coleta.setDataColeta(this.dataColeta);
			
			JSONArray cores = json.getJSONArray("cpuUser");
			
			
			Double soma = 0d;
			
			for (Object core : cores) {
				
				Double i;
				
				//Corrige um bug de quando vem 0 de utiliza��o ser Integer.
				try{
					i = (Double) core;
					i = i * 100;
				}catch(ClassCastException ex){
					i = 0d;
				}
				
				soma += i;
			}
			
			Double media = soma / cores.size();
			
			coleta.setUsado(media);
			
			this.alarmeBO.geraAlarmeCpu(servidor,media);

		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return coleta;
	}
	
	private ParticaoColeta getOsPartition(Particao particao){
		
		ParticaoColeta coleta = null;
		
		try{
				
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get os.partition " + particao.getNome()));
			
			coleta = new ParticaoColeta(particao);
									
			//pega em bytes
			coleta.setDataColeta(this.dataColeta);
			coleta.setUsado(json.getLong("partitionUsed"));
			
			
			BigDecimal utilizacao = new BigDecimal( (coleta.getUsado()/particao.getTamanho()) * 100 );
			this.alarmeBO.geraAlarmeParticao(servidor, particao, utilizacao);
			
			
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
		return coleta;
		
	}
	
	
	
	
}
