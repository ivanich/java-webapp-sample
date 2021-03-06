Ext.define('MyApp.view.person.Add', {
    extend:'Ext.window.Window',
    alias:'widget.personadd',

    title:'Add Person',
    layout:'fit',
    autoShow:true,

    initComponent:function ()
    {
        this.items = [
            {
                xtype:'form',
                bodyPadding : 5,
                items:[
                    {
                        xtype:'textfield',
                        name:'firstName',
                        fieldLabel:'First Name'
                    },
                    {
                    	xtype:'textfield',
                    	name:'middleName',
                    	fieldLabel:'Middle Name'
                    },
                    {
                        xtype:'textfield',
                        name:'lastName',
                        fieldLabel:'Last Name'
                    },
                    {
                        xtype:'hidden',
                        name:'id',
                        fieldLabel:'id'
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text:'Save',
                action:'save'
            },
            {
                text:'Cancel',
                scope:this,
                handler:this.close
            }
        ];

        this.callParent(arguments);
    }
});