Ext.define('MONITOR.store.SlasCombo', {

    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Sla',
    autoLoad: true,
    proxy: {
        type: 'rest',
        url: 'rest/slas/',
        reader: {
            type: 'json',
            root: 'records'
        },
		writer: {
			type: 'json',
			allowSingle: true
		}
    },
    listeners:{
	    load: function(store, records, successful, operation, options) {
	    	
	    	if(records == null || records.length == 0){
	    		store.removeAll();
	    	}
	    	
	        var falseReader = Ext.create('MONITOR.model.ServidorThreshold', {id:0, nome:'Não Configurado'});
	        store.insert(0, falseReader);
	    }
    }
    
});