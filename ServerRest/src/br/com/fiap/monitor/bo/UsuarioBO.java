package br.com.fiap.monitor.bo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.coleta.entities.Usuario;
import br.com.fiap.monitor.dao.GenericDAO;
import br.com.fiap.monitor.dao.UsuarioDAO;
import br.com.fiap.monitor.to.ReturnTO;

public class UsuarioBO {

	
	private UsuarioDAO usuarioDAO;
	private GenericDAO dao;
	
	public UsuarioBO(){
		this.usuarioDAO = new UsuarioDAO();
		this.dao = new GenericDAO();
	}
	
	public Usuario validaUsuario(String login, String senha) {
		return this.usuarioDAO.validaUsuario(login,senha);
	}
	
	public List<Usuario> listaUsuarios(Integer start, Integer limit){

		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Query query = session.createQuery("FROM Usuario WHERE ativo = :ativo");
			query.setBoolean("ativo", true);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<Usuario> list = dao.queryList(query);
			t.commit();
			return list;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
		
	}
	
	public Long contaUsuarios(){
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		
		try{			
			Query query = session.createQuery("SELECT count(*) FROM Usuario");
			
			Long retorno =  (Long) query.uniqueResult();
			t.commit();
			
			return retorno;
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			return null;
		}
	}

	public ReturnTO saveUsuario(Usuario usuario) {
		
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		
		retorno.setSuccess(false);
		
		try{
			
			if(usuario.getLogin() == null || usuario.getLogin().equals("")){
				retorno.setMessage("O campo usuário é obrigatório");
				t.rollback();
			}else if(usuario.getSenha() == null || usuario.getSenha().equals("")){
				retorno.setMessage("O campo senha é obrigatório");
				t.rollback();
			}else if(usuario.getEnviarEmail() && (usuario.getEmail() == null || usuario.getEmail().equals(""))){
				retorno.setMessage("O campo e-mail é obrigatório.");
				t.rollback();
			}else if(this.verificaUsuarioLogin(usuario.getLogin())){
				retorno.setMessage("Já existe um usuário com esse login.");
				t.rollback();
			}else{
				usuario.setAtivo(true);
				dao.save(usuario);
				t.commit();
				retorno.setSuccess(true);
			}	
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			retorno.setSuccess(false);
			retorno.setMessage(ex.getMessage());
		}
		
		return retorno;
		
	}
	
	public ReturnTO updateUsuario(Long id, Usuario usuario) {
		
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		
		try{
			Usuario old = (Usuario) dao.getById(Usuario.class, id);
						
			if(old.getLogin().equals(usuario.getLogin()) || !this.verificaUsuarioLogin(usuario.getLogin())){
				
				dao.update(usuario);
				retorno.setSuccess(true);
			}else{
				retorno.setSuccess(false);
				retorno.setMessage("Já existe um usuário com esse login.");
			}	
			
			t.commit();
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			retorno.setSuccess(false);
			retorno.setMessage(ex.getMessage());
		}
		
		return retorno;
		
	}
	
	public ReturnTO desativaUsuario(Usuario usuario){
		Session session = dao.getSession();
		Transaction t = session.beginTransaction();
		ReturnTO retorno = new ReturnTO();
		
		try{				
			usuario.setAtivo(false);
			dao.saveOrUpdate(usuario);
			retorno.setSuccess(true);
			t.commit();
			
		}catch(Exception ex){
			ex.printStackTrace();
			t.rollback();
			retorno.setSuccess(false);
			retorno.setMessage(ex.getMessage());
		}
		
		return retorno;
	}
	
	public Boolean verificaUsuarioLogin(String login){
		
		Session session = dao.getSession();
		
		try{
			Query query = session.createQuery("FROM Usuario WHERE login = :login");
			query.setString("login", login);
			List<Usuario> list = dao.queryList(query);
			
			if(list != null && list.size() > 0){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
	}
	

}
