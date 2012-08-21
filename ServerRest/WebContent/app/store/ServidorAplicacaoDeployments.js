Ext.define('MONITOR.store.ServidorAplicacaoDeployments', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.ServidorAplicacaoDeployment',
    groupField: 'tipo',

    proxy: {
        type: 'rest',
        url: 'rest/deployments/',
        reader: {
            type: 'json',
            root: 'servidorAplicacaoDeployment'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});