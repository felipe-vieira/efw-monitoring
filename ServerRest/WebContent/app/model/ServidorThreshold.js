Ext.define('MONITOR.model.ServidorThreshold', {
    extend: 'MONITOR.model.Threshold',
    fields: [
        {name: 'limiteMemoria', type: 'float'},
        {name: 'limiteCpu', type: 'float'},
        {name: 'limiteParticao', type: 'float'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/servidorThreshold/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	