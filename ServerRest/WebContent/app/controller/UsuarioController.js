Ext.define('MONITOR.controller.UsuarioController', {
    extend: 'Ext.app.Controller',
    views: [
    	'usuario.ListUsuario',
    	'usuario.CreateUsuario'
    ],
    stores: [
        'Usuarios'
    ],
    models: [
        'Usuario'     
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
    	this.control({
    		
    		'usuariolist > grid': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectUser
    		},
    		
    		'toolbar button[action=create]': {
    			click: this.create
    		},
    		
    		'toolbar button[action=edit]': {
    			click: this.edit
    		},
    		
    		'toolbar button[action=delete]': {
    			click: this.del
    		},
    		
    		'createusuario button[action=save]': {
    			click: this.saveOrUpdate
    		}
    		
    	});
    },

    
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	selectUser: function(grid, record){
		this.itemSelected = record;
	},
	
	create: function(button){
		var view = Ext.widget('createusuario');
		var user = Ext.create('MONITOR.model.Usuario');
		view.down('form').loadRecord(user);	
	},
	
	edit: function(button){
		
		if(this.itemSelected != null){
			
			var view = Ext.widget('createusuario');
			view.down('form').loadRecord(this.itemSelected);
			
		}else{
			
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de alterar.");
		
		}
		
	},
	
	del: function(button){
		if(this.itemSelected != null){
			Ext.MessageBox.confirm('Confirmação','Deseja excluir o usuario '+this.itemSelected.get('login')+' ?',
				function(resp){
					console.log(resp);
					if(resp=="yes"){
						console.log("opa");
						this.getUsuariosStore().remove(this.itemSelected);
					}
				},
				this
			);
		}else{
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de excluir.");
		}
	},
	
	saveOrUpdate: function(button){
	    var win    = button.up('window');
        var form   = win.down('form');
        var record = form.getRecord();
        var values = form.getValues();
	    
        if(form.getForm().isValid()){
        	record.set(values);
    	    win.close();
    	    this.getUsuariosStore().add(record);
        }
	}
	
	
  
});