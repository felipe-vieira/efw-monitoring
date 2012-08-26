Ext.define('MONITOR.view.usuario.List', {
    extend: 'Ext.panel.Panel',
    xtype: 'usuariolist',
    padding: 10,
    initComponent: function(){
    	
    	this.html = "<h1>Cadastro de Usuários</h1><hr>";
    	
    	this.callParent(arguments);
    }
});