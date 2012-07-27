Ext.define('MONITOR.store.Servidores', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Servidor',
    autoLoad: false,

    proxy: {
        type: 'rest',
        url: 'rest/servidores/',
        reader: {
            type: 'json',
            root: 'servidor'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});