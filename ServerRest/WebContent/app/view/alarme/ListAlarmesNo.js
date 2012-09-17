Ext.define('MONITOR.view.alarme.ListAlarmesNo' ,{
	
	title: 'Alarmes (Duplo clique para mais detalhes)',
    extend: 'Ext.grid.Panel',
    alias: 'widget.alarmenolist',
    requires: ['MONITOR.utils.ConvertUtils','MONITOR.utils.DateUtils'],
    features: [{ftype: 'grouping'}],

    initComponent: function() {

        this.columns = [
            
            {header: 'Tipo',  dataIndex: 'tipo',  flex: 1, tdCls:"celulaAlerta"},
            {header: 'Mensagem',  dataIndex: 'mensagem',  flex: 1, tdCls:"celulaAlerta",
            	renderer: function(val,metadata,record){
            		var params = record.get('parametro').split(';');
            		
            		for(var i=0 ; i<params.length ; i++){
            			var atual = params[i];
            			val = val.replace("?",atual);
            		}
            		
            		return val;
            	}
            },
            {header: 'Data',  dataIndex: 'data',  flex: 1, tdCls:"celulaAlerta",
            	renderer:function(val){
            		return MONITOR.utils.DateUtils.toStringPtBr(val);
            	}
            },
            
            {header: 'Status', dataIndex: 'status', tdCls:"celulaAlerta",
            	renderer: function(val,metadata,record){
            		if(val=="NAO_LIDO"){
            			return "Não Lido";
            		}else if(val=="LIDO"){
            			return "Lido";
            		}else if(val=="RESOLVIDO"){
            			return "Resolvido";
            		}
            	}
            },
            
            {header: 'Valor da Coleta', dataIndex: 'valor', tdCls: "celulaAlerta",
            	renderer: function(val,metadata,record){
            		if(val == null || val == 0){
            			return "N/A";
            		}else{
            			return val + record.getTipoAlarme().get('unidade');
            		}
            	}            	
            },
            
            {header: 'Threshold', dataIndex: 'valorLimite', tdCls: "celulaAlerta",
            	renderer: function(val,metadata,record){
            		if(val == null || val == 0){
            			return "N/A";
            		}else{
            			return val + record.getTipoAlarme().get('unidade');
            		}
            	}            	
            }
            
            
        ];
        
        this.viewConfig = {
              getRowClass: function(record,index){
            	  
            	  var criticidade = record.get('criticidade');
            	  
            	  if(criticidade == "ALERTA"){
            		  return "alerta-alerta";
            		  
            	  }else if(criticidade == "CRITICO"){
            		  return "alerta-critico";
            	  }
              }
        };
        
        this.dockedItems = {
        	xtype: 'pagingtoolbar',
            dock: 'bottom',
            displayInfo: true,
            store: this.getStore()
        };
        
        this.callParent(arguments);
    }
});