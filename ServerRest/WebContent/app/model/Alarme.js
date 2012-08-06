Ext.define('MONITOR.model.Alarme', {
    extend: 'MONITOR.model.No',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'data',	type: 'date'},
             {name: 'status',	type: 'int'},
             {name: 'valor',	type: 'float'},
             {name: 'parametro', type: 'parametro'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/servidores/',
        reader: {
            type: 'json',
            root: 'servidor'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},
	
    hasOne: [
        {model: 'MONITOR.model.SistemaOperacional', foreignKey: 'id', associationKey: 'sistemaOperacional', getterName: "getSistemaOperacional"  },
        {model: 'MONITOR.model.Memoria', foreignKey: 'id', associationKey: 'memoria' , getterName: "getMemoria"  },
        {model: 'MONITOR.model.Processador', foreignKey: 'id', associationKey: 'processador' ,  getterName: "getProcessador"  }
    ],
    
    hasMany:[
        {model: 'MONITOR.model.Particao', associationKey:'particoes', name:'particoes'}
    ]
});