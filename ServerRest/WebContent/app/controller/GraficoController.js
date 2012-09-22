Ext.define('MONITOR.controller.GraficoController', {
	
    extend: 'Ext.app.Controller',
    views: [
        'servidor.ListGraficos',
    	'grafico.GraficoPadrao',
    	'grafico.ListMetricas',
    ],
    stores: [
    ],
    models: [
        'Metrica' ,
        'MemoriaColeta'
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
	
	abreGraficoServidor: function(button){
		var main = button.up('form').up('graficopadrao').up('servidorgraficos');
		var panel = button.up('form').up('graficopadrao').down('panel');
		var values = button.up('form').getValues();
		
		var strInicio = values.dataInicio + " " + values.horaInicio;
		var strFim = values.dataFim + " " + values.horaFim;
		
		var idNo = main.getIdNo();
//	    var inicio = Ext.Date.parse(strInicio, "d/m/Y H:i");
//		var fim = Ext.Date.parse(strFim, "d/m/Y H:i");
		
		
		if(this.itemSelected == "memoria"){
			this.loadGraficoMemoria(panel,idNo,strInicio,strFim);
		}
			
	},
	
	//Graficos
    loadGraficoMemoria: function(panel,idNo,inicio,fim){
      	var store = Ext.create('MONITOR.store.MemoriaColetas');
      	
      	store.load({
    		params: {
    			idNo: idNo,
    			inicio: inicio,
    			fim: fim
    		},
    		success: function(){
    			console.log('parabns vc e bom rs');
    		},
    		failure: function(){
    			console.log('vish');
    		}
    	});
    	
    }
});