Ext.define('MONITOR.view.agendamento.CrudAgendamento', {
    extend: 'Ext.panel.Panel',
    xtype: 'crudagendamento',
    padding: 10,
    border: 0,
    initComponent: function(){
    	
    	this.items = [
    	    {
    	    	xtype: 'panel',
    	    	html: "<h1>Agendamentos</h1><hr/>",
    	    	border: 0
    	    },
    	    
    	    {
    	    	xtype: 'grid',
    	    	store: 'Agendamentos',
    	    	columns: [
    	    	   {text: 'Nó', flex:1,
    	    		   renderer: function(val,rec,attr){
    	    			   return attr.getNo().get('nome');
    	    		   }
    	    	   },
    	    	   {text: 'Hora Inicio', dataIndex: 'horaInicio', flex:1,},
    	    	   {text: 'Hora Fim', dataIndex: 'horaFim', flex:1,},
    	    	   {text: 'Intervalo', dataIndex: 'intervalo', flex:1,
    	    	       renderer: function(val){
    	    	    	   return val + " min.";
    	    	       }
    	    	   },
    	    	],
    	    	dockedItems : [
    	    	    {
    	    	    	xtype: 'toolbar',
    	    	        dock: 'top',
    	    	        id: 'toolbaragendamento',
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
    	    	    	store: 'Agendamentos'
    	    		}
    	    	]
    	    
    	    },
    	    
    	    
    	    
    	];
    		
    	
    	this.callParent(arguments);
    }
});