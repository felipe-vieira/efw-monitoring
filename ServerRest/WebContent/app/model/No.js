Ext.define('MONITOR.model.No', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int', defaultValue: null},
             {name: 'nome',	type: 'string'},
             {name: 'disponivel',	type: 'boolean'},
             {name: 'gerenciavel',	type: 'boolean'},
             {name: 'tipo', type: 'string'},
             {name: 'subTipo', type: 'string'},
             {name: 'ultimaColeta', type: 'date'},
             {name: 'hostname', type: 'string'},
             {name: 'agentPort', type: 'int', defaultValue: 9090}
    ],
    proxy: {
		type: 'rest',
		url: 'rest/nos/',
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