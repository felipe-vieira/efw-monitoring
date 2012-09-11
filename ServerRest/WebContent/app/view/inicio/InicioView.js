Ext.define('MONITOR.view.inicio.InicioView', {
    extend: 'Ext.panel.Panel',
    xtype: 'inicioview',
    padding: 10,
    border: 0,
    autoScroll: 'auto',
    
    initComponent: function(){
    	this.html = "meu cu";
    	this.callParent(arguments);
    }

});