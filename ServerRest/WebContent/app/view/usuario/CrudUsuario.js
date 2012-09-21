Ext.define('MONITOR.view.usuario.CrudUsuario', {
    extend: 'Ext.panel.Panel',
    xtype: 'crudusuario',
    padding: 10,
    border: 0,
    initComponent: function(){
    	
    	this.items = [
    	    {
    	    	xtype: 'panel',
    	    	html: "<h1>Cadastro de Usuários</h1><hr/>",
    	    	border: 0
    	    },
    	    
    	    {
    	    	xtype: 'grid',
    	    	store: 'Usuarios',
    	    	columns: [
    	    	   {text: 'Login', dataIndex: 'login', flex:1,},
    	    	   {text: 'Administrador', dataIndex: 'administrador', flex:1,
    	    	       renderer: function(val){
    	    	    	   if(val == true){
    	    	    		   return "Sim";
    	    	    	   }else{
    	    	    		   return "Não";
    	    	    	   }
    	    	       }
    	    	   }
    	    	],
    	    	dockedItems : [
    	    	    {
    	    	    	xtype: 'toolbar',
    	    	        dock: 'top',
    	    	        id: 'toolbarusuario',
    	    	        items:
    	    	        [
    	    	            {
    	    	        	    text: 'Novo',
    	    	        	    action: 'create'
	                        },
	                        '-',
	                        {
    	    	        	    text: 'Editar',
    	    	        	    action: 'edit',
	                        },
	                        '-',
	                        {
    	    	        	    text: 'Excluir',
	                    	    action: 'delete'
	                        },
    	    	        ]
    	    	    },
    	    	    
    	    	    {
    	    	    	xtype: 'pagingtoolbar',
    	    	    	dock: 'bottom',
    	    	    	displayInfo: true,
    	    	    	store: 'Usuarios'
    	    		}
    	    	]
    	    
    	    },
    	    
    	    
    	    
    	];
    		
    	
    	this.callParent(arguments);
    }
});