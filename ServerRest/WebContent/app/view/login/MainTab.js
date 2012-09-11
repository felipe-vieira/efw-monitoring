Ext.define('MONITOR.view.login.MainTab', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.maintab',
    id: 'mainTab',
    
    initComponent: function() {
	    this.items =  [
	        {
	           	title: 'Inicio',
	           	xtype: 'inicioview',
	           	closable: 'true'
	        }
		this.callParent();
    }

});