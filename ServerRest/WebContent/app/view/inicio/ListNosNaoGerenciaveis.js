Ext.define('MONITOR.view.inicio.ListNosNaoGerenciaveis' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.listnosnaogerenciaveis',
    store: 'NaoGerenciaveis',
    title: 'Nós não gerenciáveis',
	
	initComponent: function() {
    	
		this.emptyText = 'Nenhum nó não gernenciável!';
		
        this.columns = [
            {header: 'Nome',  dataIndex: 'nome',  flex: 1},
            {header: 'Hostname', dataIndex: 'hostname', flex:1},
            {header: 'Tipo', dataIndex: 'tipo', flex:1}
        ];
     
        
        this.dockedItems = {
            	xtype: 'pagingtoolbar',
                dock: 'bottom',
                displayInfo: true,
                store: 'NaoGerenciaveis'
        };
        
        this.callParent(arguments);
    },
    
    
});