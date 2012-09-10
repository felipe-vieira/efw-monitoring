Ext.define('MONITOR.view.sla.ListSlasCalculados' ,{
	
	extend: 'Ext.grid.Panel',
    alias: 'widget.slascalculadoslist',

    initComponent: function() {
    	
    	this.columns = [

    	    {header: 'Data',  dataIndex: 'controle',  flex: 1,
   	    		renderer: function(val){
   	    			return val+'%';
   	    		}
   	    	},
   	    	
   	    	{header: 'Disponibilidade',  dataIndex: 'percentual',
   	    		renderer: function(val){
   	    			return MONITOR.utils.DateUtils.toDatePtBr(val);
   	    		}
   	    	}
    	                
    	];
    	
        this.callParent(arguments);
    }
});
   