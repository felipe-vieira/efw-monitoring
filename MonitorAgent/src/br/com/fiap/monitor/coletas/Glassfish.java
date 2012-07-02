package br.com.fiap.monitor.coletas;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Glassfish {
	 
	    private MBeanServerConnection mbsc = null;  
	 
	    public void getMemory(){
		    try{
		           
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
					System.out.println(heapCommitted);
					System.out.println(heapInit);
					System.out.println(heapMax);
					System.out.println(heapUsed);
					
					// Non Heap Memory
					System.out.println(nonHeapCommitted);
					System.out.println(nonHeapInit);
					System.out.println(nonHeapMax);
					System.out.println(nonHeapUsed);
					

		        } catch (Exception e) {

		            e.printStackTrace();

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
	    
	    public void getRuntime(){
	    	
	    	try{
		           
	            connect();                  

	            String query = "java.lang:type=Runtime";                        

		        ObjectName queryName = new ObjectName(query);
		        
		        String uptime = mbsc.getAttribute(queryName, "Uptime").toString();
		        String StartTime = mbsc.getAttribute(queryName, "StartTime").toString();
		        
		        System.out.println(uptime);
		        System.out.println(StartTime);
				
	    	 } catch (Exception e) {

		            e.printStackTrace();

		        }
	    	
	    }
	    
	    public void getThread(){
	    	
	    	try{
		           
	            connect();                  

	            String query = "java.lang:type=Threading";                        

		        ObjectName queryName = new ObjectName(query);
		        
		        String StartThreadCount = mbsc.getAttribute(queryName, "TotalStartedThreadCount").toString();
		        String ThreadCount = mbsc.getAttribute(queryName, "ThreadCount").toString();
		        String PeakThreadCount = mbsc.getAttribute(queryName, "PeakThreadCount").toString();
		        String DaemonThreadCount = mbsc.getAttribute(queryName, "DaemonThreadCount").toString();
		        String CurrentThreadUserTime = mbsc.getAttribute(queryName, "CurrentThreadUserTime").toString();
		        String CurrentThreadCpuTime = mbsc.getAttribute(queryName, "CurrentThreadCpuTime").toString();
		        
		        
		        System.out.println(StartThreadCount);
		        System.out.println(ThreadCount);
		        System.out.println(PeakThreadCount);
		        System.out.println(DaemonThreadCount);
		        System.out.println(CurrentThreadUserTime);
		        System.out.println(CurrentThreadCpuTime);
				
	    	 } catch (Exception e) {
		            e.printStackTrace();
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
