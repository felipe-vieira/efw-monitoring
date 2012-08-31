Ext.define('MONITOR.model.ServidorAplicacaoThreshold', {
    extend: 'MONITOR.model.Threshold',
    fields: [
        {name: 'thresholdHeap', type: 'float'},
        {name: 'thresholdNonHeap', type: 'float'},
        {name: 'thresholdCpuUserTime', type: 'float'},
        {name: 'thresholdCpuCpuTime', type: 'float'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/servidorAplicacaoThreshold/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	