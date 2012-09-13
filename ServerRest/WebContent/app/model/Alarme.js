Ext.define('MONITOR.model.Alarme', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'data',	type: 'date'},
             {name: 'status',	type: 'string'},
             {name: 'criticidade',	type: 'string'},
             {name: 'valor',	type: 'float'},
             {name: 'valorLimite',	type: 'float'},
             {name: 'parametro', type: 'parametro'},
             {name: 'contagem',	type: 'int'},
             {name: 'tipo', type: 'string', mapping: 'tipo.descricao', persist: false},
             {name: 'mensagem', type: 'string', mapping: 'tipo.mensagem', persist: false},
             {name: 'noNome', type: 'string', mapping: 'no.nome', persist: false}
             
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/alarme/',
        reader: {
            type: 'json',
            root: 'obj',
        },
		writer: {
			type: 'json',
			allowSingle: true,	
			writeAllFields: false
		},
	},
	
    hasOne: [
        {model: 'MONITOR.model.TipoAlarme', foreignKey: 'id', associationKey: 'tipo', getterName: "getTipoAlarme"  },
        {model: 'MONITOR.model.No', foreignKey: 'id', associationKey: 'no', getterName: "getNo"  }
    ]
});