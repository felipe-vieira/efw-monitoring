package br.com.fiap.monitor.coletas;


import java.net.InetAddress;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

import br.com.fiap.monitor.coletas.estrutura.ReturnObject;


public class JBoss {

	private ModelControllerClient client;
	private String host = "localhost";
	private int port = 9999;
	
	public ReturnObject getMemory(){
		
			ReturnObject retorno = new ReturnObject();
		
			try{
				
				client = ModelControllerClient.Factory.create(InetAddress.getByName(host), port);
				ModelNode opHeap = new ModelNode();
				opHeap.get("operation").set("read-attribute");
				opHeap.get("name").set("heap-memory-usage");
				ModelNode addressHeap = opHeap.get("address");
				addressHeap.add("core-service", "platform-mbean");
				addressHeap.add("type", "memory");
				
				ModelNode returnHeap = client.execute(opHeap);
				
				ModelNode opNonHeap = new ModelNode();
				opNonHeap.get("operation").set("read-attribute");
				opNonHeap.get("name").set("non-heap-memory-usage");
				ModelNode addressNonHeap = opNonHeap.get("address");
				addressNonHeap.add("core-service", "platform-mbean");
				addressNonHeap.add("type", "memory");
				
				ModelNode returnNonHeap = client.execute(opNonHeap);
				
				retorno.putValue("heap", returnHeap.get("result").toJSONString(true));
				retorno.putValue("nonHeap", returnNonHeap.get("result").toJSONString(true));
								
				
				return retorno;
				
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
	
	public ReturnObject getThread(){
		
		ReturnObject retorno = new ReturnObject();
		
		try{
			client = ModelControllerClient.Factory.create(InetAddress.getByName(host), port);
			ModelNode opCount = new ModelNode();
			opCount.get("operation").set("read-attribute");
			opCount.get("name").set("thread-count");
			ModelNode addressCount = opCount.get("address");
			addressCount.add("core-service", "platform-mbean");
			addressCount.add("type", "threading");
			
			ModelNode returnCount = client.execute(opCount);
			
			ModelNode opCPUTime = new ModelNode();
			opCPUTime.get("operation").set("read-attribute");
			opCPUTime.get("name").set("current-thread-cpu-time");
			ModelNode addressCPUTime = opCPUTime.get("address");
			addressCPUTime.add("core-service", "platform-mbean");
			addressCPUTime.add("type", "threading");
			
			ModelNode returnCPUTime = client.execute(opCPUTime);
			
			ModelNode opUserTime = new ModelNode();
			opUserTime.get("operation").set("read-attribute");
			opUserTime.get("name").set("current-thread-user-time");
			ModelNode addressUserTime = opUserTime.get("address");
			addressUserTime.add("core-service", "platform-mbean");
			addressUserTime.add("type", "threading");
			
			ModelNode returnUserTime = client.execute(opUserTime);
						
			retorno.putValue("count", returnCount.get("result").toJSONString(true));
			retorno.putValue("cpuTime", returnCPUTime.get("result").toJSONString(true));
			retorno.putValue("userTime", returnUserTime.get("result").toJSONString(true));
			
			return retorno;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ReturnObject getRuntime(){
		
		ReturnObject retorno = new ReturnObject();
		
		try{
			client = ModelControllerClient.Factory.create(InetAddress.getByName(host), port);
			ModelNode opStartTime = new ModelNode();
			opStartTime.get("operation").set("read-attribute");
			opStartTime.get("name").set("start-time");
			ModelNode addressStartTime = opStartTime.get("address");
			addressStartTime.add("core-service", "platform-mbean");
			addressStartTime.add("type", "runtime");
			
			ModelNode returnStartTime = client.execute(opStartTime);
			
			
			ModelNode opUptime = new ModelNode();
			opUptime.get("operation").set("read-attribute");
			opUptime.get("name").set("uptime");
			ModelNode addressUptime = opUptime.get("address");
			addressUptime.add("core-service", "platform-mbean");
			addressUptime.add("type", "runtime");
			
			ModelNode returnUptime = client.execute(opUptime);
			
			retorno.putValue("startTime",returnStartTime.get("result").toJSONString(true));
			retorno.putValue("uptime",returnUptime.get("result").toJSONString(true));
						
			return retorno;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ReturnObject getStatus(){
		
		ReturnObject retorno = new ReturnObject();
		
		try{
			client = ModelControllerClient.Factory.create(InetAddress.getByName(host), port);
			ModelNode opStatus = new ModelNode();
			opStatus.get("operation").set("read-attribute");
			opStatus.get("name").set("server-state");
						
			ModelNode returnStatus = client.execute(opStatus);
			
			retorno.putValue("status",returnStatus.get("result").toJSONString(true));
			
			return retorno;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public ReturnObject getDeployments(){
		
		ReturnObject retorno = new ReturnObject();
		
		try{
			client = ModelControllerClient.Factory.create(InetAddress.getByName(host), port);
			ModelNode opDeployment = new ModelNode();
			opDeployment.get("operation").set("read-children-names");
			opDeployment.get("child-type").set("deployment");
						
			ModelNode returnDeployment = client.execute(opDeployment);
			
			retorno.putValue("deployments",returnDeployment.get("result").toJSONString(true));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return retorno;
	}

}
