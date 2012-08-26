Ext.define('MONITOR.model.BancoBackup', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'fileName',	type: 'string'},
             {name: 'backupStartDate', type: 'date'},
             {name: 'tempoExecucao', type: 'int'},
             {name: 'tamanho', type: 'int'},
             {name: 'setCount', type: 'int'},
             {name: 'recoveryModel', type: 'string'},
             {name: 'databaseName', type: 'string'},
             {name: 'tipo',	type: 'string'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/bdBackups/',
        reader: {
            type: 'json',
            root: 'bancoBackup'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	}
});