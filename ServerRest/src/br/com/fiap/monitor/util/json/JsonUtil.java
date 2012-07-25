package br.com.fiap.monitor.util.json;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static Map<String,Object> pegaFiltrosMapa(String filters){
		if(filters != null){
			String filtersJson = "{\"filters\":"+filters+"}";
			JSONObject json = JSONObject.fromObject(filtersJson);
			JSONArray filterArray = json.getJSONArray("filters");
			
			Map<String,Object> mapFilters = new HashMap<String, Object>();
			
			for(Object obj : filterArray){
				JSONObject i = (JSONObject) obj;
				String key = i.getString("property");
				Object value = i.get("value");
				mapFilters.put(key, value);
			}

			return mapFilters;		
			
		}else{
			return null;
		}
	} 
	
}
