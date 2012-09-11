Ext.define('MONITOR.view.sla.ListSlasCalculados' ,{
	
	extend: 'Ext.grid.Panel',
    alias: 'widget.slascalculadoslist',

    initComponent: function() {
    	
    	this.columns = [

    	    {header: 'Data',  dataIndex: 'controle',  flex: 1,
   	    		renderer: function(val,cell,attr){
   	    			if(attr.get('tipo') == "MENSAL"){
   	    				return Ext.Date.format(val, 'm/Y');
   	    			}else{
   	    				return Ext.Date.format(val, 'd/m/Y');
   	    			}
   	    		}
   	    	},
   	    	
   	    	{header: 'Disponibilidade',  dataIndex: 'percentual',
   	    		renderer: function(val){
   	    			return val+'%';
   	    		}
   	    	}
    	                
    	];

    	this.dockedItems = {
         	xtype: 'pagingtoolbar',
            dock: 'bottom',
            displayInfo: true,
            store: this.getStore()
        };
    	
        this.callParent(arguments);
    }


});
   