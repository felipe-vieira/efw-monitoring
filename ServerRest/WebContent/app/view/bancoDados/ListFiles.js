Ext.define('MONITOR.view.bancoDados.ListFiles' ,{
	
    extend: 'Ext.grid.Panel',
    requires: ['MONITOR.utils.ConvertUtils'],
    alias: 'widget.listfiles',
    title: 'Arquivos',

    initComponent: function() {

        this.columns = [
            {header: 'Nome',  dataIndex: 'file',  flex: 1},
            {header: 'Caminho',  dataIndex: 'filePath',  flex: 1},
            {header: 'Tamanho Máx.',  dataIndex: 'maxSize',  flex: 1,
            	renderer: function(val){
            		if(val == -1 || val == 0){
            			return "Ilimitado";
            		}else{
            			return MONITOR.utils.ConvertUtils.convertKb(val);
            		}
            		
            	}
            },
            {header: 'Situacao',  dataIndex: 'situacao',  flex: 1},
            {header: 'Database/Schema',  dataIndex: 'databaseName',  flex: 1}            
        ];

        this.callParent(arguments);
    }
});