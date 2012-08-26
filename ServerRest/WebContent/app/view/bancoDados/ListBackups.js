Ext.define('MONITOR.view.bancoDados.ListBackups' ,{
	
    extend: 'Ext.grid.Panel',
    requires: ['MONITOR.utils.ConvertUtils','MONITOR.utils.DateUtils'],
    alias: 'widget.listbackups',
    title: 'Backups',

    initComponent: function() {

        this.columns = [
            {header: 'Nome',  dataIndex: 'fileName',  flex: 1},
            {header: 'Data',  dataIndex: 'backupStartDate',  flex: 1,
            	renderer: function(val){
            		return MONITOR.utils.DateUtils.toStringPtBr(val);
            	}
            },
            {header: 'Tempo de Execução',  dataIndex: 'tempoExecucao',  flex: 1,
            	renderer: function(val){
            		return MONITOR.utils.DateUtils.secsToTime(val);
            	}
            },
            {header: 'Tamanho.',  dataIndex: 'tamanho',  flex: 1,
            	renderer: function(val){
           			return MONITOR.utils.ConvertUtils.convertB(val);           		
            	}
            },
            {header: 'Modelo de Recuperação.',  dataIndex: 'recoveryModel',  flex: 1},
            {header: 'Database/Schema',  dataIndex: 'databaseName',  flex: 1},
            {header: 'Tipo',  dataIndex: 'tipo',  flex: 1}
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