Ext.define('MONITOR.view.sla.CrudSla', {
    extend: 'Ext.panel.Panel',
    xtype: 'crudsla',
    padding: 10,
    border: 0,
    initComponent: function(){
    	
    	this.items = [
    	    {
    	    	xtype: 'panel',
    	    	html: "<h1>Cadastro de Slas</h1><hr/>",
    	    	border: 0
    	    },
    	    
    	    {
    	    	xtype: 'grid',
    	    	store: 'Slas',
    	    	columns: [
    	    	   {text: 'Nome', dataIndex: 'nome', flex:1},
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
    	    	   {text: 'Meta', dataIndex: 'meta', flex:1,
    	    		   renderer: function(val){
    	    			   return val + '%';
    	    		   }
    	    	   },
    	    	   {text: 'Ultimo Calculo - Diário', dataIndex: 'ultimaColeta', flex:1,
    	    		   renderer: function (val){
    	    			   if(val != null){
    	    				   return MONITOR.utils.DateUtils.toStringPtBr(val);
    	    			   }else{
    	    				   return "Não coletado";
    	    			   }
    	    		   }
    	    	   },
    	    	   {text: 'Ultimo Calculo - Mensal', dataIndex: 'ultimaColetaMes', flex:1,
    	    		   renderer: function (val){
    	    			   if(val != null){
    	    				   return MONITOR.utils.DateUtils.toStringPtBr(val);
    	    			   }else{
    	    				   return "Não coletado";
    	    			   }
    	    		   }
    	    	   }
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
    	    	    	store: 'Slas'
    	    		}
    	    	]
    	    
    	    },
    	    
    	    
    	    
    	];
 
        
    	this.callParent(arguments);
    }
});