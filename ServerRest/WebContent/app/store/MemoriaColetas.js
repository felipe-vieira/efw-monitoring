Ext.define('MONITOR.store.MemoriaColetas', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.MemoriaColeta',

    proxy: {
        type: 'rest',
		url: 'rest/memoriaColetas/',
        reader: {
            type: 'json',
            root: 'memoriaColeta'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});