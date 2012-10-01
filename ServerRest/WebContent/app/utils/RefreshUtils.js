Ext.define('MONITOR.utils.RefreshUtils', {
    singleton: true,
    
	statics:{
    	refresh: null,
    	grid: null,
    },
    
    setRefresh: function(grid) {
    	
    	clearTimeout(MONITOR.utils.RefreshUtils.refresh);
    	
    	MONITOR.utils.RefreshUtils.grid = grid;
    	
    	MONITOR.utils.RefreshUtils.refresh = setTimeout('MONITOR.utils.RefreshUtils.doRefresh()',60000);
    	
    },
    
    doRefresh: function() {
    	
    	clearTimeout(MONITOR.utils.RefreshUtils.refresh);
    	
    	MONITOR.utils.RefreshUtils.grid.getStore().reload();
    	
    	MONITOR.utils.RefreshUtils.refresh = setTimeout('MONITOR.utils.RefreshUtils.doRefresh()',60000);
    	
    }
    
});