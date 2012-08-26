Ext.define('MONITOR.store.BancoBackups', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.BancoBackup',
    pageSize: 10,

    proxy: {
        type: 'rest',
        url: 'rest/bdBackups/',
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