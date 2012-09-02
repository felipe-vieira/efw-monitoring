Ext.define('MONITOR.model.BancoDadosThreshold', {
    extend: 'MONITOR.model.Threshold',
    fields: [
        {name: 'limiteMemoria', type: 'float', convert: null, defaultValue:null},
        {name: 'limiteFile', type: 'float', convert: null, defaultValue:null},
        {name: 'limiteTempoBackup', type: 'int', convert: null, defaultValue:null},
        {name: 'limiteTempoJob', type: 'int', convert: null, defaultValue:null}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/bancoDadosThreshold/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
		}
    }
});
	