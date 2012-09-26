Ext.define('MONITOR.view.servidorAplicacao.ListGraficosServidorAplicacao', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.servidoraplicacaograficos',
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
    	         {nome: 'CPU', tipo: 'cpu'},
    	         {nome: 'Memória Heap', tipo: 'heap'},
    	         {nome: 'Memória Non-Heap', tipo: 'nonheap'}
    	     ]	
    	 });
    	
	    this.items = [
	        {
	        	xtype: 'listmetricas',
	        	width: '15%',
	        	minHeight: 400,
	        	store: storeMetricas
	        },
	        {
	        	
    	        xtype:'graficopadrao',
    	        width: '85%',
    	        minHeight: 400
	        		
	        }
	    	
	    ];
	    
	    this.callParent(arguments);
	    
    }

});