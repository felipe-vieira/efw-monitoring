Ext.define('MONITOR.view.alarme.AlarmeDetalhes', {
    extend: 'Ext.window.Window',
    xtype: 'alarmesdetalhes',
    border: false,
    layout: {
    	type: 'hbox',
    	align: 'stretchmax',
    },
    
    autoShow: true,
    modal: true,

    initComponent: function(){
    	
    	this.items = [
    	     {
    	    	  xtype: 'form',
    	    	  border: false,
    	          frame: true,
    	          width: 300,
    	          height: 400,
    	          items: [
    	              {
    	                  xtype: 'displayfield',
    	                  fieldLabel: 'Nó',
    	                  name: 'noNome'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	                  fieldLabel: 'Tipo do alarme',
    	                  name: 'tipo'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	                  fieldLabel: 'Mensagem',
    	                  name: 'mensagem'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	            	  fieldLabel: 'Criticidade',
    	            	  name: 'criticidade'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	            	  fieldLabel: 'Data',
    	            	  name: 'data'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	            	  fieldLabel: 'Valor da Coleta',
    	            	  name: 'valor'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	            	  fieldLabel: 'Threshold',
    	            	  name: 'valorLimite'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	            	  fieldLabel: 'Status',
    	            	  name: 'status'
    	              },
    	              {
    	            	  xtype: 'checkboxfield',
                      	  inputValue: true,
                      	  uncheckedValue: false,
                          fieldLabel: 'Solucionado',
  	            	      name: 'isSolucionado'
    	              },
    	              {
    	            	  xtype: 'textfield',
    	            	  fieldLabel: 'Titulo',
    	            	  name: 'titulo'
    	              },
    	              {
    	            	  xtype: 'textarea',
    	            	  fieldLabel: 'Solução',
    	            	  name: 'soluçao'
    	            	
    	              }
    	          ],
    	          
    	          buttons:[
    	              {
    	        	      text: 'Ok',
    	        	      action: 'save'    	        	  
    	              },
    	              {
    	        	      text: 'Cancelar',
    	        	      action: 'cancel',
    	        	      scope: this,
    	        	      handler: this.close
    	              }
    	          ]
    	     },
    	     {
    	    	  html: 'B',
    	          width: 400,
    	          height: 400
    	     }
    	];
    	
    	
    	this.callParent(arguments);
    	
    }
    
});