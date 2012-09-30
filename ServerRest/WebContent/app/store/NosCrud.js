Ext.define('MONITOR.store.NosCrud', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.No',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/nosCrud/',
        reader: {
            type: 'json',
            root: 'no'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});