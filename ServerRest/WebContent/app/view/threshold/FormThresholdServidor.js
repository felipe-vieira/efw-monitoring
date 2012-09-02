Ext.define('MONITOR.view.threshold.FormThresholdServidor', {
    extend: 'Ext.window.Window',
    alias: 'widget.formthresholdservidor',

    title: 'Cadastro de Threshold - Servidor',
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
                    	xtype: 'numberfield',
                    	name: 'limiteMemoria',
                    	fieldLabel: 'Limite de Memória (%)',
                        minValue: 0,
                        maxValue: 99.99,
                        decimalSeparator:',',
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'limiteCpu',
                    	fieldLabel: 'Limite de CPU (%)',
                        minValue: 0,
                        maxValue: 99.99,
                        decimalSeparator:',',
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'limiteParticao',
                    	fieldLabel: 'Limite de Partição (%)',
                        minValue: 0,
                        maxValue: 99.99,
                        decimalSeparator:',',
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