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
                    	allowDecimal: false,
                        maxValue: 65535,
                        minValue: 1,
                        hideTrigger:true
                    },
                    {
                    	xtype: 'numberfield',
                    	name: 'port',
                    	fieldLabel: 'Porta do Oracle',
                    	allowBlank: false,
                    	allowDecimal: false,
                        maxValue: 65535,
                        minValue: 1,
                        hideTrigger:true
                    	
                    },
                    {
                    	xtype: 'textfield',
                    	name: 'usuario',
                    	fieldLabel: 'Usu�rio',
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
                    	fieldLabel: 'Nome da Inst�ncia',
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
                    	emptyText: 'N�o Selecionado',
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
                    	emptyText: 'N�o Selecionado',
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
        
        this.callParent(arguments);
    }
});