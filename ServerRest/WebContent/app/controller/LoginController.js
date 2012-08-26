Ext.define('MONITOR.controller.LoginController', {
    extend: 'Ext.app.Controller',
    views: [
    	'login.Login',
    	'login.MainScreen',
    	'login.MainTab',
    	'login.MainMenu'
    ],
    stores: [
    	'UsuariosLogin'
    ],
    models: [
    	'Usuario'
    ],
    
    init: function() {
        this.control({
        	'loginform button[action=login]':{
        		click : this.realizaLogin
        	}
        });
    },
    
    realizaLogin: function(button){
    	
    	var win = button.up('window');
    	
    	win.setLoading(true);
    	
    	var form = win.down('form');
    	var values = form.getValues();
    	
    	var store = this.getUsuariosLoginStore();
    	
    	store.load({
    	    params: {
    	        login: values.login,
    	        senha: values.senha
    	    },
    	    callback: function(records, operation, success) {
    	        if(records.length > 0 ){
    	        	win.hide();
    	        	var viewport = win.up();
    	        	viewport.add({xtype: 'mainscreen', height: '100%', width: '100%'});
    	        }else{
    	        	win.setLoading(false);
    	        	Ext.MessageBox.alert('Erro','Usu&aacute;rio ou senha inv&aacute;lidos.');
    	        }
    	    }
    	});

    }


});