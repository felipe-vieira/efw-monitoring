Ext.define('MONITOR.view.login.MainScreen', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.mainscreen',
    layout: 'border',
    id: "mainScreen",
    
    initComponent: function() {
    
	    this.items = [
	                  
	        {    
	        	region: 'north',
	            border: false,
	            layout: 'fit',
	            id: 'topoheader',
	            title: '<div style=\'height:50px;display:block\'><img src=\'resources/img/logo.png\'/></div>',
	            floating: false, 
	            renderTo: Ext.getBody(),
	            dockedItems: [{
	            	xtype: 'mainmenu'
	            }]
	        },	        
	        {
	        	title: 'Todos os nós',
	        	region: 'west',
	            xtype:'nolist',
	            width: '20%',
	            id: 'west-nolist',
	        	layout: 'fit',    
	        	split: true,
	        	collapsible: true,
	        },
	        {
	        	region: 'center',
	            xtype: 'maintab',
	        	border: true,
	        	collapsible: false
	        }
	    ];
	    
	    
	    this.callParent(arguments);
    }

});


