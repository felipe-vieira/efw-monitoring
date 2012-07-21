Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'MONITOR',
    appFolder: 'app',
    controllers:[
        'Nos'
    ],
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'nolist'
                }
            ],

        });
    }
});