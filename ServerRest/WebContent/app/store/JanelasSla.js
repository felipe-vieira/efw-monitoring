Ext.define('MONITOR.store.JanelasSla', {

    extend: 'Ext.data.Store',
    model: 'MONITOR.model.JanelaSla',
    pageSize: 25,
    autoLoad: true,
    sorters:{
    	property: 'nome',
    	direction: 'ASC'
    },
    proxy: {
        type: 'rest',
        url: 'rest/janelasSla/',
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