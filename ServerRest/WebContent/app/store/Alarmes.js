Ext.define('MONITOR.store.Alarmes', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Alarme',
    groupField: 'status',

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