Ext.define('MONITOR.model.BancoDadosThreshold', {
    extend: 'MONITOR.model.Threshold',
    fields: [
        {name: 'id', type: 'id', convert: null, defaultValue:null},
        {name: 'intervalo', type: 'int', convert: null, defaultValue:null},
        {name: 'horaInicio', type: 'string'},
        {name: 'horaFim', type: 'string'},
        {name: 'agendado', type: 'boolean'},
        {name: 'ativo', type: 'boolean'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/agendamentos/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
		}
    },
    
    hasOne:[
         {model: 'MONITOR.model.No', foreignKey: 'no.id', associationKey: 'no' ,  getterName: "getNo", setterName: 'setNo' }
    ],

});
	