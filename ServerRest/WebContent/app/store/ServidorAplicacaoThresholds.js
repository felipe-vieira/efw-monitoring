Ext.define('MONITOR.store.ServidorAplicacaoThresholds', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.ServidorAplicacaoThreshold',
    pageSize: 25,
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/servidorAplicacaoThresholds/',
        reader: {
            type: 'json',	
            root: 'servidorAplicacaoThreshold'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});