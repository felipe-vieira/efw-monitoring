Ext.define('MONITOR.view.grafico.ListMetricas' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.listmetricas',
	title: 'M�tricas',
	
	initComponent: function() {
    	
        this.columns = [
            {header: 'Tipo',  dataIndex: 'nome',  flex: 1}
        ];
        
        this.callParent(arguments);
    },
    
    
});