Ext.define('MONITOR.view.alarme.AlarmeDetalhes', {
    extend: 'Ext.window.Window',
    xtype: 'alarmesdetalhes',
    layout: {
    	type: 'hbox',
    	align: 'stretchmax',
    },
    title: 'Detalhes do Alerta',
    autoShow: true,
    modal: true,

    initComponent: function(){
    	
    	this.items = [
    	     {
    	    	  xtype: 'form',
    	    	  border: false,
    	          frame: true,
    	          width: 300,
    	          minHeight: 450,
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
    	                  name: 'mensagemFormatada'
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	            	  fieldLabel: 'Criticidade',
    	            	  name: 'criticidade',
    	            	  renderer: function(val){
    	            		  if(val=="ALERTA"){
    	            			  return "Alerta";
    	            		  }else if(val=="CRITICO"){
    	            			  return "Crítico";
    	            		  }
    	            	  }
    	              },
    	              {
    	            	  xtype: 'displayfield',
    	            	  fieldLabel: 'Data',
    	            	  name: 'dataFormatada'
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
    	            	  name: 'status',
    	            	  renderer: function(val){
    	            		  if(val=="NAO_LIDO"){
    	            			  return "Não Lido";
    	            		  }else if(val=="LIDO"){
    	            			  return "Lido";
    	            		  }else if(val=="RESOLVIDO"){
    	            			  return "Resolvido";
    	            		  }
    	            	  }
    	            
    	              },
    	              {
    	            	  xtype: 'checkboxfield',
                      	  inputValue: true,
                      	  uncheckedValue: false,
                          fieldLabel: 'Resolvido',
  	            	      name: 'isSolucionado'
    	              },
    	              {
    	            	  xtype: 'textfield',
    	            	  fieldLabel: 'Titulo',
    	            	  name: 'titulo',
    	            	  maxLength: 30,
    	            	  enforceMaxLength: true,
    	            	  labelWidth: 70,
    	            	  width: 280,
    	            	  disabled: true
    	              },
    	              {
    	            	  xtype: 'textarea',
    	            	  fieldLabel: 'Solução',
    	            	  name: 'descricao',
    	            	  maxLength: 1024,
    	            	  enforceMaxLength: true,
    	            	  labelWidth: 70,
    	            	  width: 280,
    	            	  rows: 12,
    	            	  disabled: true
    	              },
    	              
    	              
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
    	    	 
    	    	  xtype: 'panel',
	    	      id: 'panelsolucoes',
    	          width: 400,
    	          minHeight: 450,
    	          layout: {
    	        	  type: 'vbox',
    	        	  align: 'stretch'
    	          }
    	     }
    	];
    	
    	
    	this.callParent(arguments);
    	
    }
    
});