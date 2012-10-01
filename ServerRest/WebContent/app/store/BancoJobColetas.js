Ext.define('MONITOR.store.BancoJobColetas', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.BancoJobColeta',
    pageSize: 10,
    
    proxy: {
        type: 'rest',
		url: 'rest/jobBancoDadosColetas/',
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