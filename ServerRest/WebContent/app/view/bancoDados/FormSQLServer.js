Ext.define('MONITOR.view.bancoDados.FormSQLServer', {
    extend: 'Ext.window.Window',
    alias: 'widget.formsqlserver',

    title: 'Cadastro de Banco de Dados - SQL Server',
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
                    	allowDecimal: false,
                    	minValue: 1,
                        maxValue: 65535,
                        hideTrigger: true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'port',
                    	fieldLabel: 'Porta do SQL Server',
                    	allowDecimal: false,
                    	allowBlank: false,
                    	minValue: 1,
                        maxValue: 65535,
                        hideTrigger: true
                    	
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
                    },
                    {
                    	xtype: 'textfield',
                    	name: 'database',
                    	fieldLabel: 'Database',
                    	allowBlank: false
                    },
                    {
                    	xtype: 'combobox',
                    	name: 'thresholdId',                    	
                    	store: 'BancoDadosThresholds',
                    	id: 'thresholdId',
                    	fieldLabel: 'Threshold',
                    	valueField: 'id',
                    	displayField: 'nome',
                    	queryMode: 'local',
                    	emptyText: 'Selecione',
                    	forceSelection: true
                    },
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
                    	forceSelection: true
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