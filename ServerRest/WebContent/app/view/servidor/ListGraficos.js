Ext.define('MONITOR.view.servidor.ListGraficos', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.servidorgraficos',
	layout: {
		type: 'hbox',
		align: 'stretchmax'
	},
    minHeight: 400,
    config: {
    	idNo: null
    },
    
    initComponent: function() {
    	
    	var storeMetricas = Ext.create('Ext.data.Store', {
    	     model: 'MONITOR.model.Metrica',
    	     data : [
    	         {tipo: 'cpu', nome: 'CPU' },
    	         {tipo: 'memoria', nome: 'Memória'},
    	         {tipo: 'particoes', nome: 'Partições'}
    	     ]	
    	 });
    	
	    this.items = [
	        {
	        	xtype: 'listmetricas',
	        	width: '20%',
	        	minHeight: 400,
	        	store: storeMetricas
	        },
	        {
	        	
    	        xtype:'graficopadrao',
    	        width: '80%',
    	        minHeight: 400
	        		
	        }
	    	
	    ];
	    
	    this.callParent(arguments);
	    
    }

});