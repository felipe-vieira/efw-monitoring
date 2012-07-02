package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.ServidorAplicacaoBO;
import br.com.fiap.coleta.entities.JBoss;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoriaColeta;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreadColeta;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class JBossColeta {

	private JBoss jboss;
	private ServidorAplicacaoBO servidorAplicacaoBO;
	private SocketUtil socket;
	private ServidorAplicacaoMemoriaColeta memoriaColeta;
	private Date dataColeta;
	
	public JBossColeta(No no){
		this.jboss = (JBoss) no;
		this.servidorAplicacaoBO = new ServidorAplicacaoBO();
	}
	
	public void initColeta(){
		
		
		System.out.println("coletou");
		this.dataColeta = new Date();
		
		socket = new SocketUtil(this.jboss.getHostname(), 9090);
		
		try{
			socket.openSocket();
			
			List<ServidorAplicacaoDeployment> deployments = this.getJbossDeployments();
			ServidorAplicacaoThreadColeta threadColeta = this.getJbossThread();
			this.getJbossRuntime();			
			
			
			//Salva os objetos coletados.
			servidorAplicacaoBO.updateServidorAplicacaoColeta(this.jboss);
			//TODO MEMORY + SALVAR TUDO
			
		}catch(IOException ex){
			System.out.println("Impossível abrir o socket. Verifique se o agente está instalado no servidor.");
		}
	}
	
	private List<ServidorAplicacaoDeployment> getJbossDeployments(){
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get jboss.deployment"));
			JSONArray jsonArray = json.getJSONArray("deployments");
			System.out.println(json.toString());
			
			List<ServidorAplicacaoDeployment> deployments = new ArrayList<ServidorAplicacaoDeployment>();
			
			for(Object o:jsonArray){
				String deploymentName = (String) o;
				
				ServidorAplicacaoDeployment deployment = new ServidorAplicacaoDeployment();
				deployment.setNome(deploymentName);				
				deployments.add(deployment);
				
				System.out.println(deploymentName);
				
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
	
	
	//TODO ver como faz enum no hibernate
	private void getJbossMemory(){
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get jboss.memory"));
						
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
