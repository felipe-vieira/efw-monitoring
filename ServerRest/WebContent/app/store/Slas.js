Ext.define('MONITOR.store.Slas', {

    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Sla',
    pageSize: 25,
    autoLoad: true,
    sorters:{
    	property: 'nome',
    	direction: 'ASC'
    },
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
    }
    
});