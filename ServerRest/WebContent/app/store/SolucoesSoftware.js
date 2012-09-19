Ext.define('MONITOR.store.SolucoesSoftware', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Solucao',
    pageSize: 7,

    proxy: {
        type: 'rest',
        url: 'rest/solucoesSoftware/',
        reader: {
            type: 'json',	
            root: 'records'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});