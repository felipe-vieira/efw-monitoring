Ext.define('MONITOR.model.Particao', {
    extend: 'Ext.data.Model',
    fields: [
			 {name: 'id',	type: 'int'},
             {name: 'nome',	type: 'string'},
			 {name: 'sistemaArquivo',	type: 'string'},
			 {name: 'tamanho',	type: 'int'}
    ],
	
	belongsTo: 'MONITOR.model.Servidor'
	
});
