package br.com.fiap.monitor.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;


import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.monitor.bo.AlarmeBO;

@Path("/alarmesNos")
public class AlarmesNoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getAlarmesNo(@PathParam("id") Integer id){
		AlarmeBO alarmeBO = new AlarmeBO();
		JSONObject json = new JSONObject();
		
		List<Alarme> alarmes = alarmeBO.listaAlarmesNo(id);
		json.put("alarmes", alarmes);
		
		return json.toString();		
							
	} 
	
}
