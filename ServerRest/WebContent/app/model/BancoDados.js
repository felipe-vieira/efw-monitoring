Ext.define('MONITOR.model.BancoDados', {
    extend: 'MONITOR.model.No',
    fields: [
        {name: 'port',	type: 'int'},
        {name: 'usuario',	type: 'string'},
        {name: 'senha',	type: 'string'},
        {name: 'targetServerMemory', type:'int'},
        {name: 'version', type:'string'},
        {name: 'edition', type:'string'},
        {name: 'status', type:'string'},
        {name: 'collation', type:'string'},
        {name: 'tipoBancoDados', type:'string'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/bancoDados/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
    }
});
	