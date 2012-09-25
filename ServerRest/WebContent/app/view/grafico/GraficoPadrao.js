Ext.define('MONITOR.view.grafico.GraficoPadrao', {
    extend: 'Ext.panel.Panel',
    xtype: 'graficopadrao',
    initComponent: function(){
    
    	this.items = [
    	    {
    	    	title: 'Gráficos',
    	    	xtype: 'form',
    	    	border: false,
    	    	bodyPadding: 10,
    	    	buttonAlign: 'left',
    	    	listeners:{
    	    		afterrender: function(form){
    	    			
    	    			var date1Hour = Ext.Date.add(new Date(),Ext.Date.HOUR, -1);
    	    			var dateNow = new Date();

    	    			var strDate1Hour = Ext.Date.format(date1Hour, 'd/m/Y');
    	    			var strDateNow = Ext.Date.format(dateNow, 'd/m/Y');
    	    			
    	    			var strHora1Hour = Ext.Date.format(date1Hour, 'H:i');
    	    			var strHoraNow = Ext.Date.format(dateNow, 'H:i');
    	    			
    	    			
    	    			form.getForm().setValues({
    	    				dataInicio: strDate1Hour,
    	    				horaInicio: strHora1Hour,
    	    				dataFim: strDateNow,
    	    				horaFim: strHoraNow
    	    			});
    	    			
    	    		}
    	    	},
    	    	
    	    	items:[
                    {
                       xtype: 'fieldcontainer',
                       fieldLabel: 'Inicio',
                       labelWidth: '50',
                   	   layout: 'hbox',
                   	   items: [
	    	     	              {
	    	                         	xtype: 'datefield',
	    	                         	name: 'dataInicio',
	    	                         	msgTarget:'under',
	    	                            allowBlank: false,
	    	                      },
	    	     	              {
				                	    xtype: 'timefield',
				                        name: 'horaInicio',
				                        minValue: '0:00  ',
				                        maxValue: '23:59 ',
				                        increment: 30,
				                        format : 'H:i',
				                        allowBlank: false
	    	     	              },
	    	     	              {
		    	                      	xtype: 'combobox',
		    	                    	name: 'particaoId',
		    	                    	store: 'Particoes',
		    	                    	fieldLabel: 'Partição',
		    	                    	valueField: 'id',
		    	                    	displayField: 'nome',
		    	                    	queryMode: 'local',
		    	                    	emptyText: 'Selecione',
		    	                    	labelWidth: 60,
		    	                    	padding: '0 10',
		    	                    	labelAlign: 'center',
		    	                    	forceSelection: true,
		    	                    	hidden: true
		    	                    	
	    	     	              }
	    	     	   ]
                    },
                    {
                        xtype: 'fieldcontainer',
                        fieldLabel: 'Fim',
                    	layout: 'hbox',
                    	labelWidth: '50',
                    	items: [
 	    	     	              {
 	    	                         	xtype: 'datefield',
 	    	                         	name: 'dataFim',
 	    	                         	msgTarget:'under',
 	    	                            allowBlank: false,
 	    	                       	    labelWidth: 120,
 	    	                            labelAlign: 'right'
 	    	                      },
 	    	     	              {
				                	    xtype: 'timefield',
				                        name: 'horaFim',
				                        minValue: '0:00  ',
				                        maxValue: '23:59 ',
				                        increment: 30,
				                        format : 'H:i',
				                        allowBlank: false	
 	    	     	              }
 	    	     	     ]
                     }
                ],
                
                buttons: [
                	{
                		text: 'Ok',
                		action: 'filtrar',
                	}
                ]
    	    },
    	    {
    	    	xtype:'graficopanel'
    	    }
    	];
    	
    	this.callParent(arguments);
    }
   });