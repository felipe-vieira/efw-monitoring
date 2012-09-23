Ext.define('MONITOR.model.MetricaColeta', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'data',	type: 'date'},
        {name: 'valor', type: 'int'},
        {name: 'max', type: 'int'},
        {name: 'valorPercentual', type: 'float'}
    ]
	
});