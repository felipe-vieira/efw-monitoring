Ext.define('MONITOR.controller.GraficoController', {
	
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.DateUtils',
        'MONITOR.utils.ConvertUtils'
    ],
    views: [
        'servidor.ListGraficos',
        'servidorAplicacao.ListGraficosServidorAplicacao',
        'bancoDados.ListGraficosBancoDados',
    	'grafico.GraficoPadrao',
    	'grafico.ListMetricas',
    	'grafico.GraficoPanel'
    ],
    stores: [
        'Particoes'    
    ],
    models: [
        'Metrica' ,
        'MetricaColeta'
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
    	this.control({
    		
    		'servidorgraficos':{
    			afterrender : this.renderGraficosServidor
    		},  
    		
    		'servidorgraficos > listmetricas': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItemServidor
    		},
    		
    		'servidorgraficos > graficopadrao > form button[action=filtrar]': {
    			click: this.abreGraficoServidor
    		},
    		
    		//Servidor Aplicacao
    		'servidoraplicacaograficos':{
    			afterrender : this.renderGraficosServidorAplicacao
    		},
    		
    		'servidoraplicacaograficos > listmetricas': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItemServidorAplicacao
    		}, 
    		
    		'servidoraplicacaograficos > graficopadrao > form button[action=filtrar]': {
    			click: this.abreGraficoServidorAplicacao
    		},
    		
    		//Banco de dados
    		'bancodadosgraficos':{
    			afterrender : this.renderGraficosBancoDados
    		},
    		
    		'bancodadosgraficos > listmetricas': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItemBancoDados
    		}, 
    		
    		'bancodadosgraficos > graficopadrao > form button[action=filtrar]': {
    			click: this.abreGraficoBancoDados
    		}
    		
    	});
    	
    
    },

    
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
    selectItemServidor: function(grid, record){
		this.itemSelected = record.get('tipo');
		
		var main = grid.up('servidorgraficos');
		var combobox = main.down('graficopadrao').down('form').down('combobox[name=idParticao]');
		
		if(this.itemSelected == "particoes"){
			
			Ext.create('MONITOR.store.Particoes').load({
				params:{
					idNo: main.getIdNo()
				},
				callback: function (records){
					combobox.setVisible(true);
					combobox.bindStore(this);
					var first = this.first();
					combobox.setValue(first);
				}
			});
		}else{
			combobox.setVisible(false);
		}
		
	},
	
    selectItemServidorAplicacao: function(grid, record){
		this.itemSelected = record.get('tipo');		
	},
	
    selectItemBancoDados: function(grid, record){
		this.itemSelected = record.get('tipo');
		
		var main = grid.up('bancodadosgraficos');
		var combobox = main.down('graficopadrao').down('form').down('combobox[name=idArquivo]');
		
		if(this.itemSelected == "arquivos"){
			
			Ext.create('MONITOR.store.BancoFiles').load({
				params:{
					id: main.getIdNo()
				},
				callback: function (records){
					combobox.setVisible(true);
					combobox.bindStore(this);
					var first = this.first();
					combobox.setValue(first);
				}
			});
		}else{
			combobox.setVisible(false);
		}
		
	},
	
	renderGraficosServidor: function(mainPanel){
		
		var panel = mainPanel.down('graficopadrao').down('graficopanel');
		var form = mainPanel.down('graficopadrao').down('form');
		var values = form.getValues();
		
		var idNo = mainPanel.getIdNo();
		
		this.itemSelected = 'cpu';
		
		var strInicio = values.dataInicio + " " + values.horaInicio;
		var strFim = values.dataFim + " " + values.horaFim;
			
		this.loadGraficoProcessador(panel,idNo,strInicio,strFim);
		
	},
	
	
	
	abreGraficoServidor: function(button){
		
		var main = button.up('form').up('graficopadrao').up('servidorgraficos');
		var panel = button.up('form').up('graficopadrao').down('graficopanel');
		var values = button.up('form').getValues();

		var strInicio = values.dataInicio + " " + values.horaInicio;
		var strFim = values.dataFim + " " + values.horaFim;
		
		var idParticao = values.idParticao;
		
		var idNo = main.getIdNo();
		
		if(this.itemSelected == "memoria"){
			this.loadGraficoMemoria(panel,idNo,strInicio,strFim);
		}else if(this.itemSelected == "cpu"){
			this.loadGraficoProcessador(panel,idNo,strInicio,strFim);
		}else if(this.itemSelected == "particoes"){
			
			if(idParticao != null && idParticao > 0){
				this.loadGraficoParticoes(panel,idParticao,strInicio,strFim);
			}else{
				Ext.MessageBox.alert("Alerta","Selecione uma partição.");
			}
			
		}
			
	},
	
	renderGraficosServidorAplicacao: function(mainPanel){
		
		var panel = mainPanel.down('graficopadrao').down('graficopanel');
		var form = mainPanel.down('graficopadrao').down('form');
		var values = form.getValues();
		
		var idNo = mainPanel.getIdNo();
		
		this.itemSelected = 'cpu';
		
		var strInicio = values.dataInicio + " " + values.horaInicio;
		var strFim = values.dataFim + " " + values.horaFim;
			
		this.loadGraficoProcessadorServidorAplicacao(panel,idNo,strInicio,strFim);
		
	},
	
	abreGraficoServidorAplicacao: function(button){
		
		var main = button.up('form').up('graficopadrao').up('servidoraplicacaograficos');
		var panel = button.up('form').up('graficopadrao').down('graficopanel');
		var values = button.up('form').getValues();

		
		var strInicio = values.dataInicio + " " + values.horaInicio;
		var strFim = values.dataFim + " " + values.horaFim;
		
		var idNo = main.getIdNo();
		
		if(this.itemSelected == "heap"){
			this.loadGraficoMemoriaHeap(panel,idNo,strInicio,strFim);
		}else if(this.itemSelected == "nonheap"){
			this.loadGraficoMemoriaNonHeap(panel,idNo,strInicio,strFim);
		}else if(this.itemSelected == "cpu"){
			this.loadGraficoProcessadorServidorAplicacao(panel,idNo,strInicio,strFim);
		}
			
	},
	
	
	renderGraficosBancoDados: function(mainPanel){
		
		var panel = mainPanel.down('graficopadrao').down('graficopanel');
		var form = mainPanel.down('graficopadrao').down('form');
		var values = form.getValues();
		
		this.itemSelected = 'arquivos';
		
		var combobox = form.down('combobox[name=idArquivo]');
				
		var storeFiles = Ext.create('MONITOR.store.BancoFiles');
		
		storeFiles.load({
			scope: this,
			params:{
				id: mainPanel.getIdNo()
			},
			callback: function (records){
				combobox.setVisible(true);
				combobox.bindStore(storeFiles);
				var first = storeFiles.first();
				combobox.setValue(first);
				
				var idArquivo = first.get('id');
				
				var strInicio = values.dataInicio + " " + values.horaInicio;
				var strFim = values.dataFim + " " + values.horaFim;
			
				this.loadGraficoArquivosBancoDados(panel,idArquivo,strInicio,strFim);
			}
		});
		
		
		

		
	},
	
	
	
	abreGraficoBancoDados: function(button){
		
		var main = button.up('form').up('graficopadrao').up('bancodadosgraficos');
		var panel = button.up('form').up('graficopadrao').down('graficopanel');
		var values = button.up('form').getValues();

		
		var strInicio = values.dataInicio + " " + values.horaInicio;
		var strFim = values.dataFim + " " + values.horaFim;
		
		var idNo = main.getIdNo();
		var idArquivo  = values.idArquivo;
		
		if(this.itemSelected == "arquivos"){
			
			if(idArquivo != null && idArquivo > 0){
				this.loadGraficoArquivosBancoDados(panel,idArquivo,strInicio,strFim);
			}else{
				Ext.MessageBox.alert("Alerta","Selecione um Arquivo.");
			}
			
		}else if(this.itemSelected == "jobs"){
			//this.loadGraficoMemoriaNonHeap(panel,idNo,strInicio,strFim);
		}else if(this.itemSelected == "memoria"){
			this.loadGraficoMemoriaBancoDados(panel,idNo,strInicio,strFim);
		}
			
	},
	
	//Graficos
    loadGraficoMemoria: function(panel,idNo,inicio,fim){
    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.MemoriaColetas');
      	store.load({
    		params: {
    			idNo: idNo,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual'],
	        					minimum: 0,
	        					maximum: 100,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         	
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertB(record.get('valor')) + '<br/>';
        			            		html += 'Total: ' +  MONITOR.utils.ConvertUtils.convertB(record.get('max'))  + '<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentual') + ' %';
        			            		
        			            	    this.setTitle('Memória');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    
    loadGraficoProcessador: function(panel,idNo,inicio,fim){
    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.ProcessadorColetas');
      	store.load({
    		params: {
    			idNo: idNo,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual'],
	        					minimum: 0,
	        					maximum: 100,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 60,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentual') + ' %';
        			            		
        			            	    this.setTitle('CPU');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    
    loadGraficoParticoes: function(panel,idParticao,inicio,fim){
    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.ParticaoColetas');
      	store.load({
    		params: {
    			idParticao: idParticao,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual'],
	        					minimum: 0,
	        					maximum: 100,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertKb(record.get('valor')) + '<br/>';
        			            		html += 'Total: ' +  MONITOR.utils.ConvertUtils.convertKb(record.get('max'))  + '<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentual') + ' %';
        			            		
        			            	    this.setTitle('Partição');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    
    
    loadGraficoMemoriaHeap: function(panel,idNo,inicio,fim){

    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.MemoriaHeapServidorAplicacaoColetas');
      	store.load({
    		params: {
    			idNo: idNo,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				console.log(records);
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				legend: {
        		             position: 'right'
        		        },
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual','valorPercentualAlt'],
	        					minimum: 0,
	        					maximum: 100,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
        			             title: 'Used',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertB(record.get('valor')) + '<br/>';
        			            		html += 'Total: ' +  MONITOR.utils.ConvertUtils.convertB(record.get('max'))  + '<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentual') + ' %';
        			            		
        			            	    this.setTitle('Memoria Heap - Used');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         },
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentualAlt',
        			             title: 'Commited',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
            			             'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertB(record.get('valorAlt')) + '<br/>';
        			            		html += 'Total: ' +  MONITOR.utils.ConvertUtils.convertB(record.get('max'))  + '<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentualAlt') + ' %';
        			            		
        			            	    this.setTitle('Memoria Heap - Commited');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    
    loadGraficoMemoriaNonHeap: function(panel,idNo,inicio,fim){
    	
    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.MemoriaNonHeapServidorAplicacaoColetas');
      	store.load({
    		params: {
    			idNo: idNo,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				console.log(records);
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				legend: {
        		             position: 'right'
        		        },
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual','valorPercentualAlt'],
	        					minimum: 0,
	        					maximum: 100,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
        			             title: 'Used',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertB(record.get('valor')) + '<br/>';
        			            		html += 'Total: ' +  MONITOR.utils.ConvertUtils.convertB(record.get('max'))  + '<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentual') + ' %';
        			            		
        			            	    this.setTitle('Memoria Non-Heap - Used');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         },
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentualAlt',
        			             title: 'Commited',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
            			             'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertB(record.get('valorAlt')) + '<br/>';
        			            		html += 'Total: ' +  MONITOR.utils.ConvertUtils.convertB(record.get('max'))  + '<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentualAlt') + ' %';
        			            		
        			            	    this.setTitle('Memoria Non-Heap - Commited');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    
    loadGraficoProcessadorServidorAplicacao: function(panel,idNo,inicio,fim){
    	
    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.ProcessadorServidorAplicacaoColetas');
      	store.load({
    		params: {
    			idNo: idNo,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				console.log(records);
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				legend: {
        		             position: 'right'
        		        },
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual','valorPercentualAlt'],
	        					minimum: 0,
	        					maximum: 100,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
        			             title: 'CPU Time',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + record.get('valorPercentual') + ' %<br/>';
        			            		
        			            	    this.setTitle('CPU - CPU Time');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         },
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentualAlt',
        			             title: 'User Time',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
            			             'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + record.get('valorPercentual') + ' %<br/>';
        			            		
        			            	    this.setTitle('CPU - User Time');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    
    loadGraficoArquivosBancoDados: function(panel,idArquivo,inicio,fim){
    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.FileBancoDadosColetas');
      	store.load({
    		params: {
    			idArquivo: idArquivo,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				
        				axes:[
	        				{
	        					title: 'Valor (MB)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valor'],
	        					minimum: 0,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valor',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertMb(record.get('valor')) + '<br/>';
        			            		
        			            		
        			            		if(record.get('max') > 0){
        			            			html += 'Total:  ' + MONITOR.utils.ConvertUtils.convertMb(record.get('max')) + '<br/>';
        			            		}else{
        			            			html += 'Total: Ilimitado<br/>';
        			            		}
        			            		
        			            		
        			            	    this.setTitle('Arquivo');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    //JOBS
    loadGraficoMemoriaBancoDados: function(panel,idNo,inicio,fim){
    	panel.update("");
    	panel.removeAll();
    	panel.setLoading(true);
    	var store = Ext.create('MONITOR.store.MemoriaBancoDadosColetas');
      	store.load({
    		params: {
    			idNo: idNo,
    			inicio: inicio,
    			fim: fim
    		},
    		callback: function(records, operation, success){
    			
    			if(success && records.length > 0){
    				
    				var chart = Ext.create('Ext.chart.Chart', {
        				width:  700,
        				height: 300,
        				store: this,
        				animate: true,
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual'],
	        					minimum: 0,
	        					maximum: 100,
	        				    grid: {
	        				        odd: {
	        				            opacity: 1,
	        				            fill: '#ddd',
	        				            stroke: '#bbb',
	        				            'stroke-width': 1
	        				        }
	        				    }
	        				
	        				},
	        				{
	        					title: 'Data',
	        		            type: 'Time',
	        		            position: 'bottom',
	        		            fields: ['data'],
	        		            dateFormat: 'd/m/Y H:i',
	        		            step: [Ext.Date.MINUTE, 30],
	        				}
        				],
        			    series: [
        			         	
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
        			             markerConfig: {
        			                 type: 'circle',
        			                 size: 3,
        			                 radius: 3,
        			                 'stroke-width': 0
        			             },
        			             tips: {
        			            	trackMouse: true,
        			            	width: 160,
        			            	height: 80,
        			            	renderer: function(record, item) {
        			            		
        			            		var html = 'Data: ' + Ext.Date.format(record.get('data'), 'd/m/Y H:i:s')  +'<br/>';
        			            		html += 'Utilizado:  ' + MONITOR.utils.ConvertUtils.convertKb(record.get('valor')) + '<br/>';
        			            		html += 'Total: ' +  MONITOR.utils.ConvertUtils.convertKb(record.get('max'))  + '<br/>';
        			            		html += 'Percentual: ' + record.get('valorPercentual') + ' %';
        			            		
        			            	    this.setTitle('Memória');
        			            	    this.update(html);
        			            	    
        			            	}
        			            }	 
        			         }
        			    ]
        			});
    				
    				panel.add(chart);
    				
    				
    			}else{
    				panel.update('Nenhum dado no período selecionado');
    			}
    			
    			panel.setLoading(false);
    			
    		}
    	});
    	
    },
    
});