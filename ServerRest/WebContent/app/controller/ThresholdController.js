Ext.define('MONITOR.controller.ThresholdController', {
    extend: 'Ext.app.Controller',
    views: [
        'threshold.CrudThreshold',
        'threshold.FormThresholdServidor',
        'threshold.FormThresholdServidorAplicacao',
        'threshold.FormThresholdBancoDados'
    ],
    stores: [
        'Thresholds'
    ],
    models: [
        'Threshold',
        'ServidorThreshold',
        'BancoDadosThreshold',
        'ServidorAplicacaoThreshold'
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
    	this.control({
    		'crudthreshold > grid': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItem
    		},
	    	
			'#submenuthreshold menuitem[id=createThresholdServidor]': {
				click: this.createThresholdServidor
			},
			
			'#submenuthreshold menuitem[id=createThresholdServidorAplicacao]': {
				click: this.createThresholdServidorAplicacao
			},
			
			'#submenuthreshold menuitem[id=createThresholdBancoDados]': {
				click: this.createThresholdBancoDados
			},
			
			'#toolbarthreshold button[action=edit]':{
				click: this.editThreshold
			},
			
			'#toolbarthreshold button[action=delete]':{
				click: this.deleteThreshold
			},
			
			'formthresholdservidor button[action=save]': {
				click: this.saveOrUpdate
			},
			
			'formthresholdservidoraplicacao button[action=save]': {
				click: this.saveOrUpdate
			},
			
			'formthresholdbancodados button[action=save]': {
				click: this.saveOrUpdate
			},
    	});
    	
    
    },

    createThresholdServidor: function(){
		var view = Ext.widget('formthresholdservidor');
		var model = Ext.create('MONITOR.model.ServidorThreshold');
		view.down('form').loadRecord(model);	
    },
    
    createThresholdServidorAplicacao: function(){
		var view = Ext.widget('formthresholdservidoraplicacao');
		var model = Ext.create('MONITOR.model.ServidorAplicacaoThreshold');
		view.down('form').loadRecord(model);	
    },
    
    createThresholdBancoDados: function(){
		var view = Ext.widget('formthresholdbancodados');
		var model = Ext.create('MONITOR.model.BancoDadosThreshold');
		view.down('form').loadRecord(model);		
    },
    
    editThreshold: function(button){
		if(this.itemSelected != null){
			
			var tipo = this.itemSelected.get('tipo');

			if(tipo == "Servidor"){
	    		this.editServidor(this.itemSelected);
	    	}else if (tipo == "Servidor de Aplicação"){
	    		this.editServidorAplicacao(this.itemSelected);
	    	}else if (tipo == "Banco de Dados"){
	    		this.editBancoDados(this.itemSelected);
	    	}
			
		}else{
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de alterar.");
		}
		
    },
    
    editServidor: function(record){
    	
    	var id = record.get('id');
    	
		MONITOR.model.ServidorThreshold.load(id,{
			success: function(threshold){
				var view = Ext.widget('formthresholdservidor');
				view.down('form').loadRecord(threshold);	
			}
		});
    },


    editServidorAplicacao: function(record){
    	
    	var id = record.get('id');
    	
		MONITOR.model.ServidorAplicacaoThreshold.load(id,{
			success: function(threshold){
				var view = Ext.widget('formthresholdservidoraplicacao');
				view.down('form').loadRecord(threshold);	
			}
		});
	
    },
    
    editBancoDados: function(record){
    	
    	var id = record.get('id');
    	
		MONITOR.model.BancoDadosThreshold.load(id,{
			success: function(threshold){
				var view = Ext.widget('formthresholdbancodados');
				view.down('form').loadRecord(threshold);	
			}
		});
	
    },
    
    
    
    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	selectItem: function(grid, record){
		this.itemSelected = record;
	},
	
	deleteThreshold: function(button){
		if(this.itemSelected != null){
			
			var store = this.getThresholdsStore();
			
			Ext.MessageBox.confirm('Confirmação','Deseja excluir o threshold '+this.itemSelected.get('nome')+' ?',
				function(resp){
					if(resp=="yes"){
						
						this.itemSelected.destroy({
		        			success: function(rec,op){
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
        var store  = this.getThresholdsStore();
	    
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