Ext.define('MONITOR.store.Particoes', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Particao',

    proxy: {
        type: 'rest',
        url: 'rest/particoes/',
        reader: {
            type: 'json',
            root: 'particao'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});