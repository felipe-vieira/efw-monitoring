Ext.define('MONITOR.store.ServidorAplicacaoThresholds', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.ServidorAplicacaoThreshold',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/servidorAplicacaoThresholds/',
        reader: {
            type: 'json',	
            root: 'servidorAplicacaoThreshold'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    },
    listeners:{
	    load: function(store, records, successful, operation, options) {
	        var falseReader = Ext.create('MONITOR.model.ServidorThreshold', {id:0, nome:'Não Configurado'});
	        store.insert(0, falseReader);
	    }
    }
});