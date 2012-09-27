Ext.define('MONITOR.store.ProcessadorServidorAplicacaoColetas', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.MetricaColeta',

    proxy: {
        type: 'rest',
		url: 'rest/processadorServidorAplicacaoColetas/',
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