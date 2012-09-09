Ext.define('MONITOR.controller.SlaController', {
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.DateUtils'    
    ],
    views: [
    	'sla.CrudSla',
    	'sla.FormSla'
    ],
    stores: [
        'Slas'
    ],
    models: [
        'Sla',
        'DiasSemanaSla'
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
    	this.control({
    		
    		'crudsla > grid': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItem
    		},
    		
    		'#toolbarsla button[action=create]': {
    			click: this.create
    		},
    		
    		'#toolbarsla button[action=edit]':{
    			click: this.edit
    		},
    		
    		'#toolbarsla button[action=delete]':{
    			click: this.del
    		},
    		
    		'formsla button[action=save]':{
    			click: this.saveOrUpdate
    		},
    		

    		

    		
    	});
    	

    	
    
    },

	selectItem: function(grid, record){
		this.itemSelected = record;
	},

    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	
	create: function(button){
		var view = Ext.widget('formsla');
		var user = Ext.create('MONITOR.model.Sla');
		view.down('form').loadRecord(user);	
	},
	
	edit: function(button){
		
		if(this.itemSelected != null){

			var view = Ext.widget('formsla');
			var diasSemana = this.itemSelected.getDiasSemana();
			
			view.down('form').loadRecord(this.itemSelected);
			view.down('form').form.setValues({
				'dia1': diasSemana.get('dia1'),
				'dia2': diasSemana.get('dia2'),
				'dia3': diasSemana.get('dia3'),
				'dia4': diasSemana.get('dia4'),
				'dia5': diasSemana.get('dia5'),
				'dia6': diasSemana.get('dia6'),
				'dia7': diasSemana.get('dia7'),
			});
			
						
			
		}else{			
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de alterar.");
		}
		
	},
	
	del: function(button){
		if(this.itemSelected != null){
			var store = this.getSlasStore();
			Ext.MessageBox.confirm('Confirmação','Deseja excluir o SLA '+this.itemSelected.get('nome')+' ?',
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
	    var store = this.getSlasStore();
	    
	    
        if(form.getForm().isValid()){
        	
        	record.set(values);
        	var dias = Ext.create("MONITOR.model.DiasSemanaSla");
        	dias.set("dia1",values.dia1);
        	dias.set("dia2",values.dia2);
        	dias.set("dia3",values.dia3);
        	dias.set("dia4",values.dia4);
        	dias.set("dia5",values.dia5);
        	dias.set("dia6",values.dia6);
        	dias.set("dia7",values.dia7);
        	
        	//Converte as horas
        	var horaInicio = Ext.Date.parse(values.horaInicio, "H:i");
        	var horaFim = Ext.Date.parse(values.horaFim, "H:i");
        	
       		record.set('horaInicio',horaInicio);
    		record.set('horaFim',horaFim);
        	
        	
    		win.setLoading(true);
    		
        	record.save(
        		{
        			success: function(rec,op){
        				
        				var id = op.request.scope.reader.jsonData["id"];
        				
        				dias.set("id",id);
        				
        				dias.save({
        					success: function(rec,op){
        						win.setLoading(false);
                				win.close();
                				store.reload();
        					},
        					failure: function(rec,op){
                				win.setLoading(false);
                                Ext.MessageBox.show({
                                    title: 'Erro',
                                    msg: op.request.scope.reader.jsonData["message"],
                                    icon: Ext.MessageBox.ERROR,
                                    buttons: Ext.Msg.OK
                                });
                			}
        					
        				});
        				
        			},
        			failure: function(rec,op){
        				win.setLoading(false);
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
	}	
  
});