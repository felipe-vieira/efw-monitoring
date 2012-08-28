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
    	    	store: 'Nos',
    	    	columns: [
    	    	   {text: 'Nome', dataIndex: 'nome', flex:1},
    	    	   {text: 'Hostname', dataIndex: 'hostname', flex:1},
    	    	   {text: 'Porta do Agente', dataIndex: 'agentPort', flex:1},
    	    	   {text: 'Tipo', dataIndex: 'tipo', flex: 1}
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
	    	    	        	    		text: 'SQL Server'
	    	    	        	    	},
	    	    	        	    	{
	    	    	        	    		text: 'Oracle',
	    	    	        	    	},
	    	    	        	    	"-",
	    	    	        	    	{
	    	    	        	    		text: 'Servidor de Aplicação',
    	    	        	    			disabled: true,
	    	    	        	    	},
	    	    	        	    	"-",
	    	    	        	    	{
	    	    	        	    		text: 'JBoss'
	    	    	        	    	},
	    	    	        	    	{
	    	    	        	    		text: 'Glassfish'
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
    	    	    	store: 'Nos'
    	    		}
    	    	]
    	    
    	    },
    	];
    		
    	
    	this.callParent(arguments);
    }
});