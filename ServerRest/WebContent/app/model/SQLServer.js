Ext.define('MONITOR.model.SQLServer', {
    extend: 'MONITOR.model.BancoDados',
    fields: [
        {name: 'instanceName',	type: 'string'},
        {name: 'database',	type: 'string'}    
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/sqlserver/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	