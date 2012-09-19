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
	         	{
	         		xtype: 'tbtext',
	         		text: 'Selecione uma solução',
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
	         		text: 'Solução avaliada como positiva.',
	         		id: 'txtAvaliadaPositiva',
	         		hidden: true,
	         		
	         	},
	         	{
	         		xtype: 'tbtext',
	         		text: 'Solução avaliada como Negativa.',
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
	        	    text: 'Usar essa solução',
	        	    action: 'usar',
	        	    id: 'btnUsar',
	        	    disabled: 'true'
                },
	        ]
    	}];
       	
       	this.callParent(arguments);
       	
    },
    


});