Ext.define('MONITOR.model.Usuario', {
    extend: 'Ext.data.Model',
    fields: [
       {name: 'id' , type:'int'},
       {name: 'login' , type:'string'},
       {name: 'senha' , type:'string'},
       {name: 'administrador',type:'boolean'},
       {name: 'ativo',type:'boolean'},
       {name: 'enviarEmail',type:'boolean'},
       {name: 'email',type:'string'},
    ],
    validations: [
        {type: 'presence', field: 'login'},
        {type: 'presence', field: 'senha'}
    ],
    proxy: {
		type: 'rest',
		url: 'rest/usuarios/',
        reader: {
            type: 'json',
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	},

});