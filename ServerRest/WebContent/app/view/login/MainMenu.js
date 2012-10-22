Ext.define('MONITOR.view.login.MainMenu', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.mainmenu',
    dock: 'top',
    requires: [
        'MONITOR.utils.LoginUtil',
        'MONITOR.utils.TabUtils'
    ],
    
    initComponent: function(){
    	
    	this.items =  [
				{
				    text: 'Inicio',
					handler: function(){
						MONITOR.utils.TabUtils.openShowTab('inicioview','Inicio');
				    }
				},
				{
				    text: 'SLA',
					handler: function(){
						MONITOR.utils.TabUtils.openShowTab('consultasla','Consulta de SLA');
				    }
				},
    	        {
    	            xtype: 'splitbutton',
    	            text: 'Cadastros',
    	            hidden: !MONITOR.utils.LoginUtil.usuario.get('administrador'),
    	            menu: {
    	                items: [
    	                    { 
    	                        text: 'Usuários',
    	                    	handler: function(){
    	                    		MONITOR.utils.TabUtils.openShowTab('crudusuario','Cadastro de usuários');
    	                        }
    	                    },
    	                    '-',
    	                    {   	                     
    	                        text: 'Nós',
    	                    	handler: function(){
    	                    		MONITOR.utils.TabUtils.openShowTab('crudno','Cadastro de nós');
    	                        }
    	                    },
    	                    { 
    	                        text: 'Agendamentos',
    	                    	handler: function(){
    	                    		MONITOR.utils.TabUtils.openShowTab('crudagendamento','Agendamentos');
    	                        }
    	                    },
    	                    { 
    	                        text: 'Thresholds',
    	                    	handler: function(){
    	                    		MONITOR.utils.TabUtils.openShowTab('crudthreshold','Cadastro de Thresholds');
    	                        }
    	                    },
    	                    '-',
    	                    { 
    	                        text: 'SLA',
    	                    	handler: function(){
    	                    		MONITOR.utils.TabUtils.openShowTab('crudsla','Cadastro de SLA');
    	                        }
    	                    },
    	                    {
    	                        text: 'SLA - Janelas',
    	                    	handler: function(){
    	                    		MONITOR.utils.TabUtils.openShowTab('crudjanelasla','Cadastro de Janelas SLA');
    	                        }
    	                    },
    	                    
    	                ]
    	            }
    	        }
    	        
    	];
    	
    	this.callParent(arguments);
    	
    }
      

});