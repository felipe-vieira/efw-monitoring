Ext.define('MONITOR.view.servidorAplicacao.FormGlassFish', {
    extend: 'Ext.window.Window',
    alias: 'widget.formglassfish',

    title: 'Cadastro de Servidor de Aplicacao - Glassfish',
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
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'port',
                    	fieldLabel: 'Porta do HTTP',
                    	allowBlank: false,
                    	allowDecimal: false,
                        minValue: 1,
                        maxValue: 65535,
                        hideTrigger:true
                    	
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'jmxPort',
                    	fieldLabel: 'Porta do JMX',
                    	allowBlank: false,
                    	allowDecimal: false,
                        minValue: 1,
                        maxValue: 65535,
                        hideTrigger:true
                    },
                    {
                    	xtype: 'textfield',
                    	name: 'jmxUser',
                    	fieldLabel: 'Usuário JMX',
                    	allowBlank: false
                    },
                    {
                    	xtype: 'textfield',
                    	name: 'jmxSenha',
                    	fieldLabel: 'Senha JMX',
                    	inputType: 'password',
                    	allowBlank: false
                    },
                    {
                    	xtype: 'combobox',
                    	name: 'thresholdId',                    	
                    	store: 'ServidorAplicacaoThresholds',
                    	id: 'thresholdId',
                    	fieldLabel: 'Threshold',
                    	valueField: 'id',
                    	displayField: 'nome',
                    	queryMode: 'local',
                    	emptyText: 'Não Selecionado',
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
                    	emptyText: 'Não Selecionado',
                    	forceSelection: true
                    }
                ]
            }
        ];
        
        this.listeners = {
        		beforerender: function(window){
        			
        			var form = window.down('form');
        			
        			var cmbThresholds = form.down('combobox[name=thresholdId]');
        			cmbThresholds.getStore().reload();
        			var firstThreshold = cmbThresholds.getStore().first();
        			cmbThresholds.setValue(firstThreshold);
        			
        			var cmbSlas = form.down('combobox[name=slaId]');
        			cmbSlas.getStore().reload();
        			var firstSla = cmbSlas.getStore().first();
            		cmbSlas.setValue(firstSla);
            		
        		}
        };

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