package br.com.fiap.monitor.coletas;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.monitor.coletas.estrutura.ReturnObject;
import br.com.fiap.monitor.utils.RESTUtils;


public class Glassfish {
	 
	    private MBeanServerConnection mbsc = null;  
	 
	    public ReturnObject getMemory(){
	    	
		    try{
		           	ReturnObject ro = new ReturnObject();
		           
		            connect();                  

		            String query = "java.lang:type=Memory";                        
		            
			        ObjectName queryName = new ObjectName(query);
			        
			        CompositeData heap = (CompositeData) mbsc.getAttribute(queryName, "HeapMemoryUsage");
			        
			        String heapCommitted = heap.get("committed").toString();
			        
			        String heapInit = heap.get("init").toString();
					
			        String heapMax = heap.get("max").toString();
					
					String heapUsed = heap.get("used").toString();
					
					CompositeData nonHeap = (CompositeData) mbsc.getAttribute(queryName, "NonHeapMemoryUsage");
			        
			        String nonHeapCommitted = nonHeap.get("committed").toString();
			        
			        String nonHeapInit = nonHeap.get("init").toString();
					
			        String nonHeapMax = nonHeap.get("max").toString();
					
					String nonHeapUsed = nonHeap.get("used").toString();

					// Heap Memory
					Map<String,String> heapMemory = new HashMap<String, String>();
					heapMemory.put("committed",heapCommitted);
					heapMemory.put("init",heapInit);
					heapMemory.put("max",heapMax);
					heapMemory.put("used",heapUsed);
					
					Map<String,String> nonHeapMemory = new HashMap<String, String>();
					nonHeapMemory.put("committed",nonHeapCommitted);
					nonHeapMemory.put("init",nonHeapInit);
					nonHeapMemory.put("max",nonHeapMax);
					nonHeapMemory.put("used",nonHeapUsed);	
					
					ro.putValue("heap", heapMemory);
					ro.putValue("nonHeap", nonHeapMemory);
					
					return ro;					
					
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
	    }
	    
	    public void getCompilation(){
	    	
	    	try{
		           
	            connect();                  

	            String query = "java.lang:type=Compilation";                        

		        ObjectName queryName = new ObjectName(query);
		        
		        String time = mbsc.getAttribute(queryName, "TotalCompilationTime").toString();
		        
		        System.out.println(time);
				
	    	 } catch (Exception e) {
		            e.printStackTrace();
		     }
	    	
	    }
	    
	    public ReturnObject getRuntime(){
	    	
	    	try{
		        
	    		ReturnObject ro = new ReturnObject();
	            connect();                  

	            String query = "java.lang:type=Runtime";                        

		        ObjectName queryName = new ObjectName(query);
		        
		        Long uptime = (Long) mbsc.getAttribute(queryName, "Uptime");
		        Long startTime = (Long) mbsc.getAttribute(queryName, "StartTime");
		        
		        ro.putValue("uptime",uptime);
		        ro.putValue("startTime",startTime);
				
		        return ro;
		        
	    	 } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		     }
	    	
	    }
	    
	    public ReturnObject getThread(){
	    	
	    	try{
		           
	    		ReturnObject ro = new ReturnObject();
	    		
	            connect();                  

	            String query = "java.lang:type=Threading";                        

		        ObjectName queryName = new ObjectName(query);
		        
		        String threadCount = mbsc.getAttribute(queryName, "ThreadCount").toString();
		        String currentThreadUserTime = mbsc.getAttribute(queryName, "CurrentThreadUserTime").toString();
		        String currentThreadCpuTime = mbsc.getAttribute(queryName, "CurrentThreadCpuTime").toString();
		        
				ro.putValue("count", threadCount);
				ro.putValue("cpuTime", currentThreadUserTime);
				ro.putValue("userTime", currentThreadCpuTime);
				
				return ro;
				
		        /*		         
		        String StartThreadCount = mbsc.getAttribute(queryName, "TotalStartedThreadCount").toString();
		        String PeakThreadCount = mbsc.getAttribute(queryName, "PeakThreadCount").toString();
		        String DaemonThreadCount = mbsc.getAttribute(queryName, "DaemonThreadCount").toString();
		        
		        System.out.println(StartThreadCount);
		        System.out.println(ThreadCount);
		        System.out.println(PeakThreadCount);
		        System.out.println(DaemonThreadCount);
		        System.out.println(CurrentThreadUserTime);
		        System.out.println(CurrentThreadCpuTime);
				*/
		        
	    	 } catch (Exception e) {
	    		 e.printStackTrace();
		         return null;   
		     }
	    	
	    }

	    public ReturnObject getDeployments(){
	    	
	    	ReturnObject ro = new ReturnObject();
	    	
	    	try{
	    		
	    		String stringJson = RESTUtils.getInformation("/domain/applications/list-applications");
	    		JSONObject json = JSONObject.fromObject(stringJson).getJSONObject("properties");
	    		System.out.println(json.toString());
	    		
	    		Set<String> deployments = json.keySet();
	    		
	    		ro.putValue("deployments", JSONArray.fromObject(deployments));
	    		
	    		return ro;
	    			    		
				
	    	 } catch (Exception e) {
	    		e.printStackTrace();
		        return ro;
		     }
	    	
	    }
	    
	    
	    
	    private void connect() throws Exception {

	        JMXServiceURL jmxUrl =

	                new JMXServiceURL("service:jmx:rmi://localhost:8686/jndi/rmi://localhost:8686/jmxrmi");

	        Map<String,Object> env = new HashMap<String, Object>();
	        
	        //TODO parametrizar.
	        String[] credentials = new String[]{"admin", ""};

	        env.put(JMXConnector.CREDENTIALS, credentials);              

	        JMXConnector jmxc =

	                JMXConnectorFactory.connect(jmxUrl, env);            

	        mbsc = jmxc.getMBeanServerConnection();                    

	    }
}
