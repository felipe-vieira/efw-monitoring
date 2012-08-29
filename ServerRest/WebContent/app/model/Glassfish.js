Ext.define('MONITOR.model.Glassfish', {
    extend: 'MONITOR.model.ServidorAplicacao',
    fields: [
        {name: 'jmxUser', type: 'string'},
        {name: 'jmxSenha', type: 'string'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/glassfish/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	