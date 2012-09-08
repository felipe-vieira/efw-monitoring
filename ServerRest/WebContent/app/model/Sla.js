Ext.define('MONITOR.model.Sla', {
	extend: 'Ext.data.Model',
	
    fields: [
        {name:'id', type:'int'},
        {name:'nome', type:'string'},
        {name:'horaInicio', type:'date'},
        {name:'horaFim', type:'date'},
        {name:'meta', type:'float', defaultValue: null, convert: null},
        {name:'ultimaColeta', type:'date'},
        {name:'ultimaColetaMes', type:'date'},
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/sla/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},
	
    hasOne: [
        {model: 'MONITOR.model.DiasSemanaSla', foreignKey: 'id', associationKey: 'diasSemana', getterName: "getDiasSemana", setterName: "setDiasSemana" }
    ]
    
});