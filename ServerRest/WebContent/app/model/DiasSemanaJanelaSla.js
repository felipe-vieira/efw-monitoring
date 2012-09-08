Ext.define('MONITOR.model.DiasSemanaJanelaSla', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'dia1',	type: 'boolean'},
             {name: 'dia2',	type: 'boolean'},
             {name: 'dia3',	type: 'boolean'},
             {name: 'dia4',	type: 'boolean'},
             {name: 'dia5',	type: 'boolean'},
             {name: 'dia6',	type: 'boolean'},
             {name: 'dia7',	type: 'boolean'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/diasSemanaJanelaSla/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},

});
