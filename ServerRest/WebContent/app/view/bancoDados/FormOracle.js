Ext.define('MONITOR.view.bancoDados.FormOracle', {
    extend: 'Ext.window.Window',
    alias: 'widget.formoracle',

    title: 'Cadastro de Banco de Dados - Oracle',
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
                        maxValue: 65535,
                        minValue: 1,
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'port',
                    	fieldLabel: 'Porta do Oracle',
                    	allowBlank: false,
                        maxValue: 65535,
                        minValue: 1,
                        hideTrigger:true
                    	
                    },
                    {
                    	xtype: 'textfield',
                    	name: 'usuario',
                    	fieldLabel: 'Usuário',
                    	allowBlank: false
                    },
                    {
                    	xtype: 'textfield',
                    	name: 'senha',
                    	fieldLabel: 'Senha',
                    	inputType: 'password',
                    	allowBlank: false
                    },
                    {
                    	xtype: 'textfield',
                    	name: 'instanceName',
                    	fieldLabel: 'Nome da Instância',
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