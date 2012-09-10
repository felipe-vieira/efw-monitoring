Ext.define('MONITOR.view.sla.ConsultaSla', {
    extend: 'Ext.panel.Panel',
    xtype: 'consultasla',
    padding: 10,
    border: 0,
    initComponent: function(){
   
    	
    	this.items = [
    	   {
    	       xtype:'form',
    	       title:'Filtros para consulta',
    	       bodyPadding: 10,
 
    	       items: [
    	           {
    	        	   xtype: 'fieldcontainer',
    	               layout: 'hbox',
    	               width: '100%',
    	               items:[
    	                   {
    	                        xtype: 'combobox',
    	                        name: 'noId',                    	
    	                        store: 'Nos',
    	                        fieldLabel: 'Nó',
    	                        valueField: 'id',
    	                        displayField: 'nome',
    	                        queryMode: 'local',
    	                        emptyText: 'Selecione',
    	                        labelWidth: 120,
    	                        labelAlign: 'right',
    	                        allowBlank: false,
    	                        forceSelection: true
    	                   },
    	                   {
    	                     	xtype: 'datefield',
    	                      	fieldLabel: 'Data Inicio',
    	                       	name: 'dataInicio',
    	                       	msgTarget:'under',
    	                       	allowBlank: false,
    	                       	labelWidth: 120,
    	                        labelAlign: 'right'
    	                   },
    	                   {
    	                     	xtype: 'datefield',
    	                      	fieldLabel: 'Data Fim',
    	                      	name: 'dataFim',
    	                       	msgTarget:'under',
    	                      	allowBlank: false,
    	                       	labelWidth: 120,
    	                        labelAlign: 'right'
    	                   }
    	               ]
    	           },
    	           {
    	               xtype: 'fieldcontainer',
    	               defaultType: 'radiofield',
    	               layout: 'hbox',
    	               width: '100%',
    	               items: [
    	                   {
    	                	   padding: '0 0 0 100',
    	                       name      : 'tipo',
    	                       inputValue: 'mensal',
    	                       boxLabel: 'Mensal',
    	                       labelAlign: 'right',
    	                       width: 120,
    	                       labelWidth: 120,
    	                       boxLabelAlign: 'before'
    	                    	
    	                   },
    	                   {
    	                       name      : 'tipo',
    	                       inputValue: 'diario',
    	                       boxLabel: 'Diario',
    	                       labelAlign: 'right',
    	                       width : 120,
    	                       labelWidth: 120,
    	                       boxLabelAlign: 'before'
    	                   } 
    	               ]
    	           }
    	       ],
    	       
    	       buttons: [
    	            {
    	                 text: 'Consultar',
    	                 action: 'search',
                    }
    	       ]
    	   }
    	
    	];
    	
    	this.callParent(arguments);
    }

});