Ext.define('MONITOR.store.SlasCalculados', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.SlaCalculado',
    pageSize: 30,

    proxy: {
        type: 'rest',
        url: 'rest/slasCalculados/',
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