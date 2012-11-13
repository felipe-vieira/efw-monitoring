package br.com.fiap.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.AvaliacaoSolucao;
import br.com.fiap.monitor.bo.AvaliacaoSolucaoBO;
import br.com.fiap.monitor.to.ReturnTO;

@Path("/avaliacaoSolucao")
public class AvaliacaoSolucaoRest {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnTO saveAvalicacaoSolucao(AvaliacaoSolucao avaliacao, @QueryParam("idUsuario") Long idUsuario,
			@QueryParam("idSolucao") Long idSolucao){
		
		AvaliacaoSolucaoBO avaliacaoBO = new AvaliacaoSolucaoBO();
		return avaliacaoBO.salvaAvaliacaoSolucao(avaliacao,idUsuario,idSolucao);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AvaliacaoSolucao getAvaliacaoSolucaoUsuario(@QueryParam("idUsuario") Long idUsuario,
			@QueryParam("idSolucao") Long idSolucao){
		
		AvaliacaoSolucaoBO avaliacaoBO = new AvaliacaoSolucaoBO();
		return avaliacaoBO.pegaAvaliacaoUsuario(idUsuario, idSolucao);
		
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ReturnTO deleteAvaliacaoSolucao(@PathParam("id") Long id){
		
		AvaliacaoSolucaoBO avaliacaoBO = new AvaliacaoSolucaoBO();
		return avaliacaoBO.deletaAvaliacao(id);
		
	}
}

