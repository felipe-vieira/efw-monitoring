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
    	'alarme.ListAlarmesNo',
    	'login.MainTab'
    
    ],
    stores: [
    	'Nos',
    	'Servidores',
    	'Alarmes'
    ],
    models: [
    	'No',
    	'Servidor',
    	'ServidorAplicacao',
    	'SistemaOperacional',
    	'Memoria',
    	'Processador',
    	'Particao',
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
    		//geraTabBancoDados(tabs,record);
    	}
    	
    },
    
    
    geraTabServidorAplicacao: function(tabs, record){
    	
    	tabs.setLoading(true);
    		
    	MONITOR.model.ServidorAplicacao.load(record.get('id'),{
    		success: function (sa){
    	    	
    	        if(sa != null){
    	        
    	        	var uptime = "--";
    	        	var startTime = "--";
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
    	        	
    	        	var strStatus = "";
    	        	
    	        	if(sa.get('disponivel') == true){
    	        		strStatus += "Disponível";
    	        	}else{
    	        		strStatus += "Não Disponível";
    	        		startTime = MONITOR.utils.DateUtils.toStringPtBr(sa.get('startTime')); 
    	        		uptime = MONITOR.utils.DateUtils.secsToTime(sa.get('uptime'));
    	        	}
    	        	
    	        	strStatus += " / ";
    	        	
    	        	if(sa.get('gerenciavel') == true){
    	        		strStatus += "Gerenciavel";
    	        	}else{
    	        		strStatus += "Não Gerenciavel";
    	        	}
    	        	
    	        	if(sa.get('tipoServidorAplicacao') != null){
    	        		sa.get('tipoServidorAplicacao')
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