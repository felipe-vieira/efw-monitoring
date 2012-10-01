Ext.define('MONITOR.model.BancoJobColeta', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',	type: 'int'},
        {name: 'logId', type: 'int'},
        {name: 'dataColeta', type: 'date'},
        {name: 'dataExecucao', type: 'date'},
        {name: 'executionTime', type: 'int'},
        {name: 'status', type: 'int'},
        {name: 'statusDescr', type: 'string'},
        {name: 'sqlMsg;', type: 'string'}
    ]
	
});