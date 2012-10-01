Ext.define("MONITOR.utils.ConvertUtils", {
	singleton : true,
	
	
	convertMb : function(mb) {
		
		if(mb > 1048576){
			return MONITOR.utils.ConvertUtils.roundDecimal(mb/1048576) + ' TB';
		}else if(mb > 1024){
			return MONITOR.utils.ConvertUtils.roundDecimal(mb/1024) + ' GB';
		}else{
			return mb + " MB";
		}
		
	},
	
	convertKb : function(kb) {
		if(kb > 1073741824){
			return MONITOR.utils.ConvertUtils.roundDecimal(kb/1073741824) + ' TB';
		}else if(kb > 1048576){
			return MONITOR.utils.ConvertUtils.roundDecimal(kb/1048576) + ' GB';
		}else if(kb > 1024){
			return MONITOR.utils.ConvertUtils.roundDecimal(kb/1024) + ' MB';
		}else{
			return kb + " KB";
		}
	},
	
	convertB : function(b) {
		if(b > 1099511627776){
			return MONITOR.utils.ConvertUtils.roundDecimal(b/1099511627776) + ' TB';
		}else if(b > 1073741824){
			return MONITOR.utils.ConvertUtils.roundDecimal(b/1073741824) + ' GB';
		}else if(b > 1048576){
			return MONITOR.utils.ConvertUtils.roundDecimal(b/1048576) + ' MB';
		}else if(b > 1024){
			return MONITOR.utils.ConvertUtils.roundDecimal(b/1024) + ' KB';
		}else{
			return b + " B";
		}
	},
	
	roundDecimal: function(number){
		return Math.round(number*100)/100;		
	}
});
