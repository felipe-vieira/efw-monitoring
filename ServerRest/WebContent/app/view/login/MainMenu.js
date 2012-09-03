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
    	                    			xtype: 'crudusuario'
    	                    		}).show();
    	                        }
    	                    },
    	                    '-',
    	                    {   	                     
    	                        text: 'N�s',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Cadastro de n�s',
    	                    			xtype: 'crudno'
    	                    		}).show();
    	                        }
    	                    },
    	                    { 
    	                        text: 'Agendamentos',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Agendamentos',
    	                    			xtype: 'crudagendamento'
    	                    		}).show();
    	                        }
    	                    },
    	                    { 
    	                        text: 'Thresholds',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Cadastro de Thresholds',
    	                    			xtype: 'crudthreshold'
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