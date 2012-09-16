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
    		},
    		
    		'alarmesdetalhes > form checkboxfield':{
    			change: this.habilitaSolucao
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
    	
		var isSolucionado = false;
		var titulo = "";
		var descricao = "";
		
		var solucao = record.getSolucao();
		
		console.log(solucao);
		
		
		
		if(solucao.get('id') != 0){
			
			console.log('oi');
			
			isSelecionado = true;
			var solucao = record.getSolucao();
			titulo = solucao.get('titulo');
			descricao = solucao.get('descricao');
			
			var checkbox = form.getForm().findField('isSolucionado');
			var tituloField = form.getForm().findField('titulo');
			var descField = form.getForm().findField('descricao');
			
			checkbox.disable();
			tituloField.disable();
			descField.disable();
			
		}
		
		console.log('tchaui');
		
    	form.getForm().setValues({
    		'dataFormatada' : dataFormatada,
    		'mensagemFormatada': mensagem,
    		'titulo': titulo,
    		'descricao': descricao,
    		'isSolucionado': isSolucionado
    		
    	});
    	
    	form.loadRecord(record);
    },
    
	saveOrUpdate: function(button){
	    var win    = button.up('window');
        var form   = win.down('form');
        var record = form.getRecord();
        var values = form.getValues();
        
	    if(values.isSolucionado == true){
	    	record.set('status',"RESOLVIDO");
	    }else{
	    	record.set('status',"LIDO");
	    }
	    
    	record.set(values);
    	
    	var solucao = Ext.create('MONITOR.model.Solucao');
    	
    	solucao.set('titulo',values.titulo);
    	solucao.set('descricao',values.descricao);
    	
		record.save(
    		{	
    			success: function(rec,op){
    				
    				if(record.get('status') == "RESOLVIDO"){
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
    				}else{
    					win.close();
    				}
    				
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
	},
    
	habilitaSolucao: function(checkbox, newValue){
		var formPanel =  checkbox.up('form');
		var form = formPanel.getForm();
		
		var titulo = form.findField('titulo');
		var descricao = form.findField('descricao');
		
		if(newValue == true){
			titulo.enable();
			descricao.enable();
		}else{
			titulo.disable();
			descricao.disable();
		}
	}
    
  
});