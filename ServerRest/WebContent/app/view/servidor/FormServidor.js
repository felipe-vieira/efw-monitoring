Ext.define('MONITOR.view.servidor.FormServidor', {
    extend: 'Ext.window.Window',
    alias: 'widget.formservidor',

    title: 'Cadastro de Servidor',
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
                    	xtype: 'textfield',
                    	name: 'agentPort',
                    	fieldLabel: 'Porta do Agente',
                    	allowBlank: false
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