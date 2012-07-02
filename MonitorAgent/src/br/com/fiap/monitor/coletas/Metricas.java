package br.com.fiap.monitor.coletas;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import br.com.fiap.monitor.coletas.estrutura.ReturnObject;

public class Metricas {

	
	private Sigar sigar;
	
	public Metricas(){
		this.sigar = new Sigar();
	}

	
	/**
	 * Pega as informações de memoria do SO
	 * @author Felipe
	 * @return Objeto com os valores de memoria disponíveis
	 * @throws SigarException
	 */
	public ReturnObject getOsMemory() throws SigarException{
		ReturnObject retorno =  new ReturnObject();
		Mem memoria = this.sigar.getMem();
		
		retorno.putValue("memFree", memoria.getFree());
		retorno.putValue("memUsed", memoria.getUsed());
		retorno.putValue("memPercFree", memoria.getFreePercent());
		retorno.putValue("memPercUsed", memoria.getUsedPercent());
		
		return retorno;
	}
	
	/**
	 * Pega as informações de uso de processador
	 * @return Objeto com os valores de cpu
	 * @throws SigarException
	 */ 
	public ReturnObject getProcessor() throws SigarException{
		
		ReturnObject retorno =  new ReturnObject();
		CpuPerc[] cpuList = this.sigar.getCpuPercList();
		
		Double[] cpuCombinedList = new Double[cpuList.length];
		
		for (int i = 0 ; i <cpuList.length ; i++) {
			cpuCombinedList[i] = cpuList[i].getCombined();
		}
		
		retorno.putValue("cpuUser", cpuCombinedList);
		
		return retorno;
	}
	
	/**
	 * Pega as informações de uma partição
	 * @param Nome da partição
	 * @return Objeto com as infos da partição
	 * @throws SigarException
	 */
	
	public ReturnObject getPatitionInfo(String partition) throws SigarException{
		ReturnObject retorno =  new ReturnObject();
		
		retorno.putValue("partitionFree",  this.sigar.getFileSystemUsage(partition).getFree());
		retorno.putValue("partitionUsed",  this.sigar.getFileSystemUsage(partition).getUsed());
		retorno.putValue("partitionTotal", this.sigar.getFileSystemUsage(partition).getTotal());
				
		
		return retorno;
	}
	
}
