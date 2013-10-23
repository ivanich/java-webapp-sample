Ext.define('MyApp.view.Main', {
    extend:'Ext.container.Viewport',
    layout:'fit',

    requires:[
        'MyApp.view.person.List',
        'MyApp.view.person.Edit',
        'MyApp.view.person.Add'
    ],

    initComponent:function ()
    {
        this.items = {items: [
            {
            	padding : 10,
                xtype:'personlist'
            }
        ]};

        this.callParent();
    }
});