Ext.define('MONITOR.view.usuario.FormUsuario', {
    extend: 'Ext.window.Window',
    alias: 'widget.formusuario',

    title: 'Cadastro de Usuário',
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
                        name : 'login',
                        fieldLabel: 'Login',
                        allowBlank: false
                    },
                    {
                        xtype: 'textfield',
                        name : 'senha',
                        fieldLabel: 'Senha',
                        inputType: 'password',
                        allowBlank: false
                    },
                    {
                    	xtype: 'checkboxfield',
                    	inputValue: true,
                    	uncheckedValue: false,
                    	name: 'administrador',
                    	fieldLabel: 'Administador'
                    },
                    {
                    	xtype: 'checkboxfield',
                    	inputValue: true,
                    	uncheckedValue: false,
                    	name: 'enviarEmail',
                    	fieldLabel: 'Enviar alarmes por E-mail'
                    },
                    {
                    	xtype: 'textfield',
                    	disabled: true,
                    	name: 'email',
                    	fieldLabel: 'E-mail'
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