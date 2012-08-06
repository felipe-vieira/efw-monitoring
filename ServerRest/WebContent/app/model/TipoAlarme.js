Ext.define('MONITOR.model.TipoAlarme', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id', type: 'int'},
             {name: 'descricao',	type: 'string'},
			 {name: 'mensagem',	type: 'string'},
			 {name: 'threshold',	type: 'string'},
			 {name: 'unidade',	type: 'string'}
    ]
});
