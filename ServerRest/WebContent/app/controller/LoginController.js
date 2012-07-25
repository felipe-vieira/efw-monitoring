Ext.define('MONITOR.controller.LoginController', {
    extend: 'Ext.app.Controller',
    views: [
    	'login.Login',
    	'login.MainScreen'
    ],
    stores: [
    	'Usuarios'
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
    	
    	var store = this.getUsuariosStore();
    	
    	store.load({
    	    params: {
    	        login: values.login,
    	        senha: values.senha
    	    },
    	    callback: function(records, operation, success) {
    	        if(records.length > 0 ){
    	        	win.close();
    	        	var view = Ext.widget('mainscreen');
    	        }else{
    	        	win.setLoading(false);
    	        	Ext.MessageBox.alert('Erro','Usu&aacute;rio ou senha inv&aacute;lidos.');
    	        }
    	    }
    	});

    }


});