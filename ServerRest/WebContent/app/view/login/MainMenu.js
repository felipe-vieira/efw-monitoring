Ext.define('MONITOR.view.login.MainMenu', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.mainmenu',
    dock: 'top',
    
    initComponent: function(){
    	
    	this.items =  [
				{
				    text: 'Inicio',
					handler: function(){
						var tabs = Ext.ComponentQuery.query('#mainTab');
						tabs[0].add({
							closable: true,
							title: 'Inicio',
							xtype: 'inicioview'
						}).show();
				    }
				},
				{
				    text: 'SLA',
					handler: function(){
						var tabs = Ext.ComponentQuery.query('#mainTab');
						tabs[0].add({
							closable: true,
							title: 'Consulta de SLA',
							xtype: 'consultasla'
						}).show();
				    }
				},
    	        {
    	            xtype: 'splitbutton',
    	            text: 'Cadastros',
    	            menu: {
    	                items: [
    	                    { 
    	                        text: 'Usuários',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Cadastro de usuários',
    	                    			xtype: 'crudusuario'
    	                    		}).show();
    	                        }
    	                    },
    	                    '-',
    	                    {   	                     
    	                        text: 'Nós',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Cadastro de nós',
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
    	                    },
    	                    '-',
    	                    { 
    	                        text: 'SLA',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Cadastro de SLA',
    	                    			xtype: 'crudsla'
    	                    		}).show();
    	                        }
    	                    },
    	                    { 
    	                        text: 'SLA - Janelas',
    	                    	handler: function(){
    	                    		var tabs = Ext.ComponentQuery.query('#mainTab');
    	                    		tabs[0].add({
    	                    			closable: true,
    	                    			title: 'Cadastro de Janelas SLA',
    	                    			xtype: 'crudjanelasla'
    	                    		}).show();
    	                        }
    	                    },
    	                    
    	                ]
    	            }
    	        }
    	        
    	];
    	
    	this.callParent(arguments);
    	
    }
      

});