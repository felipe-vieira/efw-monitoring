Ext.define('MONITOR.model.SistemaOperacional', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id', type: 'int'},
             {name: 'nome',	type: 'string'},
			 {name: 'descricao',	type: 'string'},
			 {name: 'fornecedor',	type: 'string'},
			 {name: 'versao',	type: 'string'},
			 {name: 'patch',	type: 'string'},
			 {name: 'arquitetura',	type: 'string'}
    ]
});
