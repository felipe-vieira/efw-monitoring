Ext.define('MONITOR.view.solucao.DescricaoSolucao', {
    extend: 'Ext.panel.Panel',
    xtype: 'descricaosolucao',
    border: 1,
    title: 'Desrição da Solução',
    overflow: 'auto',
    
    initComponent: function(){

    	this.items = [
       	     {
       	    	 id: 'descpanel',
       	    	 html: 'Selecione uma solução.',
       	         minHeight: 150
       	     }
       	];
       	
       			
       	
       	
       	this.dockedItems = [{
	    	xtype: 'toolbar',
	        dock: 'bottom',
	        id: 'toolbardescricaosolucao',
	        items:
	        [
	         	'Avalie:',
	         	
	            {
	        	    text: '-',
	        	    name: 'negativar'
                },
                '',
                {
	        	    text: '+',
	        	    action: 'positivar',
                },
                '->',
                {
	        	    text: 'Usar essa solução',
            	    action: 'usar'
                },
	        ]
    	}];
       	
       	this.callParent(arguments);
       	
    },
    


});