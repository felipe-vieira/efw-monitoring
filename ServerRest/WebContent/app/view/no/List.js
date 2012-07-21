Ext.define('MONITOR.view.no.List' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.nolist',

    store: 'Nos',
    title: 'Todos os Nós',


    initComponent: function() {

        this.columns = [
            {header: 'Name',  dataIndex: 'nome',  flex: 1},
            {header: 'Disponivel', dataIndex: 'disponivel', flex: 1}
        ];

        this.callParent(arguments);
    }
});