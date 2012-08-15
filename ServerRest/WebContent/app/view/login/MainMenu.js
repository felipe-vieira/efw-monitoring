Ext.define('MONITOR.view.login.MainMenu', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.mainmenu',
    dock: 'top',
    
    initComponent: function(){
    	
    	this.items =  [
    	        {
    	            xtype: 'splitbutton',
    	            text: 'Cadastros',
    	            menu: {
    	                items: [
    	                    { 
    	                        text: 'Usu�rios',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Cadastro de usu�rios',
    	                    			xtype: 'usuariolist'
    	                    		}).show();
    	                        }
    	                    }
    	                ]
    	            }
    	        }
    	];
    	
    	this.callParent(arguments);
    	
    }
      

});