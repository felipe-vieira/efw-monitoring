Ext.define('MONITOR.model.Agendamento', {
    extend: 'MONITOR.model.No',
    fields: [
        {name: 'id', type: 'id', convert: null, defaultValue:null},
        {name: 'intervalo', type: 'int', convert: null, defaultValue:5},
        {name: 'horaInicio', type: 'string'},
        {name: 'horaFim', type: 'string'},
        {name: 'agendado', type: 'boolean'},
        {name: 'ativo', type: 'boolean'},
        {name: 'horaInicio', type: 'string'},
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/agendamento/',
        reader: {
            type: 'json',
            successProperty: 'success',
            messageProperty: 'message',
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    },
    
    hasOne:[
         {model: 'MONITOR.model.No', foreignKey: 'no.id', associationKey: 'no' ,  getterName: "getNo", setterName: 'setNo' }
    ],

});
	