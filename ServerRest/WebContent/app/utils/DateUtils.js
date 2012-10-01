Ext.define("MONITOR.utils.DateUtils", {
	singleton : true,
	
	toStringPtBr : function (date){
		return Ext.Date.format(date, 'd/m/Y H:i:s');
	},
	
	toDatePtBr : function (date){
		return Ext.Date.format(date, 'd/m/Y');		
	},
	
	toHours : function (date){
		date = MONITOR.utils.DateUtils.offsetWorkaround(date);
		return Ext.Date.format(date,'H:i');
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

				d = Ext.Date.parse(parse, "H:i:s", true);
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
			
	},

	msToTime: function (secs){

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
			
	},
	
	offsetWorkaround: function(date){
		var local = new Date();
		
		if(date.getTimezoneOffset() != local.getTimezoneOffset()){
			var offsetDate = date.getTimezoneOffset(); 
			var offsetLocal = local.getTimezoneOffset();
		
			var diff = offsetDate - offsetLocal;
			var msDiff = (diff*60000);

			date.setTime(date.getTime() + msDiff);
			
		}
		
		return date;
	}
	
});
