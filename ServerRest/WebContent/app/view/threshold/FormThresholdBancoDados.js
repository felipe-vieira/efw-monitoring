Ext.define('MONITOR.view.threshold.FormThresholdBancoDados', {
    extend: 'Ext.window.Window',
    alias: 'widget.formthresholdbancodados',

    title: 'Cadastro de Threshold - Banco de Dados',
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
                        hideTrigger:true,
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'limiteFile',
                    	fieldLabel: 'Limite de Arquivos (%)',
                        minValue: 0,
                        maxValue: 99.99,
                        decimalSeparator:',',
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'limiteTempoBackup',
                    	fieldLabel: 'Limite de tempo sem Backup (secs.)',
                        minValue: 0,
                        allowDecimal: false,
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'limiteTempoJob',
                    	fieldLabel: 'Limite de duração de Job (secs.)',
                        minValue: 0,
                        allowDecimal: false,
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