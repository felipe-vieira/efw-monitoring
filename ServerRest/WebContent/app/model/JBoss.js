Ext.define('MONITOR.model.JBoss', {
    extend: 'MONITOR.model.ServidorAplicacao',
    fields: [
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/jboss/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	