Ext.define('MONITOR.view.janelasla.FormJanelaSla', {
    extend: 'Ext.window.Window',
    alias: 'widget.formjanelasla',

    title: 'Cadastro - Janela SLA',
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
                       	name: 'slaId',                    	
                       	store: 'SlasCombo',
                       	id: 'slaId',
                       	fieldLabel: 'SLA',
                       	valueField: 'id',
                       	displayField: 'nome',
                       	queryMode: 'local',
                       	emptyText: 'Selecione',
                       	allowBlank: false,
                       	labelWidth: 120,
                        labelAlign: 'right',
                     	forceSelection: true
                    },
                    {
                        xtype: 'textfield',
                        name : 'descricao',
                        fieldLabel: 'Descrição:',
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
                    	xtype: 'datefield',
                    	fieldLabel: 'Data Inicio',
                    	name: 'dataInicio',
                    	msgTarget:'under',
                    	allowBlank: false,
                    	labelWidth: 120,
                        labelAlign: 'right'
                    },
                    {
                    	xtype: 'datefield',
                    	fieldLabel: 'Data Fim',
                    	name: 'dataFim',
                    	msgTarget:'under',
                    	allowBlank: false,
                    	labelWidth: 120,
                        labelAlign: 'right'
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