Ext.define('MONITOR.model.MetricaColeta', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'data',	type: 'date'},
        {name: 'valor', type: 'int'},
        {name: 'valorAlt', type: 'int'},
        {name: 'max', type: 'int'},
        {name: 'valorPercentual', type: 'float'},
        {name: 'valorPercentualAlt', type: 'float'}
    ]
	
});