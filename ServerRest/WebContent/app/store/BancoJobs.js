Ext.define('MONITOR.store.BancoJobs', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.BancoJob',

    proxy: {
        type: 'rest',
        url: 'rest/jobs/',
        reader: {
            type: 'json',
            root: 'bancoJob'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});