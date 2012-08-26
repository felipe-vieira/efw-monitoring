Ext.define('MONITOR.store.UsuariosLogin', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Usuario',
    proxy: {
        type: 'rest',
        url: 'rest/usuariosLogin/',
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