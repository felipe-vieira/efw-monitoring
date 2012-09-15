Ext.define('MONITOR.controller.BaseController', {
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.DateUtils',
        'MONITOR.utils.LoginUtil'
    ],
    views: [
        'alarme.ListAlarmesNo',
        'alarme.AlarmeDetalhes'
    ],
    stores: [
          
    ],
    models: [
        'Alarme',
        'Solucao'
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
    	this.control({
    		'alarmenolist':{
    			itemdblclick: this.abreDetalhesAlarme
    		},
    	
    		'alarmesdetalhes > form button[action=save]':{
    			click: this.saveOrUpdate
    		}
    	});
    },
    
    abreDetalhesAlarme: function(grid,record){
    	var view = Ext.widget('alarmesdetalhes');
    	var form = view.down('form');
    	
    	var dataFormatada = Ext.Date.format(record.get('data'), 'd/m/Y H:i:s');
    	
    	var mensagem = record.get('mensagem');
    	var params = record.get('parametro').split(';');
    	
		for(var i=0 ; i<params.length ; i++){
			var atual = params[i];
			mensagem = mensagem.replace("?",atual);
		}
    	
    	
    	form.getForm().setValues({
    		'dataFormatada' : dataFormatada,
    		'mensagemFormatada': mensagem
    	});
    	
    	form.loadRecord(record);
    },
    
	saveOrUpdate: function(button){
	    var win    = button.up('window');
        var form   = win.down('form');
        var record = form.getRecord();
        var values = form.getValues();
        
	    if(values.isResolvido == true){
	    	record.set('status',"RESOLVIDO");
	    }else{
	    	record.set('status',"LIDO");
	    }
	    
    	record.set(values);
    	
    	var solucao = Ext.create('MONITOR.model.Solucao');
    	
    	solucao.set('titulo',values.titulo);
    	solucao.set('descricao',values.descricao);
    	console.log(MONITOR.utils.LoginUtil.usuario.get('id'));
    	
		record.save(
    		{	
    			success: function(rec,op){
    		    	solucao.save({
    		    		params: {
    		    			'idAlarme': record.get('id'),
    		    			'idUsuario': MONITOR.utils.LoginUtil.usuario.get('id')
    		    		},
    		    		success: function(rec,op){
    		    			win.close();
    					},
    					failure: function(rec,op){
    						console.log(op);
    		                Ext.MessageBox.show({
    		                    title: 'Erro',
    		                    msg: op.request.scope.reader.jsonData["message"],
    		                    icon: Ext.MessageBox.ERROR,
    		                    buttons: Ext.Msg.OK
    		                });
    					}
    		    	});
    			},
    			failure: function(rec,op){
    				console.log(op);
                    Ext.MessageBox.show({
                        title: 'Erro',
                        msg: op.request.scope.reader.jsonData["message"],
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
    			}
    		}
		);
    	
    	
    	
    	
	}
  
});