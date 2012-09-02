Ext.define('MONITOR.store.Usuarios', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Usuario',
    pageSize: 25,
    autoLoad: true,
    sorters:{
    	property: 'login',
    	direction: 'ASC'
    },
    proxy: {
        type: 'rest',
        url: 'rest/usuarios/',
        reader: {
            type: 'json',
            root: 'records'
        },
		writer: {
			type: 'json',
			allowSingle: true
		}
    }
});