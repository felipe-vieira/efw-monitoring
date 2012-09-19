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
	         	{
	         		xtype: 'tbtext',
	         		text: 'Selecione uma solu��o',
	         		id: 'txtSelecione'
	         	},
	         	{
	         		xtype: 'tbtext',
	         		text: 'Avalie:',
	         		id: 'txtAvalie',
	         		hidden: true,
	         		
	         	},
	         	{
	         		xtype: 'tbtext',
	         		text: 'Solu��o avaliada como positiva.',
	         		id: 'txtAvaliadaPositiva',
	         		hidden: true,
	         		
	         	},
	         	{
	         		xtype: 'tbtext',
	         		text: 'Solu��o avaliada como Negativa.',
	         		id: 'txtAvaliadaNegativa',
	         		hidden: true,
	         		
	         	},
	         	{
	        	    text: '(Desfazer)',
	        	    action: 'desfazer',
	        	    id: 'btnDesfazer',
	        	    disabled: true,
	        	    hidden: true
                },
	            {
	        	    text: '-',
	        	    action: 'negativar',
	        	    id: 'btnNegativar',
	        	    disabled: true,
	        	    hidden: true
                },
                {
	        	    text: '+',
	        	    action: 'positivar',
	        	    id: 'btnPositivar',
	        	    disabled: true,
	        	    hidden: true
                },
                '->',
                '-',
                {
	        	    text: 'Usar essa solu��o',
	        	    action: 'usar',
	        	    id: 'btnUsar',
	        	    disabled: 'true'
                },
	        ]
    	}];
       	
       	this.callParent(arguments);
       	
    },
    


});