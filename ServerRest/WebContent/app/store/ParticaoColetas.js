Ext.define('MONITOR.store.ParticaoColetas', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.MetricaColeta',

    proxy: {
        type: 'rest',
		url: 'rest/particaoColetas/',
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