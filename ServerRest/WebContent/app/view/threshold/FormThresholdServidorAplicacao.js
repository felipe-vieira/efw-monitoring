Ext.define('MONITOR.view.threshold.FormThresholdServidorAplicacao', {
    extend: 'Ext.window.Window',
    alias: 'widget.formthresholdservidoraplicacao',

    title: 'Cadastro de Threshold - Servidor Aplicação',
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
                    	name: 'thresholdHeap',
                    	fieldLabel: 'Limite de Memória Heap (%)',
                        minValue: 0,
                        maxValue: 99.99,
                        decimalSeparator:',',
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'thresholdNonHeap',
                    	fieldLabel: 'Limite de Memória Non-Heap (%)',
                        minValue: 0,
                        maxValue: 99.99,
                        decimalSeparator:',',
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'thresholdCpuUserTime',
                    	fieldLabel: 'Limite de CPU (%) - User Time',
                        minValue: 0,
                        maxValue: 99.99,
                        decimalSeparator:',',
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'thresholdCpuCpuTime',
                    	fieldLabel: 'Limite de CPU (%) - CPU Time',
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