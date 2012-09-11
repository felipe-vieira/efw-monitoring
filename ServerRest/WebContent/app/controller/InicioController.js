Ext.define('MONITOR.controller.InicioController', {
    extend: 'Ext.app.Controller',
    views: [
         'inicio.InicioView',
         'inicio.ListNosIndisponiveis',
         'inicio.ListNosNaoGerenciaveis',
         'inicio.ListAlarmesNaoLidos',
         'inicio.ListSlasForaMeta'
    ],
    
    stores: [
         'AlarmesNaoLidos',   
         'Indisponiveis',
         'NaoGerenciaveis',
         'SlasForaMeta'
    ],
    
    models: [
         'Alarme',
         'Sla',
         'SlaCalculado',
         'No'
    ],
    
    init: function() {
    	
    	this.control({
    	    'inicioview':{
    	    	activate: this.focaInicio
    	    }
    	   
    	});
    	
    },
    
    focaInicio: function(view,eOpts){
    	this.getAlarmesNaoLidosStore().reload();
    	this.getIndisponiveisStore().reload();
    	this.getNaoGerenciaveisStore().reload();
    	this.getSlasForaMetaStore().reload();
    }

});