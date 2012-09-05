Ext.define('MONITOR.store.BancoDadosThresholds', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.BancoDadosThreshold',
    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'rest/bancoDadosThresholds/',
        reader: {
            type: 'json',	
            root: 'bancoDadosThreshold'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    },
    listeners:{
	    load: function(store, records, successful, operation, options) {
	        var falseReader = Ext.create('MONITOR.model.ServidorThreshold', {id:0, nome:'N�o Configurado'});
	        store.insert(0, falseReader);
	    }
    }
});