Ext.define('MONITOR.controller.JanelaSlaController', {
    extend: 'Ext.app.Controller',
    requires: [
        'MONITOR.utils.DateUtils'    
    ],
    views: [
    	'janelasla.CrudJanelaSla',
    	'janelasla.FormJanelaSla'
    ],
    stores: [
        'SlasCombo',     
        'JanelasSla'
    ],
    models: [
        'Sla',
        'JanelaSla',
        'DiasSemanaJanelaSla'
    ],
    
    init: function() {
    	
    	Ext.apply(this,{
    		itemSelected: null
    	});
    	
    	this.control({
    		
    		'crudjanelasla > grid': {
    			beforerender: this.resetRegister,
    			itemclick: this.selectItem
    		},
    		
    		'#toolbarjanelasla button[action=create]': {
    			click: this.create
    		},
    		
    		'formjanelasla button[action=save]':{
    			click: this.saveOrUpdate
    		},
    		
    		/*
    		'#toolbarsla button[action=edit]':{
    			click: this.edit
    		},
    		
    		'#toolbarsla button[action=delete]':{
    			click: this.del
    		},
    		
    		
    		
    		 */
    		

    		
    	});
    	

    	
    
    },

	selectItem: function(grid, record){
		this.itemSelected = record;
	},

    resetRegister: function(){
    	this.itemSelected = null;
    },
    
	
	create: function(button){
		
		this.getSlasComboStore().reload();
		var view = Ext.widget('formjanelasla');
		var user = Ext.create('MONITOR.model.JanelaSla');
		view.down('form').loadRecord(user);	
	},
	
	edit: function(button){
		
		if(this.itemSelected != null){

			var strHoraInicio = MONITOR.utils.DateUtils.toHours(this.itemSelected.get('horaInicio'));
			var strHoraFim = MONITOR.utils.DateUtils.toHours(this.itemSelected.get('horaFim'));
			
			var view = Ext.widget('formsla');
			var diasSemana = this.itemSelected.getDiasSemana();
			
			view.down('form').loadRecord(this.itemSelected);
			view.down('form').form.setValues({
				'horaInicio': strHoraInicio,
				'horaFim': strHoraFim,
				'dia1': diasSemana.get('dia1'),
				'dia2': diasSemana.get('dia2'),
				'dia3': diasSemana.get('dia3'),
				'dia4': diasSemana.get('dia4'),
				'dia5': diasSemana.get('dia5'),
				'dia6': diasSemana.get('dia6'),
				'dia7': diasSemana.get('dia7')
			});
						
			
		}else{			
			Ext.MessageBox.alert("Alerta","Selecione um registro antes de alterar.");
		}
		
	},
	
	del: function(button){
		if(this.itemSelected != null){
			var store = this.getJanelasSlaStore();
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
	    var store = this.getJanelasSlaStore();
	    
	    
        if(form.getForm().isValid()){

        	record.set(values);
        	var dias = Ext.create("MONITOR.model.DiasSemanaJanelaSla");
        	dias.set("dia1",values.dia1);
        	dias.set("dia2",values.dia2);
        	dias.set("dia3",values.dia3);
        	dias.set("dia4",values.dia4);
        	dias.set("dia5",values.dia5);
        	dias.set("dia6",values.dia6);
        	dias.set("dia7",values.dia7);
        	
        	var slaId = values.slaId;
        	
        	//Converte as horas
        	var horaInicio = MONITOR.utils.DateUtils.stringToTime(values.horaInicio);
        	var horaFim = MONITOR.utils.DateUtils.stringToTime(values.horaFim);
        
        	console.log(values.dataInicio);
        	console.log(values.dataFim);
        	
        	var dataInicio = Ext.Date.parse(values.dataInicio, "d/m/Y");
    		var dataFim = Ext.Date.parse(values.dataFim, "d/m/Y");
    		
    		
        	if(horaInicio!= null && horaFim != null){
        		win.setLoading(true);
        		
        		record.set('horaInicio',horaInicio);
        		record.set('horaFim',horaFim);
        		record.set('dataInicio',dataInicio);
        		record.set('dataFim',dataFim);
        		
        		
	        	record.save(
	        		{
	        			
	        			params:{
	        				'slaId': slaId
	        			},
	        			success: function(rec,op){
	        				
	        				var id = op.request.scope.reader.jsonData["id"];
	        				
	        				dias.set("id",id);
	        				dias.save();
	        				
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
	        		}
	        	);
        	}
        }
	}	
  
});