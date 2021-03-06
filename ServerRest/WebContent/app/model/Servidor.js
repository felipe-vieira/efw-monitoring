Ext.define('MONITOR.model.Servidor', {
    extend: 'MONITOR.model.No',
    fields: [
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/servidor/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},
	
    hasOne: [
        {model: 'MONITOR.model.SistemaOperacional', foreignKey: 'id', associationKey: 'sistemaOperacional', getterName: "getSistemaOperacional"  },
        {model: 'MONITOR.model.Memoria', foreignKey: 'id', associationKey: 'memoria' , getterName: "getMemoria"  },
        {model: 'MONITOR.model.Processador', foreignKey: 'id', associationKey: 'processador' ,  getterName: "getProcessador" },
        {model: 'MONITOR.model.ServidorThreshold', foreignKey: 'threshold.id', associationKey: 'threshold' ,  getterName: "getThreshold", setterName: 'setThreshold' }
    ],
    
    hasMany:[
        {model: 'MONITOR.model.Particao', associationKey:'particoes', name:'particoes'}
    ]
});