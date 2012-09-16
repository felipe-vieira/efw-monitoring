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
        {name:'nomeNo', type: 'string', mapping:'no.nome', persist:false},
        {name:'meta', type: 'float', mapping:'sla.meta', persist: false},
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/slaCalculado/',
        reader: {
            type: 'json',
            useSimpleAccessors: true
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