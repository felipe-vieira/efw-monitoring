Ext.define('MONITOR.view.inicio.InicioView', {
    extend: 'Ext.panel.Panel',
    xtype: 'inicioview',
    padding: 10,
    border: 0,
    autoScroll: true,
    
    initComponent: function(){
    	
    	
    	this.items = [
    	     
    	    {
    	    	padding: 5,
    	    	width: '100%',
    	    	layout: {
    	    		type: 'hbox',
    	    		align: 'stretchmax'
    	    	},
    	    	items:[
    	    		{
    	    			xtype: 'listnosindisponiveis',
    	    			flex:1
    	    		},
    	    		{
    	    			xtype: 'listnosnaogerenciaveis',
    	    			flex:1
    	    		}
    	    	]
    	    },
    	    {
    	    	padding: 5,
    	    	xtype: 'listslasforameta',
    	    	width: '100%'
    	    },
    	    {
    	    	padding: 5,
    	    	xtype: 'listalarmesnaolidos',
    	    	width: '100%'
    	    }
    	              
    	     
    	              
    	];
    	
    		
    	
    	
    	
    	this.callParent(arguments);
    }

});