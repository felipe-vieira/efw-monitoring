Ext.define('MONITOR.model.JanelaSla', {
	extend: 'Ext.data.Model',
	
    fields: [
        {name:'id', type:'int'},
        {name:'descricao', type:'string'},
        {name:'dataInicio', type:'date'},
        {name:'dataFim', type:'date'},
        {name:'horaInicio', type:'date'},
        {name:'horaFim', type:'date'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/janelaSla/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},
	
    hasOne: [
        {model: 'MONITOR.model.DiasSemanaJanelaSla', foreignKey: 'id', associationKey: 'diasSemana', getterName: "getDiasSemana", setterName: "setDiasSemana" },
        {model: 'MONITOR.model.Sla', foreignKey: 'sla.id', associationKey: 'sla', getterName: "getSla", setterName: "setSla" }
    ]
    
});