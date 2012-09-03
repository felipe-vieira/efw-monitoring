Ext.define('MONITOR.controller.UsuarioController', {
    extend: 'Ext.app.Controller',
    views: [
    	'usuario.CrudUsuario',
    	'usuario.FormUsuario'
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
    		
    		'crudusuario > grid': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectUser
    		},
    		
    		'#toolbarusuario button[action=create]': {
    			click: this.create
    		},
    		
    		'#toolbarusuario button[action=edit]': {
    			click: this.edit
    		},
    		
    		'#toolbarusuario button[action=delete]': {
    			click: this.del
    		},
    		
    		'formusuario button[action=save]': {
    			click: this.saveOrUpdate
    		},
    		
    		
    	});
    	
    
    },

    
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	selectUser: function(grid, record){
		this.itemSelected = record;
	},
	
	create: function(button){
		var view = Ext.widget('formusuario');
		var user = Ext.create('MONITOR.model.Usuario');
		view.down('form').loadRecord(user);	
	},
	
	edit: function(button){
		
		if(this.itemSelected != null){
			
			var view = Ext.widget('formusuario');
			view.down('form').loadRecord(this.itemSelected);
			
		}else{
			
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de alterar.");
		
		}
		
	},
	
	del: function(button){
		if(this.itemSelected != null){
			Ext.MessageBox.confirm('Confirmação','Deseja excluir o usuario '+this.itemSelected.get('login')+' ?',
				function(resp){
					if(resp=="yes"){
						
						this.itemSelected.destroy({
		        			success: function(rec,op){
		        				var store = Ext.data.StoreManager.lookup('Usuarios');
		        				store.reload();
		        			},
		        			failure: function(rec,op){
		                        Ext.MessageBox.show({
		                            title: 'Erro',
		                            msg: op.request.scope.reader.jsonData["message"],
		                            icon: Ext.MessageBox.ERROR,
		                            buttons: Ext.Msg.OK
		                        });
		        			}
						});
						
					}
				},this);
			
			
		}else{
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de excluir.");
		}
		
		
	},
	
	saveOrUpdate: function(button){
	    var win    = button.up('window');
        var form   = win.down('form');
        var record = form.getRecord();
        var values = form.getValues();
	    var store = this.getUsuariosStore();
	    
        if(form.getForm().isValid()){
        	record.set(values);
        	
        	record.save(
        		{
        			success: function(rec,op){
        				win.close();
        				store.reload();
        			},
        			failure: function(rec,op){
                        Ext.MessageBox.show({
                            title: 'Erro',
                            msg: op.request.scope.reader.jsonData["message"],
                            icon: Ext.MessageBox.ERROR,
                            buttons: Ext.Msg.OK
                        });
        			}
        		}
        	);
        	
        	
        }
        
	},
	
  
});