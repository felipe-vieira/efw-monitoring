Ext.define('MONITOR.view.alarme.ListAlarmesNo' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.alarmenolist',
    features: [{ftype: 'grouping'}],

    initComponent: function() {

        this.columns = [
            {header: 'Data',  dataIndex: 'data',  flex: 1},
            {header: 'Status', dataIndex: 'status'}
        ];

        this.callParent(arguments);
    }
});