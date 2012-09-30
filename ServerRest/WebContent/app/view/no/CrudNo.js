Ext.define('MONITOR.view.no.CrudNo', {
    extend: 'Ext.panel.Panel',
    xtype: 'crudno',
    padding: 10,
    border: 0,
    initComponent: function(){
    	
    	this.items = [
    	    {
    	    	xtype: 'panel',
    	    	html: "<h1>Cadastro de Nós</h1><hr/>",
    	    	border: 0
    	    },
    	    
    	    {
    	    	xtype: 'grid',
    	    	store: 'NosCrud',
    	    	columns: [
    	    	   {text: 'Nome', dataIndex: 'nome', flex:1},
    	    	   {text: 'Hostname', dataIndex: 'hostname', flex:1},
    	    	   {text: 'Porta do Agente', dataIndex: 'agentPort', flex:1},
    	    	   {text: 'Tipo', dataIndex: 'tipo', flex: 1},
    	    	   {text: 'Sub-tipo', dataIndex: 'subTipo', flex: 1}
    	    	],
    	    	dockedItems : [
    	    	    {
    	    	    	xtype: 'toolbar',
    	    	        dock: 'top',
    	    	        id: 'toolbarno',
    	    	        items:
    	    	        [
    	    	            {
    	    	            	xtype: 'splitbutton',
    	    	        	    text: 'Novo',
    	    	        	    menu:{
    	    	        	    	id: 'submenuno',
    	    	        	    	items:[
	    	    	        	    	{
	    	    	        	    		text: 'Servidor',
	    	    	        	    		id: "createServidor"
	    	    	        	    	},
	    	    	        	    	"-",
	    	    	        	    	{
	    	    	        	    		text: 'Banco de Dados',
	    	    	        	    		disabled: true,
	    	    	        	    	},
	    	    	        	    	{
	    	    	        	    		text: 'SQL Server',
	    	    	        	    		id: 'createSQLServer'
	    	    	        	    	},
	    	    	        	    	{
	    	    	        	    		text: 'Oracle',
	    	    	        	    		id: 'createOracle'
	    	    	        	    	},
	    	    	        	    	"-",
	    	    	        	    	{
	    	    	        	    		text: 'Servidor de Aplicação',
    	    	        	    			disabled: true,
	    	    	        	    	},
	    	    	        	    	"-",
	    	    	        	    	{
	    	    	        	    		text: 'JBoss',
	    	    	        	    		id: 'createJBoss'
	    	    	        	    	},
	    	    	        	    	{
	    	    	        	    		text: 'Glassfish',
	    	    	        	    		id: 'createGlassfish'
	    	    	        	    	}
    		                        ]
    	    	        	    }
	                        },
	                        "-",
		                    {
    	    	        	    text: 'Editar',
    	    	        	    action: 'edit',
	                        },
	                        '-',
	                        {
    	    	        	    text: 'Excluir',
	                    	    action: 'delete'
	                        }
    	    	        ]
    	    	    },
    	    	    
    	    	    {
    	    	    	xtype: 'pagingtoolbar',
    	    	    	dock: 'bottom',
    	    	    	displayInfo: true,
    	    	    	store: 'NosCrud'
    	    		}
    	    	]
    	    
    	    },
    	];
    		
    	
    	this.callParent(arguments);
    }
});