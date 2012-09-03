Ext.define('MONITOR.store.Agendamentos', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Agendamento',
    pageSize: 25,
    autoLoad: true,
    proxy: {
        type: 'rest',
        url: 'rest/agendamentos/',
        reader: {
            type: 'json',
            root: 'records',
            successProperty: 'success'
        },
		writer: {
			type: 'json',
			allowSingle: true
		}
    }
});