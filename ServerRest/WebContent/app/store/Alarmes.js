Ext.define('MONITOR.store.Alarmes', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Alarme',
    groupField: 'tipo',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/alarmesNos/',
        reader: {
            type: 'json',
            root: 'alarmes'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});