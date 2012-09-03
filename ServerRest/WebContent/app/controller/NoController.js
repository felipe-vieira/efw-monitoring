Ext.define('MONITOR.controller.NoController', {
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.ConvertUtils',
        'MONITOR.utils.DateUtils'    
    ],
    views: [
    	'no.List',
    	'no.CrudNo',
    	
    	'servidor.ListParticoes',
    	'servidor.ListGraficos',
    	'servidor.FormServidor',
    	
    	'servidorAplicacao.ListDeployments',
    	'servidorAplicacao.ListMemorias',
    	'servidorAplicacao.FormGlassFish',
    	'servidorAplicacao.FormJBoss',
    	
    	'bancoDados.ListJobs',
    	'bancoDados.ListFiles',
    	'bancoDados.ListBackups',
    	'bancoDados.FormOracle',
    	'bancoDados.FormSQLServer',
    	
    	'alarme.ListAlarmesNo',
    	
    	'login.MainTab'
    
    ],
    stores: [
    	'Nos',
    	'Servidores',
    	
    	'ServidorAplicacaoDeployments',
    	'ServidorAplicacaoMemorias',
    	
    	'BancoJobs',
    	'BancoFiles',
    	'BancoBackups',
    	
    	'Alarmes',
    	
    	'Thresholds',
    	'ServidorThresholds',
    	'ServidorAplicacaoThresholds',
    	'BancoDadosThresholds'
    ],
    models: [
    	
    	'No',
    	
    	//Servidor
    	'Servidor',
    	'SistemaOperacional',
    	'Memoria',
    	'Processador',
    	'Particao',
    	
    	//Servidor Aplicacao
    	'ServidorAplicacao',
    	'ServidorAplicacaoDeployment',
    	'ServidorAplicacaoMemoria',
    	'JBoss',
    	'Glassfish',
    	
    	//Banco de Dados
    	'BancoDados',
    	'BancoJob',
    	'BancoFile',
    	'BancoBackup',
    	'SQLServer',
    	'Oracle',

    	//Alarme
    	'Alarme',
    	'TipoAlarme'
   	
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
        this.control({
        	'nolist' : {
        		itemdblclick: this.addTabNo
        	},
        	
    		'crudno > grid': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItem
    		},
	    	
			'#submenuno menuitem[id=createServidor]': {
				click: this.createServidor
			},
			
			'#submenuno menuitem[id=createSQLServer]': {
				click: this.createSQLServer
			},
			
			'#submenuno menuitem[id=createOracle]': {
				click: this.createOracle
			},
			
			'#submenuno menuitem[id=createJBoss]': {
				click: this.createJBoss
			},
			
			'#submenuno menuitem[id=createGlassfish]': {
				click: this.createGlassfish
			},
			
			'#toolbarno button[action=edit]': {
				click: this.editNo
			},
			
			'#toolbarno button[action=delete]':{
				click: this.deleteNo
			},
			
			'formservidor button[action=save]': {
				click: this.saveOrUpdate
			},
			
			'formsqlserver button[action=save]': {
				click: this.saveOrUpdate
			},
			
			'formoracle button[action=save]': {
				click: this.saveOrUpdate
			},
			
			'formjboss button[action=save]': {
				click: this.saveOrUpdate
			},
			
			'formglassfish button[action=save]': {
				click: this.saveOrUpdate
			},
        
        });
    },

    addTabNo: function(grid, record){
    	
    	var viewport = grid.up('viewport');
    	var tabs = viewport.down('maintab');
    	var tipo = record.get('tipo'); 
    	
    	if(record.get('ultimaColeta') == null){
    		
            Ext.MessageBox.show({
                title: 'Sem informações para o Nó',
                msg: "O nó "+record.get('nome')+" ainda não possui informações, verifique o agendamento das coletas.",
                icon: Ext.MessageBox.WARNING,
                buttons: Ext.Msg.OK
            });
    		
    	}else{
    	
	    	if(tipo == "Servidor"){
	    		this.geraTabServidor(tabs,record);
	    	}else if (tipo == "Servidor de Aplicacao"){
	    		this.geraTabServidorAplicacao(tabs,record);
	    	}else if (tipo == "Banco de Dados"){
	    		this.geraTabBancoDados(tabs,record);
	    	}
	    	
    	}
    	
    },
    
    
    geraTabServidorAplicacao: function(tabs, record){
    	
    	tabs.setLoading(true);
    		
    	MONITOR.model.ServidorAplicacao.load(record.get('id'),{
    		success: function (sa){
    	    	
    	        if(sa != null){
    	        
    	        	var uptime = "--";
    	        	var startTime = "--";
    	        	
    	        	//Store de alarmes
    	        	var storeAlarmes = Ext.create('MONITOR.store.Alarmes');       	
    	        	storeAlarmes.load({
    	        		params: {
    	        			id: sa.get('id'),
    	        			start: 0,
    	        			limit: 10	
    	        		}
    	        	});
    	        	
    	        	storeAlarmes.on('beforeload',function(store, operation,eOpts){
    	                operation.params={
    	                		id: sa.get('id')
    	                };

    	            });
    	        	
    	        	//Store de deployments
    	        	var storeDeployments = Ext.create('MONITOR.store.ServidorAplicacaoDeployments');
    	        	storeDeployments.load({
    	        		params: {
    	        			id: sa.get('id')
    	        		}
    	        	});
    	        	
    	        	var strStatus = "";
    	        	
    	        	//Store de memorias
    	        	var storeMemorias = Ext.create('MONITOR.store.ServidorAplicacaoMemorias');
    	        	storeMemorias.load({
    	        		params: {
    	        			id: sa.get('id')
    	        		}
    	        	});
    	        	
    	        	if(sa.get('disponivel') == true){
    	        		strStatus += "Disponível";
    	        		startTime = MONITOR.utils.DateUtils.toStringPtBr(sa.get('startTime')); 
    	        		uptime = MONITOR.utils.DateUtils.secsToTime(sa.get('uptime'));
    	        	}else{
    	        		strStatus += "Não Disponível";
    	        	}
    	        	
    	        	strStatus += " / ";
    	        	
    	        	if(sa.get('gerenciavel') == true){
    	        		strStatus += "Gerenciavel";
    	        	}else{
    	        		strStatus += "Não Gerenciavel";
    	        	}
    	        	
    	        	if(sa.get('subTipo') != null){
    	        		sa.get('subTipo');
    	        	}
    	        	
    	        	
    	        	var infoHtml =
    	        		'<table class="tabelaInfo"> <tbody>'+
    	        			'<tr>'+
    	        				'<td class="titulo">Nome:</td>' + 
    	        				'<td>'+ sa.get('nome') + '</td>' +
    	        				'<td class="titulo">Hostname:</td>' + 
    	        				'<td>'+ sa.get('hostname') + '</td>' +
    	        			'</tr>'+
    	        			'<tr>'+
	        					'<td class="titulo">Tipo:</td>' + 
	        					'<td>'+ sa.get('subTipo') +'</td>' +
	        					'<td class="titulo">Porta:</td>' + 
	        					'<td>'+ sa.get('port') + '</td>' +
	        				'</tr>'+
    	        			'<tr>'+
        						'<td class="titulo">Iniciado em:</td>' + 
        						'<td>'+ startTime +'</td>' +
        						'<td class="titulo">Uptime:</td>' + 
        						'<td>'+ uptime + '</td>' +
        					'</tr>'+
    	        			'<tr>'+
	        					'<td class="titulo">Status:</td>' + 
	        					'<td>'+ strStatus +'</td>' +
	        					'<td class="titulo">Ultima coleta:</td>' + 
	        					'<td>'+ MONITOR.utils.DateUtils.toStringPtBr(sa.get('ultimaColeta')) + '</td>' +
	        				'</tr>'+
    	        		'</tbody> </table>';
    	        	
    	        	
    	        	
    	        	tabs.add({
    	                closable: true,
    	                title: record.get('nome'),
    	                padding: 10,
    	                autoScroll: true,
    	                items: [
    	    	            {
    	    	            	xtype: 'panel',
    	    	            	title: 'Informações do Servidor de Aplicação',
    	    	            	padding: 10,
    	    	            	html: infoHtml            	
    	    	            },
    	    	            {
    	    	            	xtype: 'listdeployments',
    	    	            	store: storeDeployments,
    	    	            	padding: 10    	    	            	
    	    	            },
    	    	            {
    	    	            	xtype: 'listsamemorias',
    	    	            	store: storeMemorias,
    	    	            	padding: 10
    	    	            },
    	    	            {
    	    	            	xtype: 'alarmenolist',
    	    	            	store: storeAlarmes,
    	    	            	padding: 10    	    	            	
    	    	            },
    	    	            {
    	    	            	xtype: 'servidorgraficos',
    	    	            	padding: 10,
    	    	            },
    	                ]
    	            }).show();
    	        	        	
    	        }
    	        
    	        tabs.setLoading(false);
    	    }
    	});
    	

    	
    	
    	
    },
    
    geraTabServidor: function(tabs, record){
    
    	tabs.setLoading(true);
    		
    	MONITOR.model.Servidor.load(record.get('id'),{
    		success: function (servidor){
    	    	
    	        if(servidor != null){
    	        
    	        	var so = servidor.getSistemaOperacional();
    	        	var memoria = servidor.getMemoria();
    	        	var processador = servidor.getProcessador();
    	        
    	        	
    	        	var msgPatch = "";
    	        	var processadorMsg = "";
    	        	var storeAlarmes = Ext.create('MONITOR.store.Alarmes');
    	        	
    	        	storeAlarmes.load({
    	        		params: {
    	        			id: servidor.get('id'),
    	        			start: 0,
    	        			limit: 10	
    	        		}
    	        	});
    	        	
    	        	storeAlarmes.on('beforeload',function(store, operation,eOpts){
    	                operation.params={
    	                		id: servidor.get('id')
    	                };

    	            });
    	        	
    	        	
    	        	if(so.get('patch') != null && so.get('patch') != ""){
    	        		msgPatch = " - " + so.get('patch');
    	        	}
    	        	
    	        	if(processador.get('cores') > 1){
    	        		processadorMsg = processador.get('fabricante') +' '
    	        			+ processador.get('modelo') + ' (' + processador.get('cores') + ' cores - Clocks: ' + processador.get('clock') + 'MHz)'; 
    	        	}else{
    	        		processadorMsg = processador.get('fabricante') +' '
	        				+ processador.get('modelo') + ' (' + processador.get('cores') + ' core - Clock: ' + processador.get('clock') + 'MHz)';
    	        	}
    	        	
    	        	var strStatus = "";
    	        	
    	        	if(servidor.get('disponivel') == true){
    	        		strStatus += "Disponível";
    	        	}else{
    	        		strStatus += "Não Disponível";
    	        	}
    	        	
    	        	strStatus += " / ";
    	        	
    	        	if(servidor.get('gerenciavel') == true){
    	        		strStatus += "Gerenciavel";
    	        	}else{
    	        		strStatus += "Não Gerenciavel";
    	        	}
    	        	
    	        	
    	        	var infoHtml =
    	        		'<table class="tabelaInfo"> <tbody>'+
    	        			'<tr>'+
    	        				'<td class="titulo">Nome:</td>' + 
    	        				'<td>'+ servidor.get('nome') + '</td>' +
    	        				'<td class="titulo">Hostname:</td>' + 
    	        				'<td>'+ servidor.get('hostname') + '</td>' +
    	        			'</tr>'+
    	        			'<tr>'+
	        					'<td class="titulo">Status:</td>' + 
	        					'<td>'+ strStatus + '</td>' +
	        					'<td class="titulo">Ultima coleta:</td>' + 
	        					'<td>'+ MONITOR.utils.DateUtils.toStringPtBr(servidor.get('ultimaColeta')) + '</td>' +
	        				'</tr>'+
    	        			'<tr>'+
		        				'<td class="titulo">Sistema Operacional:</td>' + 
		        				'<td>'+ so.get('descricao') + '</td>' +
		        				'<td class="titulo">Fornecedor: </td>' + 
		        				'<td>'+ so.get('fornecedor') + '</td>' +
		        			'</tr>'+
    	        			'<tr>'+
	        					'<td class="titulo">Versão:</td>' + 
		        				'<td>'+ so.get('versao') + msgPatch + '</td>' +
		        				'<td class="titulo">Arquitetura: </td>' + 
		        				'<td>'+ so.get('arquitetura') + '</td>' +	
		        			'</tr>'+
		        			'<tr>'+
		        				'<td class="titulo">Processador:</td>'+
		        				'<td>' + processadorMsg +  '</td>'+
		        				'<td class="titulo">Memoria</td>'+
		        				'<td>'+ MONITOR.utils.ConvertUtils.convertB(memoria.get('totalMemoria')) +'</td>' + 
		        			'</tr>'+
    	        		'</tbody> </table>';
    	        	
    	        	
    	        	
    	        	tabs.add({
    	                closable: true,
    	                title: record.get('nome'),
    	                padding: 10,
    	                autoScroll: true,
    	                items: [
    	    	            {
    	    	            	xtype: 'panel',
    	    	            	title: 'Informações do Servidor',
    	    	            	padding: 10,
    	    	            	html: infoHtml            	
    	    	            },
    	    	            {
    	    	            	xtype: 'particoeslist',
    	    	            	store: servidor.particoes(),
    	    	            	padding: 10
    	    	            },
    	    	            {
    	    	            	xtype: 'alarmenolist',
    	    	            	store: storeAlarmes,
    	    	            	padding: 10    	    	            	
    	    	            },
    	    	            {
    	    	            	xtype: 'servidorgraficos',
    	    	            	padding: 10,
    	    	            },
    	                ]
    	            }).show();
    	        	        	
    	        }
    	        
    	        tabs.setLoading(false);
    	    }
    	});
    },

    geraTabBancoDados: function(tabs, record){
        
    	tabs.setLoading(true);
    		
    	MONITOR.model.BancoDados.load(record.get('id'),{
    		success: function (bd){
    	    	
    	        if(bd != null){
    	        
    	        	var storeAlarmes = Ext.create('MONITOR.store.Alarmes');
    	        	
    	        	//Store de alarmes
    	        	storeAlarmes.load({
    	        		params: {
    	        			id: bd.get('id'),
    	        			start: 0,
    	        			limit: 10	
    	        		}
    	        	});
    	        	
    	        	storeAlarmes.on('beforeload',function(store, operation,eOpts){
    	                operation.params={
    	                		id: bd.get('id')
    	                };

    	            });
    	        	
    	        	//Store de jobs
    	        	var storeJobs = Ext.create('MONITOR.store.BancoJobs');
    	        	storeJobs.load({
    	        		params: {
    	        			id: bd.get('id')
    	        		}
    	        	});
    	        	
    	        	var storeFiles = Ext.create('MONITOR.store.BancoFiles');
    	        	storeFiles.load({
    	        		params: {
    	        			id: bd.get('id')
    	        		}
    	        	});
    	        	
    	        	//Store dos backups
    	        	//Store de alarmes
    	        	var storeBackups = Ext.create('MONITOR.store.BancoBackups');
    	        	
    	        	storeBackups.load({
    	        		params: {
    	        			id: bd.get('id'),
    	        			start: 0,
    	        			limit: 10	
    	        		}
    	        	});
    	        	
    	        	storeBackups.on('beforeload',function(store, operation,eOpts){
    	                operation.params={
    	                		id: bd.get('id')
    	                };

    	            });
    	        	
  	        	    	        	
    	        	var strStatus = "";
    	        	
    	        	if(bd.get('disponivel') == true){
    	        		strStatus += "Disponível";
    	        	}else{
    	        		strStatus += "Não Disponível";
    	        	}
    	        	
    	        	strStatus += " / ";
    	        	
    	        	if(bd.get('gerenciavel') == true){
    	        		strStatus += "Gerenciavel";
    	        	}else{
    	        		strStatus += "Não Gerenciavel";
    	        	}
    	        	
    	        	var infoHtml =
    	        		'<table class="tabelaInfo"> <tbody>'+
    	        			'<tr>'+
    	        				'<td class="titulo">Nome:</td>' + 
    	        				'<td>'+ bd.get('nome') + '</td>' +
    	        				'<td class="titulo">Hostname:</td>' + 
    	        				'<td>'+ bd.get('hostname') + '</td>' +
    	        			'</tr>'+
    	        			'<tr>'+
	        					'<td class="titulo">Status:</td>' + 
	        					'<td>'+ strStatus + '</td>' +
	        					'<td class="titulo">Ultima coleta:</td>' + 
	        					'<td>'+ MONITOR.utils.DateUtils.toStringPtBr(bd.get('ultimaColeta')) + '</td>' +
	        				'</tr>'+
    	        			'<tr>'+
        					'<td class="titulo">Tipo:</td>' + 
        						'<td>'+ bd.get('subTipo') + '</td>' +
        						'<td class="titulo">Porta:</td>' + 
        						'<td>'+ bd.get('port') + '</td>' +
        					'</tr>'+
        					'<td class="titulo">Versão:</td>' + 
        						'<td>'+ bd.get('version') + '</td>' +
        						'<td class="titulo">Edição:</td>' + 
        						'<td>'+ bd.get('edition') + '</td>' +
        					'</tr>'+
        						'<td class="titulo">Collation:</td>' + 
        						'<td>'+ bd.get('collation') + '</td>' +
        						'<td class="titulo">Status:</td>' + 
        						'<td>'+ bd.get('status') + '</td>' +
    					'</tr>'+
    	        		'</tbody> </table>';
    	        	
    	        	
    	        	
    	        	tabs.add({
    	                closable: true,
    	                title: record.get('nome'),
    	                padding: 10,
    	                autoScroll: true,
    	                items: [
    	    	            {
    	    	            	xtype: 'panel',
    	    	            	title: 'Informações do Banco de Dados',
    	    	            	padding: 10,
    	    	            	html: infoHtml,            	
    	    	            },
    	    	            {
    	    	            	xtype: 'listjobs',
    	    	            	padding: 10,
    	    	            	store: storeJobs
    	    	            },
    	    	            {  	    	            
    	    	            	xtype: 'listfiles',
    	    	            	padding: 10,
    	    	            	store: storeFiles
    	    	            },
    	    	            {  	    	            
    	    	            	xtype: 'listbackups',
    	    	            	padding: 10,
    	    	            	store: storeBackups
    	    	            },
    	    	            {
    	    	            	xtype: 'alarmenolist',
    	    	            	store: storeAlarmes,
    	    	            	padding: 10    	    	            	
    	    	            },
    	    	            
    	                ]
    	            }).show();
    	        	        	
    	        }
    	        
    	        tabs.setLoading(false);
    	    }
    	});
    },	
    

    createServidor: function(button){
		var view = Ext.widget('formservidor');
		var model = Ext.create('MONITOR.model.Servidor');
		view.down('form').loadRecord(model);	
    },
    
    createSQLServer: function(button){
		var view = Ext.widget('formsqlserver');
		var model = Ext.create('MONITOR.model.SQLServer');
		view.down('form').loadRecord(model);	
    },

    createOracle: function(button){
		var view = Ext.widget('formoracle');
		var model = Ext.create('MONITOR.model.Oracle');
		view.down('form').loadRecord(model);	
    },
    
    createJBoss: function(button){
		var view = Ext.widget('formjboss');
		var model = Ext.create('MONITOR.model.JBoss');
		view.down('form').loadRecord(model);	
    },
    
    createGlassfish: function(button){
		var view = Ext.widget('formglassfish');
		var model = Ext.create('MONITOR.model.Glassfish');
		view.down('form').loadRecord(model);	
    },
    
    editNo: function(button){
		if(this.itemSelected != null){
			
			
			var tipo = this.itemSelected.get('tipo');
			var subTipo = this.itemSelected.get('subTipo');
			
			
	    	if(tipo == "Servidor"){
	    		this.editServidor(this.itemSelected);
	    	}else if (tipo == "Servidor de Aplicacao"){
	    		if(subTipo == "Glassfish"){
	    			this.editGlassfish(this.itemSelected);
	    		}else if(subTipo == "JBoss"){
	    			this.editJBoss(this.itemSelected);
	    		}
	    		
	    	}else if (tipo == "Banco de Dados"){
	    		
	    		if(subTipo == "Oracle"){
	    			this.editOracle(this.itemSelected);
	    		}else if(subTipo == "SQL Server"){
	    			this.editSQLServer(this.itemSelected);
	    		}
	    		
	    	}
			
		}else{
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de alterar.");
		}
		
    },
    
    editServidor : function(record){
    	
    	this.getServidorThresholdsStore().reload();
    	
    	var id = record.get('id');
    	    	
		MONITOR.model.Servidor.load(id,{
			success: function(no){
				
				var thresholdId = no.getThreshold().get('id');
		
				var view = Ext.widget('formservidor');
				view.down('form').down('combobox').setValue(thresholdId);
				view.down('form').loadRecord(no);
				
			}
		});
		
    },
    
    editGlassfish : function(record){
    	
    	var id = record.get('id');
    	
		MONITOR.model.Glassfish.load(id,{
			success: function(no){
				
				var thresholdId = no.getThreshold().get('id');
				
				var view = Ext.widget('formglassfish');
				view.down('form').down('combobox').setValue(thresholdId);
				view.down('form').loadRecord(no);
			}
		});
		
    },
    
    editJBoss : function(record){
    	
    	var id = record.get('id');
    	
		MONITOR.model.JBoss.load(id,{
			success: function(no){
				
				var thresholdId = no.getThreshold().get('id');
				
				var view = Ext.widget('formjboss');
				view.down('form').down('combobox').setValue(thresholdId);
				view.down('form').loadRecord(no);
			}
		});
		
    },
    
    editOracle : function(record){
    	
    	var id = record.get('id');
    	
		MONITOR.model.Oracle.load(id,{
			success: function(no){
				
				var thresholdId = no.getThreshold().get('id');
				
				var view = Ext.widget('formoracle');
				view.down('form').down('combobox').setValue(thresholdId);
				view.down('form').loadRecord(no);
			}
		});
		
    },
    
    editSQLServer : function(record){
    	
    	var id = record.get('id');
    	
		MONITOR.model.SQLServer.load(id,{
			success: function(no){
				
				var thresholdId = no.getThreshold().get('id');
				
				var view = Ext.widget('formsqlserver');
				view.down('form').down('combobox').setValue(thresholdId);
				view.down('form').loadRecord(no);
			}
		});
		
    }, 
    
	saveOrUpdate: function(button){
	    var win    = button.up('window');
        var form   = win.down('form');
        var record = form.getRecord();
        var values = form.getValues();
	    var store =  this.getNosStore();

		var thresholdId = values.thresholdId;
		
		
        if(form.getForm().isValid()){
        	record.set(values);
        	record.save(
        		{
        			params:{
        				'thresholdId': thresholdId
        			},
        			success: function(rec,op){
        				win.close();
        				store.reload();
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
        }
	},
	
	deleteNo: function(button){
		if(this.itemSelected != null){
			
			//var store = Ext.data.StoreManager.lookup('Nos');
			var store = this.getNosStore();

			Ext.MessageBox.confirm('Confirmação','Deseja excluir o nó '+this.itemSelected.get('nome')+' ?',
				function(resp){
					if(resp=="yes"){
						
						this.itemSelected.destroy({
		        			success: function(rec,op){
		        				store.reload();
		        			},
		        			failure: function(rec,op){
		                        Ext.MessageBox.show({
		                            title: 'Erro',
		                            msg: op.request.scope.reader.jsonData["message"],
		                            icon: Ext.MessageBox.ERROR,
		                            buttons: Ext.Msg.OK
		                        });
		        			}
						});
						
					}
				},this);
			
			
		}else{
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de excluir.");
		}
		
	},
	
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	selectItem: function(grid, record){
		this.itemSelected = record;
	},


});