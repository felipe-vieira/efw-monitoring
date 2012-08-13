Ext.define("MONITOR.utils.DateUtils", {
	singleton : true,
	
	toStringPtBr : function (date){
		
		return date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear() + " "  + 
			date.getHours() + ":" + date.getMinutes(); 
		
	}
	
});
