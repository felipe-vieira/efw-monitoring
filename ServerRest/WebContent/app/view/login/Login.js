Ext.define('MONITOR.view.login.Login', {
    extend: 'Ext.window.Window',
    alias: 'widget.loginform',

    title: 'Entrar no sistema',
    height: 150,
    width: 290,
    layout: 'fit',
    closable: false,
    autoShow: true,

    initComponent: function() {
    	
        this.items = [
            {
                xtype: 'form',
                bodyPadding: 10,
                html: "",
                
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
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text: 'Ok',
                action: 'login',
                formBind: true
               
            }
        ];
        
        this.callParent(arguments);
    }
});