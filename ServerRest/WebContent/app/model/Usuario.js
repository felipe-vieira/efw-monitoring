Ext.define('MONITOR.model.Usuario', {
    extend: 'Ext.data.Model',
    fields: ['id','login','senha','administrador'],
    validations: [
        {type: 'presence', field: 'login'},
        {type: 'presence', field: 'senha'}
    ]

});