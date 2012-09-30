Ext.define('MONITOR.view.servidorAplicacao.FormJBoss', {
    extend: 'Ext.window.Window',
    alias: 'widget.formjboss',

    title: 'Cadastro de Servidor de Aplicacao - JBoss',
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
                    	allowDecimal: false,
                    	allowBlank: false,
                        minValue: 1,
                        maxValue: 65535,
                        hideTrigger:true
                    	
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'jmxPort',
                    	fieldLabel: 'Porta do JMX',
                    	allowDecimal: false,
                    	allowBlank: false,
                        minValue: 1,
                        maxValue: 65535,
                        hideTrigger:true
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