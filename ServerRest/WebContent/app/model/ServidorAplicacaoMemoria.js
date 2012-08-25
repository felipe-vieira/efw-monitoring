Ext.define('MONITOR.model.ServidorAplicacaoMemoria', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'init',	type: 'int'},
             {name: 'max',  type: 'int'},
             {name: 'tipo', type: 'string'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/saConfigMemory/',
        reader: {
            type: 'json',
            root: 'servidorAplicacaoMemoria'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	}
});