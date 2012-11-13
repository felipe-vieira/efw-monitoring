package br.com.fiap.monitor.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.fiap.coleta.entities.SlaCalculado;
import br.com.fiap.coleta.entities.enumerators.TipoSla;
import br.com.fiap.monitor.bo.SlaBO;
import br.com.fiap.monitor.to.PagingTO;

@Path("/slasCalculados")
public class SlasCalculadosRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PagingTO<SlaCalculado> pegaUltimoSlaCalulculado(@QueryParam("id") Long id,  @QueryParam("dataInicio") String strDataInicio,
			@QueryParam("dataFim") String strDataFim, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit,
			@QueryParam("tipo") String tipo){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		PagingTO<SlaCalculado> retorno = new PagingTO<SlaCalculado>();
		
		try{
			Date dataInicio = sdf.parse(strDataInicio);
			Date dataFim = sdf.parse(strDataFim);;
			
			SlaBO bo = new SlaBO();
			TipoSla tipoSla;
			
			if(tipo.equals("mensal")){
				tipoSla = TipoSla.MENSAL;
			}else{
				tipoSla = TipoSla.DIARIO;
			}
			
			List<SlaCalculado> records = bo.listaSlasCalculados(id, dataInicio, dataFim, start, limit, tipoSla);
			Long total = bo.countSlaCalculados(id, dataInicio, dataFim, tipoSla);
			
			retorno.setRecords(records);
			retorno.setTotal(total);
			retorno.setSuccess(true);
			
		}catch (ParseException e) {
			e.printStackTrace();
			retorno.setSuccess(false);
		}
		
		return retorno;
			
	}
	
}
