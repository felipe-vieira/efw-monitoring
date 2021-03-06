Ext.define('MONITOR.view.grafico.GraficoPadrao', {
    extend: 'Ext.panel.Panel',
    xtype: 'graficopadrao',
    initComponent: function(){
    
    	this.items = [
    	    {
    	    	title: 'Gr�ficos',
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
                       labelWidth: '40',
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
		    	                    	name: 'idParticao',
		    	                    	store: 'Particoes',
		    	                    	fieldLabel: 'Parti��o',
		    	                    	valueField: 'id',
		    	                    	displayField: 'nome',
		    	                    	queryMode: 'local',
		    	                    	emptyText: 'Selecione',
		    	                    	labelWidth: 55,
		    	                    	forceSelection: true,
 	    	     	            	  	margin:{
 	    	     	            	  		left: 10
 	    	     	            	  	},
		    	                    	labelAlign: 'center',
		    	                    	forceSelection: true,
		    	                    	hidden: true
		    	                    	
	    	     	              },
	    	     	              {
		    	                      	xtype: 'combobox',
		    	                    	name: 'idArquivo',
		    	                    	store: 'BancoFiles',
		    	                    	fieldLabel: 'Arquivo',
		    	                    	valueField: 'id',
		    	                    	displayField: 'file',
		    	                    	queryMode: 'local',
		    	                    	emptyText: 'Selecione',
		    	                    	labelWidth: 55,
		    	                    	forceSelection: true,
	    	     	            	  	margin:{
	    	     	            	  		left: 10
	    	     	            	  	},
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
                    	labelWidth: '40',
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
 	    	     	              },
 	    	     	              {
 	    	     	            	  	xtype: 'button',
 	    	     	            	  	text: 'Ok',
 	    	     	            	  	action: 'filtrar',
 	    	     	            	  	margin:{
 	    	     	            	  		left: 10
 	    	     	            	  	}
 	    	     	              }
 	    	     	     ]
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