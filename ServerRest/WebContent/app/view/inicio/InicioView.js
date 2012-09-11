Ext.define('MONITOR.view.inicio.InicioView', {
    extend: 'Ext.panel.Panel',
    xtype: 'inicioview',
    padding: 10,
    border: 0,
    autoScroll: 'auto',
    
    initComponent: function(){
    	
    	
    	this.items = [
    	
    	     
    	    {
    	    	padding: 5,
    	    	width: '100%',
    	    	layout: 'column',
    	    	items:[
    	    		{
    	    			xtype: 'listnosindisponiveis',
    	    			columnWidth: '0.5'
    	    		},
    	    		{
    	    			xtype: 'listnosnaogerenciaveis',
    	    			columnWidth: '0.5'
    	    		}
    	    	]
    	    },
    	    {
    	    	padding: 5,
    	    	title: "Abaixo da Meta - SLA Mensal",
    	    	width: '100%',
    	    },
    	    {
    	    	padding: 5,
    	    	title: "Alarmes não lidos",
    	    	width: '100%',
    	    },
    	              
    	     
    	              
    	];
    	
    		
    	
    	
    	
    	this.callParent(arguments);
    }

});