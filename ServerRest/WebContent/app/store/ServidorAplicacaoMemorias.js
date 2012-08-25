Ext.define('MONITOR.store.ServidorAplicacaoMemorias', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.ServidorAplicacaoMemoria',
    groupField: 'tipo',

    proxy: {
        type: 'rest',
		url: 'rest/saConfigMemory/',
        reader: {
            type: 'json',
            root: 'servidorAplicacaoMemoria'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});