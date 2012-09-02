Ext.define('MONITOR.view.threshold.CrudThreshold', {
    extend: 'Ext.panel.Panel',
    xtype: 'crudthreshold',
    padding: 10,
    border: 0,
    initComponent: function(){
    	
    	this.items = [
    	    {
    	    	xtype: 'panel',
    	    	html: "<h1>Cadastro de Thresholds</h1><hr/>",
    	    	border: 0
    	    },
    	    
    	    {
    	    	xtype: 'grid',
    	    	store: 'Thresholds',
    	    	columns: [
    	    	   {text: 'Nome', dataIndex: 'nome', flex:1},
    	    	   {text: 'Tipo', dataIndex: 'tipo', flex:1}
    	    	],
    	    	dockedItems : [
    	    	    {
    	    	    	xtype: 'toolbar',
    	    	        dock: 'top',
    	    	        id: 'toolbarthreshold',
    	    	        items:
    	    	        [
 	    	            {
	    	            	xtype: 'splitbutton',
	    	        	    text: 'Novo',
	    	        	    menu:{
	    	        	    	id: 'submenuthreshold',
	    	        	    	items:[
    	    	        	    	{
    	    	        	    		text: 'Servidor',
    	    	        	    		id: 'createThresholdServidor'
    	    	        	    	},
    	    	        	    	{
    	    	        	    		text: 'Banco de Dados',
    	    	        	    		id: 'createThresholdBancoDados'
    	    	        	    	},
    	    	        	    	{
    	    	        	    		text: 'Servidor de Aplicação',
	    	        	    			id: 'createThresholdServidorAplicacao'
    	    	        	    	}
		                        ]
	    	        	    }
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
    	    	    	store: 'Thresholds'
    	    		}
    	    	]
    	    
    	    },
    	    
    	    
    	    
    	];
    		
    	
    	this.callParent(arguments);
    }
});