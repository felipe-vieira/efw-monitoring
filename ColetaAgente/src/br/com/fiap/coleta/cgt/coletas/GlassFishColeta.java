package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.ServidorAplicacaoBO;
import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoriaColeta;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreadColeta;
import br.com.fiap.coleta.entities.enumerators.TipoMemoriaServidorAplicacao;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class GlassFishColeta {

	private Glassfish glassfish;
	private ServidorAplicacaoBO servidorAplicacaoBO;
	private SocketUtil socket;
	private Date dataColeta;
	
	public GlassFishColeta(No no){
		this.glassfish = (Glassfish) no;
		this.servidorAplicacaoBO = new ServidorAplicacaoBO();
	}
	
	public void initColeta(){
		
		this.dataColeta = new Date();
		
		socket = new SocketUtil(this.glassfish.getHostname(), 9090);
		
		try{
			socket.openSocket();
			
			// Verifica atraves da pagina padrao do Jboss se ele esta disponivel
			URL url = new URL("http://" + this.glassfish.getHostname());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			connection.disconnect();
						
			if (code == 200){
				this.glassfish.setDisponivel(true);
			}
			else{
				this.glassfish.setDisponivel(false);
			}
			
			//Pega as propriedades do coletor
			this.getGlassfishRuntime();
			List<ServidorAplicacaoMemoria> propriedadesMemorias = this.getConfigGlassfishMemory();
			
			//Pega os valores da coleta
			Map<String,ServidorAplicacaoDeployment> deployments = this.getGlassfishDeployments();
			List<ServidorAplicacaoMemoriaColeta> coletasMemorias = this.getGlassfishMemory();
			ServidorAplicacaoThreadColeta threadColeta = this.getGlassfishThread();
					
			// Verifica se as coletas estao vazias, caso estiverem ele nao esta gerenciavel
			if (deployments.isEmpty() || propriedadesMemorias.isEmpty() || coletasMemorias.isEmpty()){
				this.glassfish.setGerenciavel(false);
			}
			else{
				this.glassfish.setGerenciavel(true);
			}
			
			//Salva as propriedades
			servidorAplicacaoBO.updateServidorAplicacaoColeta(this.glassfish);
			servidorAplicacaoBO.salvaPropriedadesMemoria(propriedadesMemorias);
			
			//Salva as coletas
			servidorAplicacaoBO.salvaPropriedadesMemoria(propriedadesMemorias);
			servidorAplicacaoBO.salvaColetasMemoria(coletasMemorias);
			servidorAplicacaoBO.salvaColetasThread(threadColeta);
			servidorAplicacaoBO.salvaMapDeployments(deployments);
			
			// Ultima coleta
			this.glassfish.setUltimaColeta(dataColeta);
			
			socket.close();
			
		}catch(IOException ex){
			
			this.glassfish.setDisponivel(false);
			this.glassfish.setGerenciavel(false);
			this.glassfish.setUltimaColeta(dataColeta);
			this.servidorAplicacaoBO.updateServidorAplicacaoColeta(this.glassfish);
			
			System.out.println("Imposs�vel abrir o socket. Verifique se o agente est� instalado no servidor.");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private Map<String,ServidorAplicacaoDeployment> getGlassfishDeployments(){
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.deployment"));
			JSONArray jsonArray = json.getJSONArray("deployments");
			System.out.println(json.toString());
			
			Map<String,ServidorAplicacaoDeployment> deployments = this.servidorAplicacaoBO.getMapDeploymentsServidor(this.glassfish);
			
			if(deployments == null){
				deployments = new HashMap<String,ServidorAplicacaoDeployment>();
			}
			
			for(Object o:jsonArray){
				String deploymentName = (String) o;
				
				ServidorAplicacaoDeployment deployment = deployments.get(deploymentName);
				
				if(deployment==null){
					deployment = new ServidorAplicacaoDeployment();
					deployment.setNome(deploymentName);
					deployment.setServidorAplicacao(this.glassfish);
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
	
	private ServidorAplicacaoThreadColeta getGlassfishThread(){
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.thread"));
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
	
	private void getGlassfishRuntime(){
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.runtime"));
			
			Date startTime = new Date(json.getLong("startTime"));
			
			this.glassfish.setStartTime(startTime);
			this.glassfish.setUptime(json.getLong("uptime"));
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	private List<ServidorAplicacaoMemoria> getConfigGlassfishMemory(){
		try{		
			
			List<ServidorAplicacaoMemoria> retorno = new ArrayList<	ServidorAplicacaoMemoria>();
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.memory"));
						
			JSONObject jsonHeap = json.getJSONObject("heap");
			JSONObject jsonNonHeap = json.getJSONObject("nonHeap");
			
			ServidorAplicacaoMemoria memoriaHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.HEAP);
			
			if(memoriaHeap == null){
				memoriaHeap = new ServidorAplicacaoMemoria(this.glassfish);
			}
			memoriaHeap.setTipo(TipoMemoriaServidorAplicacao.HEAP);
			memoriaHeap.setInit(jsonHeap.getLong("init"));
			memoriaHeap.setMax(jsonHeap.getLong("max"));
			
			
			ServidorAplicacaoMemoria memoriaNonHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.NONHEAP);
			
			if(memoriaNonHeap == null){
				memoriaNonHeap = new ServidorAplicacaoMemoria(this.glassfish);
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
	
	private List<ServidorAplicacaoMemoriaColeta> getGlassfishMemory(){
		try{		
			
			List<ServidorAplicacaoMemoriaColeta> retorno = new ArrayList<	ServidorAplicacaoMemoriaColeta>();
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.memory"));
			
			JSONObject jsonHeap = json.getJSONObject("heap");
			JSONObject jsonNonHeap = json.getJSONObject("nonHeap");
			
			
			ServidorAplicacaoMemoria memoriaHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.HEAP);
			ServidorAplicacaoMemoria memoriaNonHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.NONHEAP);
			
			
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
