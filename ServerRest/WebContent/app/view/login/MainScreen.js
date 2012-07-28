Ext.define('MONITOR.view.login.MainScreen', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.mainscreen',
    layout: 'border',
    initComponent: function() {
    
	    this.items = [
	                  
	        {    
	        	region: 'north',
	            border: false,
	            layout: 'fit',
	            title: 'EFW Monitoring',
	            floating: false,  // usually you want this set to True (default)
	            renderTo: Ext.getBody(),  // usually rendered by it's containing component
	            dockedItems: [{
	            xtype: 'toolbar',
	            dock: 'top',
	            items: [{
	              xtype: 'splitbutton',
	              text: 'Cadastro',
	              menu: {
	                items: [{
	                  text: 'New...'
	                },{
	                  text: 'Open...'
	                }]
	              }
	              },'-',{
	            	  xtype: 'splitbutton',
	            	  text: 'Base de Conhecimento',
	            	  menu: {
	                  items: [{
	                  text: 'Copy'
	                },{
	                  text: 'Paste'
	                }]
	              }
	            }]    
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