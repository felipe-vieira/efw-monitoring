Ext.define('MONITOR.model.BancoFile', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id',	type: 'int'},
             {name: 'file',	type: 'string'},
             {name: 'filePath',	type: 'string'},
             {name: 'maxSize',	type: 'int'},
             {name: 'growth',	type: 'boolean'},
             {name: 'situacao',	type: 'string'},
             {name: 'fileName',	type: 'string'},
             {name: 'ativo',	type: 'boolean'},
             {name: 'databaseName',	type: 'string'},
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/jobs/',
        reader: {
            type: 'json',
            root: 'bancoJob'
        },
		writer: {
			type: 'json',
			writeAllFields: true
		}
	}
});