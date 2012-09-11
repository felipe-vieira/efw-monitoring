Ext.define('MONITOR.store.AlarmesNaoLidos', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Alarme',
    pageSize: 10,
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/alarmesNaoLidos/',
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