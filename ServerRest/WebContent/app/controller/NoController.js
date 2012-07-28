Ext.define('MONITOR.controller.NoController', {
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.ConvertUtils'     
    ],
    views: [
    	'no.List',
    	'no.Edit',
    	'servidor.ListParticoes',
    	'servidor.ListGraficos'
    
    ],
    stores: [
    	'Nos',
    	'Servidores'
    ],
    models: [
    	'No',
    	'Servidor',
    	'SistemaOperacional',
    	'Memoria',
    	'Processador',
    	'Particao'
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
    	}else if (tipo == "Servidor de Aplicação"){
    		//geraTabServidorAplicacao(tabs,record);
    	}else if (tipo == "Banco de Dados"){
    		//geraTabBancoDados(tabs,record);
    	}
    	
    },
    
    
    geraTabServidor: function(tabs, record){
    	
    	tabs.setLoading(true);
    		
    	MONITOR.model.Servidor.load(record.get('id'),{
    		success: function (servidor){
    	    	
    	        if(servidor != null){
    	        
    	        	console.log(servidor.particoes());
    	        	
    	        	var so = servidor.getSistemaOperacional();
    	        	var memoria = servidor.getMemoria();
    	        	var processador = servidor.getProcessador();
    	        
    	        	
    	        	var msgPatch = "";
    	        	var processadorMsg = "";
    	        	
    	        	if(so.get('patch') != null && so.get('patch') != ""){
    	        		msgPatch = " - " + so.get('patch');
    	        	}
    	        	
    	        	if(processador.get('cores') > 1){
    	        		processadorMsg = processador.get('fabricante') +' '
    	        			+ processador.get('modelo') + '(' + processador.get('cores') + ' cores - Clock: ' + processador.get('clock') + 'MHz)'; 
    	        	}else{
    	        		processadorMsg = processador.get('fabricante') +' '
	        				+ processador.get('modelo') + '(' + processador.get('cores') + ' core - Clock: ' + processador.get('clock') + 'MHz)';
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
    	    	            	xtype: 'panel',
    	    	            	title: 'Alertas ',
    	    	            	padding: 10,
    	    	            	html: '<< TODO >>'
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