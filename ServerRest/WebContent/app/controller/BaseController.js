Ext.define('MONITOR.controller.BaseController', {
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.DateUtils',
        'MONITOR.utils.LoginUtil'
    ],
    views: [
        'alarme.ListAlarmesNo',
        'alarme.AlarmeDetalhes',
        'solucao.ListSolucoes',
        'solucao.DescricaoSolucao',
        'inicio.ListAlarmesNaoLidos',
    ],
    stores: [
        'SolucoesNo'
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
    	
    		'listalarmesnaolidos':{
    			itemdblclick: this.abreDetalhesAlarme
    		},
    		
    		'alarmesdetalhes > form button[action=save]':{
    			click: this.saveOrUpdate
    		},
    		
    		'alarmesdetalhes > form checkboxfield':{
    			change: this.habilitaSolucao
    		},
    		
    		'listsolucoes':{
    			beforerender: this.resetRegister,
    			itemclick: this.selectItem
    		}
    		
    		
    		
    	});
    },
    
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	selectItem: function(grid, record){
		this.itemSelected = record;
		var view = grid.up('panel').up('panel');
		var descPanel = view.down('descricaosolucao').down('#descpanel');
		descPanel.update(record.get('descricao'));
	},
	
    
    abreDetalhesAlarme: function(grid,record){
    	var view = Ext.widget('alarmesdetalhes');
    	var form = view.down('form');
    	var panelSolucoes = view.down('#panelsolucoes');
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
		
		
		
		
		if(solucao.get('id') != 0){
			
			
			isSelecionado = true;
			var solucao = record.getSolucao();
			titulo = solucao.get('titulo');
			descricao = solucao.get('descricao');
			
			var checkbox = form.getForm().findField('isSolucionado');
			var tituloField = form.getForm().findField('titulo');
			var descField = form.getForm().findField('descricao');
			
			checkbox.disable();
			tituloField.enable();
			descField.enable();
			tituloField.setReadOnly(true);
			descField.setReadOnly(true);
			
		}
		
		
    	form.getForm().setValues({
    		'dataFormatada' : dataFormatada,
    		'mensagemFormatada': mensagem,
    		'titulo': titulo,
    		'descricao': descricao,
    		'isSolucionado': isSolucionado
    		
    	});
    	
    	form.loadRecord(record);
    	
    	var storeSolucoes = this.loadSolucoesNo(record);
    	
    	panelSolucoes.add(
    		{
    		    xtype: 	'listsolucoes',
    		    store: storeSolucoes
    		},
    		{
    			xtype: 	'descricaosolucao',
    		}
    	);
    	
    },
    
	saveOrUpdate: function(button){
	    var win    = button.up('window');
        var form   = win.down('form');
        var record = form.getRecord();
        var values = form.getValues();
        
        win.setLoading(true);
        
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
	    		    			win.setLoading(false);
	    		    			win.close();
	    		    	    },
	    					failure: function(rec,op){
	    		                Ext.MessageBox.show({
	    		                    title: 'Erro',
	    		                    msg: op.request.scope.reader.jsonData["message"],
	    		                    icon: Ext.MessageBox.ERROR,
	    		                    buttons: Ext.Msg.OK
	    		                });
	    		                win.setLoading(false);
	    					}
	    		    	});
    				}else{
    					win.setLoading(false);
    					win.close();
    				}
    				
    			},
    			failure: function(rec,op){
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
	},
	
	loadSolucoesNo: function(record){
		
		var idNo  = record.get('idNo');
		var idTipoAlarme = record.get('idTipoAlarme');
		
		
		console.log(idNo);
		console.log(idTipoAlarme);
		
    	var storeSolucoes = Ext.create('MONITOR.store.SolucoesNo');
    	storeSolucoes.load({
    		params: {
    			idNo: idNo,
    			idTipoAlarme: idTipoAlarme,
    			start: 0,
    			limit: 7
    		}    		
    	});
    	
    	storeSolucoes.on('beforeload',function(store, operation,eOpts){
            operation.params={
        		idNo: idNo,
        		idTipoAlarme: idTipoAlarme
            };
        });
    	
    	return storeSolucoes;
	}
    
  
});