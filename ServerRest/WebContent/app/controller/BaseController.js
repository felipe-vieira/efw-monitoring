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
        'SolucoesNo',
        'SolucoesSoftware',
        'SolucoesTipo'
    ],
    models: [
        'Alarme',
        'Solucao',
        'AvaliacaoSolucao'
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null,
    		avaliacaoSelected: null,
    		alarmeSelected: null,
    		solucaoUsada: null,
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
    		
    		'alarmesdetalhes > form button[action=limparSolucao]':{
    			click: this.limparSolucao
    		},
    		
    		'alarmesdetalhes > form checkboxfield':{
    			change: this.habilitaSolucao
    		},
    		
    		'listsolucoes':{
    			beforerender: this.resetRegister,
    			itemclick: this.selectItem
    		},
    		
    		'#toolbarsolucoes button[action=listNo]':{
    			click: this.loadGridSolucoesNo
    		},
    		
    		'#toolbarsolucoes button[action=listSoftware]':{
    			click: this.loadGridSolucoesSoftware
    		},
    		
    		'#toolbarsolucoes button[action=listTipo]':{
    			click: this.loadGridSolucoesTipo
    		},
    		    		
    		'#toolbardescricaosolucao button[action=positivar]':{
    			click: this.positivar
    		},
    		
    		'#toolbardescricaosolucao button[action=negativar]':{
    			click: this.negativar
    		},
    		
    		'#toolbardescricaosolucao button[action=desfazer]':{
    			click: this.desfazerAvaliacao
    		},
    		
    		'#toolbardescricaosolucao button[action=usar]':{
    			click: this.usarSolucao
    		}
    		
    	});
    },
    
    resetRegister: function(){
    	this.itemSelected = null;
		this.avaliacaoSelected =  null;
		this.solucaoUsada = null;
    },
    
	selectItem: function(grid, record){
		
		this.itemSelected = record;
		var view = grid.up('panel').up('panel');
		var descPanel = view.down('descricaosolucao').down('#descpanel');
		this.changeToolbarDescricao(record);
		descPanel.update(record.get('descricao'));
	
	},
	
    
    abreDetalhesAlarme: function(grid,record){
    	var view = Ext.widget('alarmesdetalhes');
    	var form = view.down('form');
    	var panelSolucoes = view.down('#panelsolucoes');
    	var dataFormatada = Ext.Date.format(record.get('data'), 'd/m/Y H:i:s');
    	
    	var mensagem = record.get('mensagem');
    	var params = record.get('parametro').split(';');

		var checkbox = form.getForm().findField('isSolucionado');
		var tituloField = form.getForm().findField('titulo');
		var descField = form.getForm().findField('descricao');
    	
    	this.alarmeSelected = record;
    	this.solucaoUsada = null;
    	
		for(var i=0 ; i<params.length ; i++){
			var atual = params[i];
			mensagem = mensagem.replace("?",atual);
		}
    	
		var isSolucionado = false;
		var titulo = "";
		var descricao = "";
		
		var solucao = record.getSolucao();
		
		
		if(record.get('status') == 'RESOLVIDO' && solucao != null  && solucao.get('id') != 0){
			
			
			isSolucionado = true;
			var solucao = record.getSolucao();
			titulo = solucao.get('titulo');
			descricao = solucao.get('descricao');
			
			checkbox.disable();
			tituloField.enable();
			descField.enable();
			tituloField.setReadOnly(true);
			descField.setReadOnly(true);
			
		}else{
			checkbox.enable;
			tituloField.setReadOnly(false);
			descField.setReadOnly(false);
			tituloField.disable();
			descField.disable();
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
    
    usarSolucao: function(button){
    	var form = button.up('panel').up('panel').up('alarmesdetalhes').down('form').getForm();
    	this.solucaoUsada = this.itemSelected;
    	
		var txtTitulo = form.findField('titulo');
		var txtDescricao = form.findField('descricao');
		
    	form.setValues({
    		titulo: this.solucaoUsada.get('titulo'),
    		descricao: this.solucaoUsada.get('descricao'),
    		isSolucionado: true
    	});
    	
    	txtTitulo.setReadOnly(true);
		txtDescricao.setReadOnly(true);
    	
    },
    
    limparSolucao: function(button){
    	
    	var form = button.up('form').getForm();
    	this.solucaoUsada = null;
    	
		var txtTitulo = form.findField('titulo');
		var txtDescricao = form.findField('descricao');
		
    	form.setValues({
    		titulo: '',
    		descricao: '',
    		isSolucionado: false
    	});
    	
    	txtTitulo.setReadOnly(false);
		txtDescricao.setReadOnly(false);
    	
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
    	
    	var idSolucaoUsada = 0;
    	
    	if(this.solucaoUsada != null ){
    		this.solucaoUsada.get('id');
    	}
    	
		record.save(
    		{	
    			scope: this,
    			params: {
    				idSolucao: idSolucaoUsada
    			},
    			success: function(rec,op){
    				
    				if(record.get('status') == "RESOLVIDO" && this.solucaoUsada == null){
	    		    	solucao.save({
	    		    		params: {
	    		    			'idAlarme': record.get('id'),
	    		    			'idUsuario': MONITOR.utils.LoginUtil.usuario.get('id'),
	    		    		},
	    		    		success: function(rec,op){
	    		    			win.setLoading(false);
	    		    			win.close();
	    		    			this.solucaoUsada = null;
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
	
	
	loadGridSolucoesNo: function(button){
		
		var grid = button.up('listsolucoes');
		var store = this.loadSolucoesNo();
		grid.reconfigure(store);

	},

	loadGridSolucoesSoftware: function(button){
		
		var grid = button.up('listsolucoes');
		var store = this.loadSolucoesSoftware();
		grid.reconfigure(store);

	},
	
	loadGridSolucoesTipo: function(button){
		
		var grid = button.up('listsolucoes');
		var store = this.loadSolucoesTipo();
		grid.reconfigure(store);

	},
	
	loadSolucoesNo: function(record){
		
		var idNo  = this.alarmeSelected.get('idNo');
		var idTipoAlarme = this.alarmeSelected.get('idTipoAlarme');
		
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
	},
	
	loadSolucoesSoftware: function(record){
		
		var idNo  = this.alarmeSelected.get('idNo');
		var idTipoAlarme = this.alarmeSelected.get('idTipoAlarme');
		
    	var storeSolucoes = Ext.create('MONITOR.store.SolucoesSoftware');
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
	},
	
	loadSolucoesTipo: function(record){
		
		var idNo  = this.alarmeSelected.get('idNo');
		var idTipoAlarme = this.alarmeSelected.get('idTipoAlarme');
		
    	var storeSolucoes = Ext.create('MONITOR.store.SolucoesTipo');
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
	},
	
	
	
	positivar: function(button){
		this.avaliarSolucao(button,'POSITIVO');
	},
	
	negativar: function(button){
		this.avaliarSolucao(button,'NEGATIVO');
	},
		
	avaliarSolucao: function(button,tipo){
		
		var win = button.up('panel').up('panel').up('window');
		var grid = button.up('panel').up('panel').down('listsolucoes');
		
		win.setLoading(true);
		
		var model = Ext.create('MONITOR.model.AvaliacaoSolucao');
		
		model.set('tipoAvaliacao',tipo);
		
		var idUsuario = MONITOR.utils.LoginUtil.usuario.get('id');
		var idSolucao = this.itemSelected.get('id');
		
		model.save({
			
			scope: this,
			
			params:{
				idUsuario: idUsuario,
				idSolucao: idSolucao
			},
			
			success: function(rec,op){
				grid.getStore().reload();
				this.changeToolbarDescricao(this.itemSelected);
				win.setLoading(false);
				
			},
			
			failure: function(rec,op){
                win.setLoading(false);
                
				Ext.MessageBox.show({
                    title: 'Erro',
                    msg: op.request.scope.reader.jsonData["message"],
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
                
			}
			
		});
	},
	
	
	desfazerAvaliacao: function(button){
		var grid = button.up('panel').up('panel').down('listsolucoes');
		
		
		this.avalicaoSelected.destroy({
			scope:this,
			
			success: function(rec,op){
				this.avalicaoSelected = null;
				this.changeToolbarDescricao(this.itemSelected);
				grid.getStore().reload();
			}
		});
		
	},
	
	changeToolbarDescricao: function(record){
		
		var arr = Ext.ComponentQuery.query('#toolbardescricaosolucao');
		var toolbar = arr[0];
		
		if(record == null){
			this.renderAvaliacaoNull(toolbar);
			this.avalicaoSelected = null;
			
		}else{
			
			var idUsuario = MONITOR.utils.LoginUtil.usuario.get('id');
			var idSolucao = record.get('id');
			
			MONITOR.model.AvaliacaoSolucao.load(0,{
				scope: this,
				params:{
					idUsuario: idUsuario,
					idSolucao: idSolucao
				},
				
				success: function(rec,op){
					
					console.log(rec);
					if(rec == null || rec.get('avaliacao') == 0){
						this.renderAvaliacaoNaoAvaliada(toolbar);
						this.avalicaoSelected = null;
					}else{
						this.avalicaoSelected = rec;
						this.renderAvaliacaoAvaliada(toolbar,rec);
					}
				}
				
			});
		}
	},
	
	renderAvaliacaoNull: function(toolbar){
		toolbar.down('#txtSelecione').show();
		toolbar.down('#txtAvalie').hide();
		toolbar.down('#txtAvaliadaPositiva').hide();
		toolbar.down('#txtAvaliadaNegativa').hide();
		
		toolbar.down('#btnNegativar').hide();
		toolbar.down('#btnPositivar').hide();
		toolbar.down('#btnDesfazer').hide();
		
		toolbar.down('#btnNegativar').disable();
		toolbar.down('#btnPositivar').disable();
		toolbar.down('#btnDesfazer').disable();
		
		
		toolbar.down('#btnUsar').disable();
	},
	
	renderAvaliacaoNaoAvaliada: function(toolbar){
		toolbar.down('#txtSelecione').hide();
		toolbar.down('#txtAvalie').show();
		toolbar.down('#txtAvaliadaPositiva').hide();
		toolbar.down('#txtAvaliadaNegativa').hide();
		
		toolbar.down('#btnNegativar').show();
		toolbar.down('#btnPositivar').show();
		toolbar.down('#btnDesfazer').hide();
			
		toolbar.down('#btnNegativar').enable();
		toolbar.down('#btnPositivar').enable();
		toolbar.down('#btnDesfazer').disable();
		
		if(this.alarmeSelected.get('status') == "RESOLVIDO"){
			toolbar.down('#btnUsar').disable();
		}else{
			toolbar.down('#btnUsar').enable();
		}
		
	},
	
	renderAvaliacaoAvaliada: function(toolbar,avaliacao){
		
		toolbar.down('#txtSelecione').hide();
		toolbar.down('#txtAvalie').hide();
		
		if(avaliacao.get('tipoAvaliacao') == "POSITIVO"){
			toolbar.down('#txtAvaliadaPositiva').show();
			toolbar.down('#txtAvaliadaNegativa').hide();
		}else{
			toolbar.down('#txtAvaliadaPositiva').hide();
			toolbar.down('#txtAvaliadaNegativa').show();	
		}
		
		toolbar.down('#btnNegativar').hide();
		toolbar.down('#btnPositivar').hide();
		toolbar.down('#btnDesfazer').show();
			
		toolbar.down('#btnNegativar').disable();
		toolbar.down('#btnPositivar').disable();
		toolbar.down('#btnDesfazer').enable();
		
		if(this.alarmeSelected.get('status') == "RESOLVIDO"){
			toolbar.down('#btnUsar').disable();
		}else{
			toolbar.down('#btnUsar').enable();
		}
	}
	
});