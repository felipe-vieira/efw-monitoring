Ext.define('MONITOR.controller.InicioController', {
    extend: 'Ext.app.Controller',
    views: [
         'inicio.InicioView',
         'inicio.ListNosIndisponiveis',
         'inicio.ListNosNaoGerenciaveis'
    ],
    
    stores: [
         'Indisponiveis',
         'NaoGerenciaveis'
    ],
    
    models: [
    ],
    
    init: function() {
    
    }

});