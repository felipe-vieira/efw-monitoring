Ext.define('MONITOR.store.BancoFiles', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.BancoFile',

    proxy: {
        type: 'rest',
        url: 'rest/bdFiles/',
        reader: {
            type: 'json',
            root: 'bancoFile'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});