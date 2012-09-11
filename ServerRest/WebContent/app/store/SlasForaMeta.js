Ext.define('MONITOR.store.SlasForaMeta', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.SlaCalculado',
    pageSize: 10,
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/slasForaMeta/',
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