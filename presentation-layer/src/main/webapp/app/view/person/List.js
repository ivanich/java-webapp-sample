Ext.define('MyApp.view.person.List', {
    extend:'Ext.grid.Panel',
    alias:'widget.personlist',
    title:'All People',
    store:'People',

    initComponent:function ()
    {
        console.log("{MyApp.view.person.List} init person.List view");

        this.columns = [
            {header:'Name', dataIndex:'name', flex:1},
            {header:'Email', dataIndex:'email', flex:1}
        ];

        this.buttons = [
            {
                text:'Sync',
                action:'sync'
            }
        ];

        this.callParent(arguments);
    }
});