package br.com.fiap.monitor.coletas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import br.com.fiap.monitor.coletas.estrutura.ReturnObject;



public class Configuracoes {
	
	
	private Sigar sigar;
	private OperatingSystem os;
	
	
	public Configuracoes(){
		this.sigar = new Sigar();
		this.os = OperatingSystem.getInstance();
	}
	
	/**
	 * @author felipe
	 * Pega as informações do SO.
	 * @return Objeto com os valores de configuração
	 */
	public ReturnObject getConfigOs(){
		ReturnObject retorno =  new ReturnObject();
		
		retorno.putValue("vendor", this.os.getVendor());
		retorno.putValue("name", this.os.getName());
		retorno.putValue("descr", this.os.getDescription());
		retorno.putValue("arch", this.os.getArch());
		retorno.putValue("verdorVersion", this.os.getArch());
		retorno.putValue("version", this.os.getVersion());
		retorno.putValue("patch", this.os.getPatchLevel());
		
		
		return retorno;
	}
	
	
	/**
	 * @author Felipe
	 * Pega as informações de configuração da cpu.
	 * @return Objeto com os valores de configuração
	 */
	
	public ReturnObject getConfigProcessor() throws SigarException{
		ReturnObject retorno =  new ReturnObject();
		
		CpuInfo[] cpuList = this.sigar.getCpuInfoList();
		
		retorno.putValue("vendor", cpuList[0].getVendor());
		retorno.putValue("model", cpuList[0].getModel());
		retorno.putValue("freq", cpuList[0].getMhz());
		retorno.putValue("totalCores", cpuList[0].getTotalCores());
		retorno.putValue("procCount", cpuList.length);		

		return retorno;
		
	}
	
	/**
	 * @author Felipe
	 * Pega o total de memoria ram da maquina
	 * @return Objeto com os valores de configuração 
	 * @throws SigarException
	 */
	public ReturnObject getConfigMemory() throws SigarException{
		ReturnObject retorno =  new ReturnObject();
		
		retorno.putValue("total", this.sigar.getMem().getTotal());

		return retorno;
		
	}
	
	/**
	 * Pega a lista de partições da maquina
	 * @author Felipe
	 * @return Objeto com os valores de configuração 
	 * @throws SigarException
	 */
	public ReturnObject getConfigPartitions() throws SigarException{
		ReturnObject retorno =  new ReturnObject();
		
		FileSystem[] fileSystems = this.sigar.getFileSystemList();
		List<HashMap<String, Object>>  retornoParticoes = new ArrayList<HashMap<String,Object>>();
		
		for (FileSystem fileSystem : fileSystems) {
			//if(!fileSystem.getTypeName().equals("cdrom")){
			if(fileSystem.getTypeName().equalsIgnoreCase("local") || fileSystem.getTypeName().equalsIgnoreCase("swap")){
				HashMap<String, Object> particao = new HashMap<String, Object>();
				particao.put("name", fileSystem.getDirName());
				particao.put("fileSystem",fileSystem.getSysTypeName());
				particao.put("totalSize",this.sigar.getFileSystemUsage(fileSystem.getDirName()).getTotal());
				
				retornoParticoes.add(particao);
			}
		}
		
		JSONArray json = JSONArray.fromObject(retornoParticoes);
		retorno.putValue("partitions", json);
		return retorno;
		
	}
	
	
	
	
}
