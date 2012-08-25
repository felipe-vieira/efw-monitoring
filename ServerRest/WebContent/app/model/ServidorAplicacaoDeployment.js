Ext.define('MONITOR.model.ServidorAplicacaoDeployment', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'nome',	type: 'string'}         
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/deployments/',
        reader: {
            type: 'json',
            root: 'servidorAplicacaoDeployment'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	}
});