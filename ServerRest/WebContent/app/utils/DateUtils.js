Ext.define("MONITOR.utils.DateUtils", {
	singleton : true,
	
	toStringPtBr : function (date){
		return date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear() + " "  + 
			date.toLocaleTimeString();
		
	},
	
	toDatePtBr : function (date){
		return date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();		
	},
	
	toHours : function (date){
		return date.toLocaleTimeString().substring(0,5);
	},
	
	stringToTime : function (str){
		
		var arr= str.split(":");
		var error = false;
		var d = null;
		
		if(arr.length !=2){
			error = true;
		}else{
			
			if(isNaN(arr[0]) || arr[0] <0 || arr[0] >23 || isNaN(arr[1]) ||  arr[1]<0 || arr[1]>59){
				error = true;
			}else{
				var parse = arr[0]+':'+arr[1]+':'+'00';

				console.log(parse);
				d = Ext.Date.parse(parse, "H:i:s");
				console.log(d);
			}
		}
		
		if(error == true){
			
            Ext.MessageBox.show({
                title: 'Erro',
                msg: "Formato de hora inválido",
                icon: Ext.MessageBox.ERROR,
                buttons: Ext.Msg.OK
            });
            
			return null;
			
		}else{
			return d;
		}
		
		
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
