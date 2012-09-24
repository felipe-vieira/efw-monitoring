Ext.define('MONITOR.controller.GraficoController', {
	
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.DateUtils',
        'MONITOR.utils.ConvertUtils'
    ],
    views: [
        'servidor.ListGraficos',
    	'grafico.GraficoPadrao',
    	'grafico.ListMetricas',
    	'grafico.GraficoPanel'
    ],
    stores: [
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
    		
    		'listmetricas': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItem
    		}, 
    		
    		'servidorgraficos':{
    			afterrender : this.renderGraficosServidor
    		},
    		
    		'servidorgraficos > graficopadrao > form button[action=filtrar]': {
    			click: this.abreGraficoServidor
    		}
    		
    	});
    	
    
    },

    
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
    selectItem: function(grid, record){
		this.itemSelected = record.get('tipo');
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
		
		var idNo = main.getIdNo();
		
		if(this.itemSelected == "memoria"){
			this.loadGraficoMemoria(panel,idNo,strInicio,strFim);
		}else if(this.itemSelected == "cpu"){
			this.loadGraficoProcessador(panel,idNo,strInicio,strFim);
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
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual'],
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
	        		            step: [Ext.Date.HOUR, 1],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
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
        				
        				axes:[
	        				{
	        					title: 'Valor (%)',
	        					type: 'Numeric',
	        					position: 'left',
	        					fields: ['valorPercentual'],
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
	        		            step: [Ext.Date.HOUR, 1],
	        				}
        				],
        			    series: [
        			         {
        			             type: 'line',
        			             xField: 'data',
        			             yField: 'valorPercentual',
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
    	
    }
});