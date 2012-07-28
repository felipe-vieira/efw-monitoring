Ext.define('MONITOR.view.servidor.ListParticoes' ,{
	
    extend: 'Ext.grid.Panel',
    requires: ['MONITOR.utils.ConvertUtils'],
    alias: 'widget.particoeslist',
    title: 'Partições',

    initComponent: function() {

        this.columns = [
            {header: 'Nome',  dataIndex: 'nome',  flex: 1},
            {header: 'Sistema de Arquivos',  dataIndex: 'sistemaArquivo',  flex: 1},
            {header: 'Tamanho', dataIndex: 'tamanho', 
	             renderer:function(val){
	            	 return MONITOR.utils.ConvertUtils.convertKb(val);
	             }
            },
            {header: 'Tipo', dataIndex: 'tipo', hidden : true}
        ];

        this.callParent(arguments);
    }
});