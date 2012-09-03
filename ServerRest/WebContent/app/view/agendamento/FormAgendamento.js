Ext.define('MONITOR.view.agendamento.FormAgendamento', {
    extend: 'Ext.window.Window',
    alias: 'widget.formagendamento',

    title: 'Novo Agendamento',
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
                         xtype: 'combobox',
                         name: 'noId',                    	
                         store: 'Nos',
                         fieldLabel: 'Nó',
                         valueField: 'id',
                         displayField: 'nome',
                         queryMode: 'local',
                         emptyText: 'Selecione',
                         labelWidth: 120,
                         labelAlign: 'right',
                         allowBlank: false,
                         forceSelection: true
                    },
                    {
                        xtype: 'textfield',
                        name : 'horaInicio',
                        fieldLabel: 'Hora Inicio (hh:ms)',
                        labelWidth: 120,
                        labelAlign: 'right',
                        allowBlank: false
                    },
                    {
                        xtype: 'textfield',
                        name : 'horaFim',
                        fieldLabel: 'Hora Fim (hh:ms)',
                        labelWidth: 120,
                        labelAlign: 'right',
                        allowBlank: false
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'intervalo',
                    	labelWidth: 120,
                    	fieldLabel: 'Intervalo (mins.)',
                    	allowDecimal: false,
                    	allowBlank: false,
                    	labelAlign: 'right',
                        minValue: 1,
                        hideTrigger:true
                    },
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