Ext.define('MONITOR.model.ServidorThreshold', {
    extend: 'MONITOR.model.Threshold',
    fields: [
        {name: 'limiteMemoria', type: 'float', convert: null, defaultValue:null},
        {name: 'limiteCpu', type: 'float', convert: null, defaultValue:null},
        {name: 'limiteParticao', type: 'float', convert: null, defaultValue:null}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/servidorThreshold/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json'
		}
    }
});
	