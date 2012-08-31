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
    	    	   {text: 'Nome', dataIndex: 'nome', columnWidth:'50%'},
    	    	   {text: 'Tipo', dataIndex: 'tipo', columnWidth:'50%'}
    	    	],
    	    	dockedItems : [
    	    	    {
    	    	    	xtype: 'toolbar',
    	    	        dock: 'top',
    	    	        id: 'toolbarthreshold',
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
    	    	    	store: 'Thresholds'
    	    		}
    	    	]
    	    
    	    },
    	    
    	    
    	    
    	];
    		
    	
    	this.callParent(arguments);
    }
});