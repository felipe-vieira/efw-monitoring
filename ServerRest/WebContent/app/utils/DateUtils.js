Ext.define("MONITOR.utils.DateUtils", {
	singleton : true,
	
	toStringPtBr : function (date){
		
		return date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear() + " "  + 
			date.toLocaleTimeString();
		
	},
	
	secsToTime: function (secs){
		
		var dias = 0;
		var horas = 0;
		var minutos = 0;
		var segundos = 0;
		var retorno = "";
		
		if(secs > 86400){
			dias = Math.floor(secs/86400);
			secs = secs % 86400;
		}
		
		if(secs > 3600){
			horas = Math.floor(secs/3600);
			secs = secs % 3600;
		}
		
		if(secs > 60){
			minutos = Math.floor(secs/60);
			secs = secs % 60;
		}
		
		if(dias > 0){
			retorno += dias + "d ";
		}
		
		if(horas > 0){
			retorno += horas + "h ";
		}
		
		retorno += minutos+"m ";
		retorno += segundos+"s ";
		
		return retorno;
			
	}
	
});
