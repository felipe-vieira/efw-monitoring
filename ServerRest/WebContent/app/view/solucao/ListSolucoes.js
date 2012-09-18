Ext.define('MONITOR.view.solucao.ListSolucoes', {
    extend: 'Ext.grid.Panel',
    xtype: 'listsolucoes',
    border: 1,
    title: 'Solu��es sugeridas',
    minHeight: 250,
    initComponent: function(){
    	
 	   this.columns = [
           {text: 'T�tulo', dataIndex: 'titulo', flex:1},
    	   {text: 'Avalia��o', dataIndex: 'avaliacao', flex:1, tdCls:'avaliacao'}
    	];
    	  
 	   this.dockedItems = [
    	    {
    	    	xtype: 'toolbar',
    	        dock: 'top',
    	        id: 'toolbarsolucoes',
    	        items:
    	        [
    	            {
    	        	    text: 'Para esse N�',
    	        	    action: 'listNo'
                    },
                    '-',
                    {
    	        	    text: 'Para esse Software',
    	        	    action: 'listSoftware',
                    },
                    '-',
                    {
    	        	    text: 'Para esse Tipo',
    	        	    action: 'listTipo'
                    },
    	        ]
    	    },
    	    
    	    {
    	    	xtype: 'pagingtoolbar',
    	    	dock: 'bottom',
    	    	displayInfo: true,
    	    	store: this.getStore()
    		}
    	];
    	    
    		
        this.viewConfig = {
                getRowClass: function(record,index){
              	  
              	  var avaliacao = record.get('avaliacao');
              	  if(avaliacao > 0){
              		  return "positiva";
              	  }else if(avaliacao < 0){
              		  return "negativa";
              	  }else{
              		  return "neutra";
              	  }
              	  
                }
          };
        
    	this.callParent(arguments);
    }
});