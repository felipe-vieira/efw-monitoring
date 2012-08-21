Ext.define('MONITOR.model.ServidorAplicacao', {
    extend: 'MONITOR.model.No',
    fields: [
        {name: 'port',	type: 'int'},
        {name: 'jmxPort',	type: 'int'},
        {name: 'startTime',	type: 'date'},
        {name: 'uptime', type:'int'},
        {name: 'tipoServidorAplicacao', type:'string'}    
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
	