Ext.define('MONITOR.store.SolucoesTipo', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Solucao',
    pageSize: 7,

    proxy: {
        type: 'rest',
        url: 'rest/solucoesTipo/',
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