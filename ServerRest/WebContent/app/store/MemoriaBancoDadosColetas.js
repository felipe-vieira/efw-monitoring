Ext.define('MONITOR.store.MemoriaBancoDadosColetas', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.MetricaColeta',

    proxy: {
        type: 'rest',
		url: 'rest/memoriaBancoDadosColetas/',
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