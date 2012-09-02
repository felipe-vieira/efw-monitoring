Ext.define('MONITOR.store.BancoDadosThresholds', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.BancoDadosThreshold',
    pageSize: 25,
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/bancoDadosThresholds/',
        reader: {
            type: 'json',	
            root: 'bancoDadosThresholds'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});