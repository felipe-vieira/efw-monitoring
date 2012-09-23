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