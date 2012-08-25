Ext.define('MONITOR.view.servidorAplicacao.ListMemorias' ,{
	
    extend: 'Ext.grid.Panel',
    requires: ['MONITOR.utils.ConvertUtils'],
    alias: 'widget.listsamemorias',
    title: 'Configuração de Memória - JVM',

    initComponent: function() {

        this.columns = [
            {header: 'Tipo',  dataIndex: 'tipo',  flex: 1,
            	renderer: function(val){
            		if(val=="HEAP"){
            			return "Heap";
            		}else{
            			return "Non-Heap";
            		}
            	}
            },
            {header: 'Inicial',  dataIndex: 'init',  flex: 1,
            	renderer: function(val){
            		return MONITOR.utils.ConvertUtils.convertB(val);
            	}
            },
            
            {header: 'Máxima',  dataIndex: 'max',  flex: 1,
            	renderer: function(val){
            		return MONITOR.utils.ConvertUtils.convertB(val);
            	}
            }
            
        ];

        this.callParent(arguments);
    }
});