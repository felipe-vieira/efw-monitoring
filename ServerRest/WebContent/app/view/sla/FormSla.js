Ext.define('MONITOR.view.sla.FormSla', {
    extend: 'Ext.window.Window',
    alias: 'widget.formsla',

    title: 'Novo SLA',
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
                        fieldLabel: 'Nome:',
                        labelWidth: 120,
                        labelAlign: 'right',
                        allowBlank: false
                    },
                    {
                    	xtype: 'timefield',
                        name: 'horaInicio',
                        fieldLabel: 'Hora Inicio',
                        minValue: '0:00  ',
                        maxValue: '23:59 ',
                        increment: 30,
                        anchor: '100%',
                        labelWidth: 120,
                        labelAlign: 'right',
                        format : 'H:i'
                    }, 
                    {
                        xtype: 'timefield',
                        name: 'horaFim',
                        fieldLabel: 'Hora Fim',
                        minValue: '0:00  ',
                        maxValue: '23:59 ',
                        increment: 30,
                        anchor: '100%',
                        labelWidth: 120,
                        labelAlign: 'right',
                        format : 'H:i'
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'meta',
                    	labelWidth: 120,
                    	fieldLabel: 'Meta',
                    	allowDecimal: true,
                    	allowBlank: false,
                    	labelAlign: 'right',
                        minValue: 1,
                        maxValue: 100,
                        hideTrigger:true
                    },
                    {
                    	xtype: 'fieldcontainer',
                    	layout: 'hbox',
                    	items: [
                                {
                                	xtype: 'checkboxfield',
                                	inputValue: true,
                                	uncheckedValue: false,
                                	name: 'dia1',
                                	fieldLabel: 'Dom',
                                    labelWidth: 50,
                                    labelAlign: 'right'                                		
                                },
                                {
                                	xtype: 'checkboxfield',
                                	inputValue: true,
                                	uncheckedValue: false,
                                	name: 'dia2',
                                	fieldLabel: 'Seg',
                                	labelWidth: 50,
                                    labelAlign: 'right'
                                },
                                {
                                	xtype: 'checkboxfield',
                                	inputValue: true,
                                	uncheckedValue: false,
                                	name: 'dia3',
                                	fieldLabel: 'Ter',
                                	labelWidth: 50,
                                    labelAlign: 'right'
                                }
                    	]
                    },
                    {
                    	xtype: 'fieldcontainer',
                    	layout: 'hbox',
                    	items: [
                                {
                                	xtype: 'checkboxfield',
                                	inputValue: true,
                                	uncheckedValue: false,
                                	name: 'dia4',
                                	fieldLabel: 'Qua',
                                	labelWidth: 50,
                                    labelAlign: 'right'
                                },
                                {
                                	xtype: 'checkboxfield',
                                	inputValue: true,
                                	uncheckedValue: false,
                                	name: 'dia5',
                                	fieldLabel: 'Qui',
                                	labelWidth: 50,
                                    labelAlign: 'right'
                                },                                
								{
                                	xtype: 'checkboxfield',
                                	inputValue: true,
                                	uncheckedValue: false,
                                	name: 'dia6',
                                	fieldLabel: 'Sex',
                                	labelWidth: 50,
                                    labelAlign: 'right'
                                },
                    	]
                    },
                    {
                    	xtype: 'checkboxfield',
                    	inputValue: true,
                    	uncheckedValue: false,
                    	name: 'dia7',
                    	fieldLabel: 'Sab',
                    	labelWidth: 50,
                        labelAlign: 'right'
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