Ext.define('MONITOR.view.grafico.ListMetricas' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.listmetricas',
	title: 'Métricas',
    listeners:{
    	afterrender: function(grid){
    		
    		var first = grid.getStore().first();
    		var selection = grid.getSelectionModel();
    		selection.select(first);
    	}
    },
    
	initComponent: function() {
    	
        this.columns = [
            {header: 'Tipo',  dataIndex: 'nome', flex: 1}
        ];
        
        this.callParent(arguments);
    },
    
    
});