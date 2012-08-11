Ext.define('MONITOR.view.alarme.ListAlarmesNo' ,{
	
	title: 'Alarmes',
    extend: 'Ext.grid.Panel',
    alias: 'widget.alarmenolist',
    features: [{ftype: 'grouping'}],
    height: 300,
    autoScroll: true,

    initComponent: function() {

        this.columns = [
            {header: 'Data',  dataIndex: 'data',  flex: 1, tdCls:"celulaAlerta",
            	renderer:function(val){
            		return val.toLocaleDateString();
            	}
            },
            
            {header: 'Status', dataIndex: 'status', tdCls:"celulaAlerta",
            	renderer: function(val){
            		if(val=="NAO_LIDO"){
            			return "Não Lido";
            		}else if(val=="LIDO"){
            			return "Lido";
            		}else if(val=="RESOLVIDO"){
            			return "Resolvido";
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
        }

        this.callParent(arguments);
    }
});