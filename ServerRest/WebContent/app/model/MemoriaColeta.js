Ext.define('MONITOR.model.MemoriaColeta', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',	type: 'int'},
        {name: 'dataColeta', type: 'date'},
        {name: 'usado', type: 'int'},
    ]
	
});