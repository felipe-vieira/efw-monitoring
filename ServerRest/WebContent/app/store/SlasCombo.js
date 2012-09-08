Ext.define('MONITOR.store.SlasCombo', {

    extend: 'Ext.data.Store',
    model: 'MONITOR.model.Sla',
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