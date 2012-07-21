Ext.define('MONITOR.controller.Nos', {
    extend: 'Ext.app.Controller',
    views: [
    	'no.List',
    	'no.Edit'
    ],
    stores: [
    	'Nos'
    ],
    models: [
    	'No'
    ],
    init: function() {
        this.control({
        	'nolist' : {
        		itemdblclick: this.editUser
        	},
        	'noedit button[action=save]':{
        		click : this.updateNo
        	}
        });
    },

    editUser: function(grid, record){
    	var view = Ext.widget('noedit');
    	view.down('form').loadRecord(record);
    },

    updateNo: function(button){
    	var win = button.up('window'),
    		form = win.down('form'),
    		record = form.getRecord(),
    		values = form.getValues();

    	record.set(values);
    	win.close();
    	this.getNosStore().sync();
    	console.log('wtf omfg');
    }



});