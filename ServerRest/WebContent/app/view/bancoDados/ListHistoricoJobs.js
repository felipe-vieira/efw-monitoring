Ext.define('MONITOR.view.bancoDados.ListHistoricoJobs' ,{
	
    extend: 'Ext.grid.Panel',
    alias: 'widget.listhistoricojobs',
    requires: ['MONITOR.utils.ConvertUtils','MONITOR.utils.DateUtils'],

    initComponent: function() {

        this.columns = [
    	      {header: 'Data',  dataIndex: 'dataExecucao',  flex: 1,
    	    	  renderer: function(val){
    	    		  return Ext.Date.format(val, 'd/m/Y H:i:s');  
    	    	  },
    	      },
    	      {header: 'Tempo',  dataIndex: 'executionTime', 
    	    	  	renderer: function(val){
    	    	  		return MONITOR.utils.DateUtils.secsToTime(val);
    	    	  	}, flex: 1},
    	      {header: 'Status',  dataIndex: 'statusDescr',  flex: 1}
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