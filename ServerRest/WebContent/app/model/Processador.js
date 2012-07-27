Ext.define('MONITOR.model.Processador', {
    extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'fabricante',	type: 'string'},
			 {name: 'modelo',	type: 'string'},
			 {name: 'cores',	type: 'int'},
			 {name: 'clock',	type: 'int'}
    ]
	
});