Ext.define('MONITOR.view.janelasla.CrudJanelaSla', {
    extend: 'Ext.panel.Panel',
    xtype: 'crudjanelasla',
    padding: 10,
    border: 0,
    initComponent: function(){
    	
    	this.items = [
    	    {
    	    	xtype: 'panel',
    	    	html: "<h1>Cadastro de Janelas - SLA</h1><hr/>",
    	    	border: 0
    	    },
    	    
    	    {
    	    	xtype: 'grid',
    	    	store: 'JanelasSla',
    	    	columns: [
    	    	   {text: 'Descricao', dataIndex: 'descricao', flex:1},
    	    	   {text: 'Data Inicio', dataIndex: 'dataInicio', flex:1,
    	    		   renderer: function(val){
    	    			   if(val != null){
    	    				   return MONITOR.utils.DateUtils.toDatePtBr(val);
    	    			   }
    	    		   }
    	    	   },
    	    	   {text: 'Data Fim', dataIndex: 'dataFim', flex:1,
    	    		   renderer: function(val){
    	    			   if(val != null){
    	    				   return MONITOR.utils.DateUtils.toDatePtBr(val);
    	    			   }
    	    		   }
    	    	   },
    	    	   {text: 'Hora Inicio', dataIndex: 'horaInicio', flex:1,
    	    		   renderer: function(val){
    	    			   if(val != null){
    	    				   return MONITOR.utils.DateUtils.toHours(val);
    	    			   }
    	    		   }
    	    	   },
    	    	   {text: 'Hora Fim', dataIndex: 'horaFim', flex:1,
    	    		   renderer: function(val){
    	    			   if(val != null){
    	    				   return MONITOR.utils.DateUtils.toHours(val);
    	    			   }
    	    		   }
    	    	   },
    	    	],
    	    	dockedItems : [
    	    	    {
    	    	    	xtype: 'toolbar',
    	    	        dock: 'top',
    	    	        id: 'toolbarsla',
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
    	    	    	store: 'JanelasSla'
    	    		}
    	    	]
    	    
    	    },
    	    
    	    
    	    
    	];
    		
    	
    	this.callParent(arguments);
    }
});