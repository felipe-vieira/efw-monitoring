Ext.define('MONITOR.store.FileBancoDadosColetas', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.MetricaColeta',

    proxy: {
        type: 'rest',
		url: 'rest/fileBancoDadosColetas/',
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