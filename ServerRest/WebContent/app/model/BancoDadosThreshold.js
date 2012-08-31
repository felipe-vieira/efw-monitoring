Ext.define('MONITOR.model.BancoDadosThreshold', {
    extend: 'MONITOR.model.Threshold',
    fields: [
        {name: 'limiteMemoria', type: 'float'},
        {name: 'limiteFile', type: 'float'},
        {name: 'limiteTempoBackup', type: 'int'},
        {name: 'limiteTempoJob', type: 'int'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/bancoDadosThreshold/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	