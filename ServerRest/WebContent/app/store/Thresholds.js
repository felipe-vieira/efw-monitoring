Ext.define('MONITOR.store.Thresholds', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Threshold',
    pageSize: 25,

    proxy: {
        type: 'rest',
        url: 'rest/thresholds/',
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