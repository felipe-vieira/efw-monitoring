Ext.define('MONITOR.view.inicio.ListNosIndisponiveis' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.listnosindisponiveis',
    store: 'Indisponiveis',
    title: 'Nós indisponíveis',
	
	initComponent: function() {
    	
		this.emptyText = 'Nenhum nó indisponível!';
		
        this.columns = [
            {header: 'Nome',  dataIndex: 'nome',  flex: 1},
            {header: 'Hostname', dataIndex: 'hostname', flex:1},
            {header: 'Tipo', dataIndex: 'tipo', flex:1}
        ];
     
        
        this.dockedItems = {
            	xtype: 'pagingtoolbar',
                dock: 'bottom',
                displayInfo: true,
                store: 'Indisponiveis'
        };
        
        this.callParent(arguments);
    },
    
    
});