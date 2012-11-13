package br.com.fiap.coleta.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.coleta.dao.BancoDadosDAO;
import br.com.fiap.coleta.dao.ColetaDAO;
import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.BancoFile;
import br.com.fiap.coleta.entities.BancoFileColeta;
import br.com.fiap.coleta.entities.BancoJob;
import br.com.fiap.coleta.entities.BancoJobColeta;
import br.com.fiap.coleta.entities.BancoMemoriaColeta;

public class BancoDadosBO {

	private BancoDadosDAO bancoDadosDAO;
	private ColetaDAO coletaDAO;
	
	public BancoDadosBO(){
		this.bancoDadosDAO = new BancoDadosDAO();
		this.coletaDAO = new ColetaDAO();
	}
	
	public Map<String,BancoFile> pegaMapFilesBancoDados(BancoDados bd) {
		
		List<BancoFile> listaFiles = this.bancoDadosDAO.listaFilesBancoDados(bd);
		Map<String, BancoFile> map = null;
		
		if(listaFiles != null && listaFiles.size() > 0){
			map = new HashMap<String, BancoFile>();
			for (BancoFile file : listaFiles) {
				file.setAtivo(false);
				map.put(file.getFile(), file);
			}
		}
		
		return map;
	}
	
	public Map<String,BancoJob> pegaMapJobsBancoDados(BancoDados bd) {
		
		List<BancoJob> listaJobs = this.bancoDadosDAO.listaJobsBancoDados(bd);
		Map<String, BancoJob> map = null;
		
		if(listaJobs != null && listaJobs.size() > 0){
			map = new HashMap<String, BancoJob>();
			for (BancoJob job : listaJobs) {
				map.put(job.getJobName(), job);
			}
		}
		
		return map;
	}

	public Boolean verificaColetaJobSalva(BancoJob job, Long logId) {
		BancoJobColeta coleta = this.bancoDadosDAO.pegaColetaJobLogId(job,logId);
		if(coleta != null){
			return true;
		}else{
			return false;
		}
	}

	public void salvaConfigFiles(Map<String, BancoFile> files) {
		this.coletaDAO.salvaMapColeta(files);
	}

	public void salvaConfigBackups(List<BancoBackup> backups) {
		this.coletaDAO.salvaListaColetas(backups);		
	}

	public void salvaConfigJobs(Map<String, BancoJob> jobs) {
		this.coletaDAO.salvaMapColeta(jobs);		
	}

	public void salvaColetaMemoria(BancoMemoriaColeta memoriaColeta) {
		this.coletaDAO.salvaColeta(memoriaColeta);		
	}

	public void salvaColetasFiles(List<BancoFileColeta> filesColeta) {
		this.coletaDAO.salvaListaColetas(filesColeta);		
	}

	public void salvaColetasJobs(List<BancoJobColeta> jobsColeta) {
		this.coletaDAO.salvaListaColetas(jobsColeta);
	}

	public void salvaBanco(BancoDados bd) {
		this.coletaDAO.salvaColeta(bd);
	}
	
	public Long pegaUltimoSetBackup(BancoDados bd) {
		return this.bancoDadosDAO.pegaUltimoSetBackup(bd);		
	}
	
	public BancoBackup pegaUltimoBackup(BancoDados bd){
		return this.bancoDadosDAO.pegaUltimoBackup(bd);
	}

}
