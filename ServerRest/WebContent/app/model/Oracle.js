Ext.define('MONITOR.model.Oracle', {
    extend: 'MONITOR.model.BancoDados',
    fields: [
        {name: 'instanceName',	type: 'string'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/oracle/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	