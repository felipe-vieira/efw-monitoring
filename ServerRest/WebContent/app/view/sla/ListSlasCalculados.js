Ext.define('MONITOR.view.sla.ListSlasCalculados' ,{
	
	extend: 'Ext.grid.Panel',
    alias: 'widget.slascalculadoslist',
    layout: 'anchor',
    
    initComponent: function() {
    	
    	this.columns = [

    	    {header: 'No',  dataIndex: 'nomeNo',  flex: 1},
    	    
    	    {header: 'Tipo', dataIndex: 'tipo', flex: 1,
    	    	renderer: function(val){
   	    	        if(val == "MENSAL"){
   	    				return "Mensal";
   	    			}else{
   	    				return "Di�rio";
   	    			}
   	    		}
    	    },
    	    
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
   	    	},
   	    	
   	    	{header: 'Meta',  dataIndex: 'meta',
   	    		
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
   