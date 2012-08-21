Ext.define('MONITOR.model.No', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'nome',	type: 'string'},
             {name: 'disponivel',	type: 'boolean'},
             {name: 'gerenciavel',	type: 'boolean'},
             {name: 'tipo', type: 'string'},
             {name: 'ultimaColeta', type: 'date'},
             {name: 'hostname', type: 'string'},
             {name: 'agentPort', type: 'int'}
    ]
});