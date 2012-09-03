Ext.define('MONITOR.controller.AgendamentoController', {
    extend: 'Ext.app.Controller',
    views: [
         'agendamento.CrudAgendamento',
         'agendamento.FormAgendamento'
    ],
    stores: [
        'Agendamentos',
        'Nos'
    ],
    models: [
        'No',
        'Agendamento'
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
    	this.control({
    		'#toolbaragendamento button[action=create]': {
    			click: this.create
    		},
    		
    		'formagendamento button[action=save]': {
    			click: this.saveOrUpdate
    		},
    		
    		
    	});
    	
    
    },

	create: function(button){
		var view = Ext.widget('formagendamento');
		var user = Ext.create('MONITOR.model.Agendamento');
		view.down('form').loadRecord(user);	
	},
	
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	selectUser: function(grid, record){
		this.itemSelected = record;
	},

	
	saveOrUpdate: function(button){	
	    var win    = button.up('window');
        var form   = win.down('form');
        var record = form.getRecord();
        var values = form.getValues();
	    var store = this.getAgendamentosStore();
	    var noId = values.noId;
    	
	    
	    
        if(form.getForm().isValid()){
        	record.set(values);
        	record.setNo(noId);
        	
        	record.save(
        		{
        			params:{
        				'noId': noId
        			},
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
        			},
        		}
        	);
        	
        	
        }
        
	},
	
  
});