Ext.define('MONITOR.store.ServidorThresholds', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.ServidorThreshold',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/servidorThresholds/',
        reader: {
            type: 'json',	
            root: 'servidorThreshold'
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