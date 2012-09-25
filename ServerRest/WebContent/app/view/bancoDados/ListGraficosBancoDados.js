Ext.define('MONITOR.view.bancoDados.ListGraficosBancoDados', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.bancodadosgraficos',
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
    	         {nome: 'Arquivos', tipo: 'arquivos' },
    	         {nome: 'Jobs', tipo: 'jobs'},
    	         {nome: 'Memória', tipo: 'memoria'},
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