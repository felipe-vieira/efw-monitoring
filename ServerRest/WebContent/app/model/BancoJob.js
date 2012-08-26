Ext.define('MONITOR.model.BancoJob', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'owner',	type: 'string'},
             {name: 'jobName',	type: 'string'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/jobs/',
        reader: {
            type: 'json',
            root: 'bancoJob'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	}
});