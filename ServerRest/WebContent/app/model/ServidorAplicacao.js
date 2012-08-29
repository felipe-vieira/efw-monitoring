Ext.define('MONITOR.model.ServidorAplicacao', {
    extend: 'MONITOR.model.No',
    fields: [
        {name: 'port',	type: 'int', defaultValue: 8080},
        {name: 'jmxPort',	type: 'int', defaultValue: 9999},
        {name: 'startTime',	type: 'date'},
        {name: 'uptime', type:'int'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/servidoresAplicacao/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	