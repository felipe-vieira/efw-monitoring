Ext.define('MONITOR.model.SlaCalculado', {
	extend: 'Ext.data.Model',
	
    fields: [
        {name:'id', type:'int'},
        {name:'tipo', type:'string'},
        {name:'controle', type:'date'},
        {name:'percentual', type:'float'},
        {name:'tempoTotal', type:'int'},
        {name:'tempoIndisponivel', type:'int'},
        {name:'tempoJanela', type:'int'},
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/slaCalculado/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},

	hasOne:[
        {model: 'MONITOR.model.Sla', foreignKey: 'sla.id', associationKey: 'sla' ,  getterName: "getSla", setterName: 'setSla' }
    ]
	
    
});