Ext.define('MONITOR.view.no.List' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.nolist',
    store: 'Nos',
    autoScroll: true,
    features: [{ftype: 'grouping'}],

    initComponent: function() {

        this.columns = [
            {header: 'Nome',  dataIndex: 'nome',  flex: 1},
            {header: 'Disponivel', dataIndex: 'disponivel', 
	             renderer:function(val){
	             	if(val){
	            		return '<img src="./resources/img/up.gif"/>';
	            	}else{
	            		return '<img src="./resources/img/down.gif"/>';
	            	}
	             }
            },
            {header: 'Tipo', dataIndex: 'tipo', hidden : true}
        ];

        this.callParent(arguments);
    }
});