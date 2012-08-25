package br.com.fiap.monitor.bo;

import java.util.List;

import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.dao.NoDAO;
import br.com.fiap.coleta.entities.No;

public class NoBO {

	private NoDAO noDao;
	private GenericDAO genericDao;
	
	public NoBO(){
		this.noDao = new NoDAO();
		this.genericDao = new GenericDAO();
	}
	
	public List<No> listAllNos(){
		return this.noDao.listAllNos();
	}

	public No getNoId(Integer id) {
		return this.noDao.getNoById(id);
	}

	public void saveOrUpdateNo(No no) {
		this.noDao.saveOrUpdateNo(no);
	}
	

	
	
}
