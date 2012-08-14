Ext.define("MONITOR.utils.DateUtils", {
	singleton : true,
	
	toStringPtBr : function (date){
		
		return date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear() + " "  + 
			//date.getHours() + ":" + date.getMinutes();
			date.toLocaleTimeString();
		
	}
	
});
