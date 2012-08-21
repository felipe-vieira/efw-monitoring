Ext.define('MONITOR.view.servidorAplicacao.ListDeployments' ,{
	
    extend: 'Ext.grid.Panel',
    requires: ['MONITOR.utils.ConvertUtils'],
    alias: 'widget.listdeployments',
    title: 'Deployments',

    initComponent: function() {

        this.columns = [
            {header: 'Nome',  dataIndex: 'nome',  flex: 1},
        ];

        this.callParent(arguments);
    }
});