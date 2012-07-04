package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.ServidorAplicacaoBO;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoriaColeta;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreadColeta;
import br.com.fiap.coleta.entities.enumerators.TipoMemoriaServidorAplicacao;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class JBossColeta {

	private JBoss jboss;
	private ServidorAplicacaoBO servidorAplicacaoBO;
	private SocketUtil socket;
	private Date dataColeta;
	
	public JBossColeta(No no){
		this.jboss = (JBoss) no;
		this.servidorAplicacaoBO = new ServidorAplicacaoBO();
	}
	
	public void initColeta(){
		
		this.dataColeta = new Date();
		
		socket = new SocketUtil(this.jboss.getHostname(), 9090);
		
		try{
			socket.openSocket();
			
			//Pega as propriedades do coletor
			this.getJbossRuntime();
			List<ServidorAplicacaoMemoria> propriedadesMemorias = this.getConfigJbossMemory();
			
			//Pega os valores da coleta
			Map<String,ServidorAplicacaoDeployment> deployments = this.getJbossDeployments();
			List<ServidorAplicacaoMemoriaColeta> coletasMemorias = this.getJbossMemory();
			ServidorAplicacaoThreadColeta threadColeta = this.getJbossThread();
					
			
			//Salva as propriedades
			servidorAplicacaoBO.updateServidorAplicacaoColeta(this.jboss);
			servidorAplicacaoBO.salvaPropriedadesMemoria(propriedadesMemorias);
			
			//Salva as coletas
			servidorAplicacaoBO.salvaPropriedadesMemoria(propriedadesMemorias);
			servidorAplicacaoBO.salvaColetasMemoria(coletasMemorias);
			servidorAplicacaoBO.salvaColetasThread(threadColeta);
			servidorAplicacaoBO.salvaMapDeployments(deployments);
			
			socket.close();
			
		}catch(IOException ex){
			System.out.println("Imposs�vel abrir o socket. Verifique se o agente est� instalado no servidor.");
		}
	}
	
	private Map<String,ServidorAplicacaoDeployment> getJbossDeployments(){
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get jboss.deployment"));
			JSONArray jsonArray = json.getJSONArray("deployments");
			System.out.println(json.toString());
			
			Map<String,ServidorAplicacaoDeployment> deployments = this.servidorAplicacaoBO.getMapDeploymentsServidor(this.jboss);
			
			if(deployments == null){
				deployments = new HashMap<String,ServidorAplicacaoDeployment>();
			}
			
			for(Object o:jsonArray){
				String deploymentName = (String) o;
				
				ServidorAplicacaoDeployment deployment = deployments.get(deploymentName);
				
				if(deployment==null){
					deployment = new ServidorAplicacaoDeployment();
					deployment.setNome(deploymentName);
					deployment.setServidorAplicacao(this.jboss);
				}
				
				deployment.setAtivo(true);
				
				deployments.put(deploymentName, deployment);
			} 
			
			return deployments;
	
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	private ServidorAplicacaoThreadColeta getJbossThread(){
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get jboss.thread"));
			System.out.println(json.toString());
			ServidorAplicacaoThreadColeta threadColeta = new ServidorAplicacaoThreadColeta();
			
			threadColeta.setDataColeta(this.dataColeta);
			threadColeta.setThreadCount(json.getLong("count"));
			threadColeta.setCpuTime(json.getLong("cpuTime"));
			threadColeta.setUserTime(json.getLong("userTime"));
			
			return threadColeta;
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private void getJbossRuntime(){
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get jboss.runtime"));
			
			Date startTime = new Date(json.getLong("startTime"));
			
			this.jboss.setStartTime(startTime);
			this.jboss.setUptime(json.getLong("uptime"));
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	private List<ServidorAplicacaoMemoria> getConfigJbossMemory(){
		try{		
			
			List<ServidorAplicacaoMemoria> retorno = new ArrayList<	ServidorAplicacaoMemoria>();
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get jboss.memory"));
						
			JSONObject jsonHeap = json.getJSONObject("heap");
			JSONObject jsonNonHeap = json.getJSONObject("nonHeap");
			
			ServidorAplicacaoMemoria memoriaHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.jboss, TipoMemoriaServidorAplicacao.HEAP);
			
			if(memoriaHeap == null){
				memoriaHeap = new ServidorAplicacaoMemoria(this.jboss);
			}
			memoriaHeap.setTipo(TipoMemoriaServidorAplicacao.HEAP);
			memoriaHeap.setInit(jsonHeap.getLong("init"));
			memoriaHeap.setMax(jsonHeap.getLong("max"));
			
			
			ServidorAplicacaoMemoria memoriaNonHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.jboss, TipoMemoriaServidorAplicacao.NONHEAP);
			
			if(memoriaNonHeap == null){
				memoriaNonHeap = new ServidorAplicacaoMemoria(this.jboss);
			}
			
			memoriaNonHeap.setTipo(TipoMemoriaServidorAplicacao.NONHEAP);
			memoriaNonHeap.setInit(jsonNonHeap.getLong("init"));
			memoriaNonHeap.setMax(jsonNonHeap.getLong("max"));
			
			
			retorno.add(memoriaHeap);
			retorno.add(memoriaNonHeap);
			
			return retorno;
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	private List<ServidorAplicacaoMemoriaColeta> getJbossMemory(){
		try{		
			
			List<ServidorAplicacaoMemoriaColeta> retorno = new ArrayList<	ServidorAplicacaoMemoriaColeta>();
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get jboss.memory"));
			
			JSONObject jsonHeap = json.getJSONObject("heap");
			JSONObject jsonNonHeap = json.getJSONObject("nonHeap");
			
			
			ServidorAplicacaoMemoria memoriaHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.jboss, TipoMemoriaServidorAplicacao.HEAP);
			ServidorAplicacaoMemoria memoriaNonHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.jboss, TipoMemoriaServidorAplicacao.NONHEAP);
			
			
			if(memoriaHeap != null){
				ServidorAplicacaoMemoriaColeta coletaMemoriaHeap = new ServidorAplicacaoMemoriaColeta(memoriaHeap);
				
				coletaMemoriaHeap.setDataColeta(this.dataColeta);
				coletaMemoriaHeap.setUsed(jsonHeap.getLong("used"));
				coletaMemoriaHeap.setCommited(jsonHeap.getLong("committed"));
				
				retorno.add(coletaMemoriaHeap);
				
			}
					
			if(memoriaNonHeap != null){
				ServidorAplicacaoMemoriaColeta coletaMemoriaNonHeap = new ServidorAplicacaoMemoriaColeta(memoriaNonHeap);
				
				coletaMemoriaNonHeap.setDataColeta(this.dataColeta);
				coletaMemoriaNonHeap.setUsed(jsonNonHeap.getLong("used"));
				coletaMemoriaNonHeap.setCommited(jsonNonHeap.getLong("committed"));
				
				retorno.add(coletaMemoriaNonHeap);
				
			}
			
			return retorno;
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
}
