Ext.define('MONITOR.model.Alarme', {
    extend: 'MONITOR.model.No',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'data',	type: 'date'},
             {name: 'status',	type: 'string'},
             {name: 'valor',	type: 'float'},
             {name: 'valorLimite',	type: 'float'},
             {name: 'parametro', type: 'parametro'},
             {name: 'contagem',	type: 'int'},
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/alarme/',
        reader: {
            type: 'json',
            root: 'alarme'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},
	
    hasOne: [
        {model: 'MONITOR.model.tipoAlarme', foreignKey: 'id', associationKey: 'tipo', getterName: "getTipoAlarme"  },
        {model: 'MONITOR.model.No', foreignKey: 'id', associationKey: 'no', getterName: "getNo"  }
        
    ]
});