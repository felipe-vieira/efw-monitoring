Ext.define('MONITOR.store.ProcessadorColetas', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.MetricaColeta',

    proxy: {
        type: 'rest',
		url: 'rest/processadorColetas/',
        reader: {
            type: 'json',
            root: 'metricaTO'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});