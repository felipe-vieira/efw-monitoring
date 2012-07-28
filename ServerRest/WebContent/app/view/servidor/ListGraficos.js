Ext.define('MONITOR.view.servidor.ListGraficos', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.servidorgraficos',
    layout: 'column',
    height: 400,
    title: 'Informações de coletas',
    
    initComponent: function() {
    
	    this.items = [
	        {
	        	title: 'Metricas',
	        	columnWidth: 0.2	        	
	        },
	        {
	        	title: 'Graficos',
	        	columnWidth: 0.8
	        		
	        }
	    	
	    ];
	    
	    this.callParent(arguments);
    }

});