Ext.define('MONITOR.view.bancoDados.ListJobs' ,{
	
    extend: 'Ext.grid.Panel',
    requires: ['MONITOR.utils.ConvertUtils'],
    alias: 'widget.listjobs',
    title: 'Jobs',

    initComponent: function() {

        this.columns = [
            {header: 'Nome',  dataIndex: 'jobName',  flex: 1},
            {header: 'Criador',  dataIndex: 'owner',  flex: 1}
            
        ];

        this.callParent(arguments);
    }
});