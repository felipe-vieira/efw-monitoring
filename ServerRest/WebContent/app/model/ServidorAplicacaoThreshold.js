Ext.define('MONITOR.model.ServidorAplicacaoThreshold', {
    extend: 'MONITOR.model.Threshold',
    fields: [
        {name: 'thresholdHeap', type: 'float', convert: null, defaultValue:null},
        {name: 'thresholdNonHeap', type: 'float', convert: null, defaultValue:null},
        {name: 'thresholdCpuUserTime', type: 'float', convert: null, defaultValue:null},
        {name: 'thresholdCpuCpuTime', type: 'float', convert: null, defaultValue:null}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/servidorAplicacaoThreshold/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json'
		}
    }
});
	