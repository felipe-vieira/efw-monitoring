Ext.define('MONITOR.view.solucao.DescricaoSolucao', {
    extend: 'Ext.panel.Panel',
    xtype: 'descricaosolucao',
    border: 1,
    title: 'Desri��o da Solu��o',
    overflow: 'auto',
    
    initComponent: function(){

    	this.items = [
       	     {
       	    	 id: 'descpanel',
       	    	 html: 'Selecione uma solu��o.',
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
	        	    text: 'Usar essa solu��o',
            	    action: 'usar'
                },
	        ]
    	}];
       	
       	this.callParent(arguments);
       	
    },
    


});