Ext.define('MONITOR.store.Usuarios', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Usuario',
    pageSize: 25,
    autoLoad: true,
    autoSync: true,
    proxy: {
        type: 'rest',
        url: 'rest/usuarios/',
        reader: {
            type: 'json',
            root: 'usuario'
        },
		writer: {
			type: 'json',
			writeAllFields: true,
			allowSingle: true
		}
    }
});