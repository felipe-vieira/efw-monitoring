Ext.define('MONITOR.controller.NoController', {
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.ConvertUtils',
        'MONITOR.utils.DateUtils'    
    ],
    views: [
    	'no.List',
    	'no.Edit',
    	'servidor.ListParticoes',
    	'servidor.ListGraficos',
    	'servidorAplicacao.ListDeployments',
    	'servidorAplicacao.ListMemorias',
    	'bancoDados.ListJobs',
    	'bancoDados.ListFiles',
    	'bancoDados.ListBackups',
    	'alarme.ListAlarmesNo',
    	'login.MainTab'
    
    ],
    stores: [
    	'Nos',
    	'Servidores',
    	'Alarmes',
    	'ServidorAplicacaoDeployments',
    	'ServidorAplicacaoMemorias',
    	'BancoJobs',
    	'BancoFiles',
    	'BancoBackups'
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
    	
    	//Banco de Dados
    	'BancoDados',
    	'BancoJob',
    	'BancoFile',
    	'BancoBackup',

    	//Alarme
    	'Alarme',
    	'TipoAlarme'
   	
    ],
    
    init: function() {
        this.control({
        	'nolist' : {
        		itemdblclick: this.addTabNo
        	},
        	'noedit button[action=save]':{
        		click : this.updateNo
        	}
        });
    },

    addTabNo: function(grid, record){
    	
    	var viewport = grid.up('viewport');
    	var tabs = viewport.down('maintab');
    	var tipo = record.get('tipo'); 
    	
    	if(tipo == "Servidor"){
    		this.geraTabServidor(tabs,record);
    	}else if (tipo == "Servidor de Aplicacao"){
    		this.geraTabServidorAplicacao(tabs,record);
    	}else if (tipo == "Banco de Dados"){
    		this.geraTabBancoDados(tabs,record);
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
    	        	
    	        	if(sa.get('tipoServidorAplicacao') != null){
    	        		sa.get('tipoServidorAplicacao');
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
	        					'<td>'+ sa.get('tipoServidorAplicacao') +'</td>' +
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
        						'<td>'+ bd.get('tipoBancoDados') + '</td>' +
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
    

    updateNo: function(button){
    	var win = button.up('window'),
    		form = win.down('form'),
    		record = form.getRecord(),
    		values = form.getValues();
    		
    	record.set(values);
    	win.close();
    	this.getNosStore().sync();
    }



});