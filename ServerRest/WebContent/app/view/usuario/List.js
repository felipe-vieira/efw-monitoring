Ext.define('MONITOR.view.usuario.List', {
    extend: 'Ext.panel.Panel',
    xtype: 'usuariolist',
    
    initComponent: function(){
    	
    	this.html = "<h1>Cadastro de Usu�rios</h1>";
    	
    	this.callParent(arguments);
    }
});