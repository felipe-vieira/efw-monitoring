Ext.define('MONITOR.view.login.MainTab', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.maintab',
   
    initComponent: function() {
	    this.items =  [
	        {
	           	title: 'Foo',
	           	closable: 'true'
	        }
	    ];

		this.callParent();
    }

});