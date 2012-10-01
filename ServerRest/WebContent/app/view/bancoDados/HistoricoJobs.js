Ext.define('MONITOR.view.bancoDados.HistoricoJobs', {
    extend: 'Ext.window.Window',
    xtype: 'historicojobs',
    autoShow: true,
    modal: true,
    width: 700,
    
    configs:{
    	store: null,
    },
    
    initComponent: function(){
    	
    	this.items = [
    	     {
    	    	  xtype: 'listhistoricojobs',
    	    	  border: false,
    	          frame: true,
    	          minHeight:270,
    	          store: this.store
    	    }
    	];
    	
    	
    	this.callParent(arguments);
    	
    }
    
});