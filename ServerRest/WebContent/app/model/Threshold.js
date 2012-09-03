Ext.define('MONITOR.model.Threshold', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'nome',	type: 'string'},
             {name: 'tipo',	type: 'string'}
    ],
    proxy: {
		type: 'rest',
		url: 'rest/threshold/',
        reader: {
            type: 'json',
            root: 'no'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},
});