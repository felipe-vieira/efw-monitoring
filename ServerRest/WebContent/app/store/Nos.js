Ext.define('MONITOR.store.Nos', {
    extend: 'Ext.data.Store',
    model: 'MONITOR.model.No',
    groupField: 'tipo',
    autoLoad: true,
    autoSync: true,
    pageSize: 25,
    
    sorters:{
    	property: 'nome',
    	direction: 'ASC'
    },
    
    proxy: {
        type: 'rest',
        url: 'rest/nos/',
        reader: {
            type: 'json',
            root: 'no'
        },
		writer: {
			type: 'json', //json ou xml
			writeAllFields: true,
			allowSingle: true
		}
    }
});