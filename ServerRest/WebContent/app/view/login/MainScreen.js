Ext.define('MONITOR.view.login.MainScreen', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.mainscreen',
    layout: 'border',
    renderTo: Ext.getBody(),
    items: [
        {
        	region: 'north',
        	border: false,
            layout: 'fit',
            title: 'EFW Monitoring',
            floating: false, 
            dockedItems: [
                {
            	     xtype: 'toolbar',
            	     dock: 'top',
            	     items: [
            	         {
            		         xtype: 'splitbutton',
            		         text: 'Cadastro',
            		         menu: {
            		             items: [
            		                  {text: 'New...'},
            		                  {text: 'Open...'}
            		             ]
                             }
                         },
                         '-',
                         {
                             xtype: 'splitbutton',
                             text: 'Base de Conhecimento',
                             menu: {
                                  items: [
                                      { text: 'Copy'},
                                      { text: 'Paste'}
                                  ]
                             }
                         }
                    ]    
                }
           ]
        },
        {
        	region: 'west',
        	border: false,
        	title: 'Nós monitorados',
        	width: '20%',
        	collapsible: true,
        	multiSelect: false
        }
    ]
});