Ext.define('MONITOR.view.servidorAplicacao.FormJBoss', {
    extend: 'Ext.window.Window',
    alias: 'widget.formjboss',

    title: 'Cadastro de Servidor de Aplicacao - JBoss',
    layout: 'fit',
    autoShow: true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                bodyPadding: 10,
                items: [
                    {
                        xtype: 'textfield',
                        name : 'nome',
                        fieldLabel: 'Nome',
                        allowBlank: false
                    },
                    {
                        xtype: 'textfield',
                        name : 'hostname',
                        fieldLabel: 'Hostname',
                        allowBlank: false
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'agentPort',
                    	fieldLabel: 'Porta do Agente',
                    	allowBlank: false,
                        minValue: 1,
                        maxValue: 65535,
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'port',
                    	fieldLabel: 'Porta do HTTP',
                    	allowBlank: false,
                        minValue: 1,
                        maxValue: 65535,
                        hideTrigger:true
                    	
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'jmxPort',
                    	fieldLabel: 'Porta do JMX',
                    	allowBlank: false,
                        minValue: 1,
                        maxValue: 65535,
                        hideTrigger:true
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text: 'Salvar',
                action: 'save',
                formBind: 'true'
            },
            {
                text: 'Cancelar',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});