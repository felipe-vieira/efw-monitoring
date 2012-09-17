Ext.define('MONITOR.model.Solucao', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',	type: 'int'},
        {name: 'titulo',	type: 'string'},
        {name: 'descricao',	type: 'string'},
        {name: 'avaliacao',	type: 'int'},
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/solucao/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			allowSingle: true,	
			writeAllFields: false
		},
	}
	
});