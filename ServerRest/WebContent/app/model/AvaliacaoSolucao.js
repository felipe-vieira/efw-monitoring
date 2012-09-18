Ext.define('MONITOR.model.AvaliacaoSolucao', {
	extend: 'Ext.data.Model',
	
    fields: [
        {name:'id', type:'int'},
        {name:'avaliacao', type:'int'},
        {name:'tipoAvaliacao', type:'string'}
    ],
    
    proxy: {
		type: 'rest',
		url: 'rest/avaliacaoSolucao/',
        reader: {
            type: 'json'
        },
		writer: {
			type: 'json',
			writeAllFields: true	
		}
	},
	
    hasOne: [
        {model: 'MONITOR.model.Solucao', foreignKey: 'id', associationKey: 'solucao', getterName: "getSolucao" },
        {model: 'MONITOR.model.Usuario', foreignKey: 'id', associationKey: 'usuario', getterName: "getUsuario" }
    ]
    
});