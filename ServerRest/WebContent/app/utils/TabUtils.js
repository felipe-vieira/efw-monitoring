Ext.define("MONITOR.utils.TabUtils", {
	singleton : true,
	
	openShowTab : function (xtype,titulo){

		var opened = Ext.ComponentQuery.query(xtype);
		
		if(opened !== null && opened.length > 0){
			opened[0].show(); 
		}else{
			var tabs = Ext.ComponentQuery.query('#mainTab');
			tabs[0].add({
				closable: true,
				title: titulo,
				xtype: xtype
			}).show();
			
		}
		
		
	}
	
});
