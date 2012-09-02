Ext.define('MONITOR.store.ServidorThresholds', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.ServidorThreshold',
    pageSize: 25,
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/servidorThresholds/',
        reader: {
            type: 'json',	
            root: 'servidorThreshold'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});