Ext.application({
	requires: ['Ext.container.Viewport', 'Ext.data.proxy.Rest', 'Ext.form.Panel'],
    name: 'MONITOR',
    appFolder: 'app',
    controllers:[
        'AgendamentoController',
        'LoginController',       
        'NoController',
        'ThresholdController',
        'UsuarioController',
        
    ],
    launch: function() {
    	
    Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            renderTo: Ext.getBody(),
            items: [
                {
                    xtype: 'loginform'
                }
            ],

        });
    }
});