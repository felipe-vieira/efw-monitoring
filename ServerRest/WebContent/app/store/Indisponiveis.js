Ext.define('MONITOR.store.Indisponiveis', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.No',
    pageSize: 10,
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/indisponiveis/',
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