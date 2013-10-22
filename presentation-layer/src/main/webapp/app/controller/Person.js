Ext.define("MyApp.controller.Person", {
    extend:'Ext.app.Controller',

    stores:['People'],
    models:['Person'],
    views:['person.List', 'person.Edit'],

    init:function ()
    {
        console.log("{MyApp.controller.People} Initialized People! This happens before the Application launch function is called");

        this.control({
            'personlist':{
                itemdblclick:this.editPerson
            },
            'personlist button[action=sync]':{
                click:this.syncPerson
            },
            'personedit button[action=save]':{
                click:this.updatePerson
            }
        });
    },

    editPerson:function (grid, record)
    {
        console.log('{MyApp.controller.People} Double clicked on ' + record.get('name'));

        var view = Ext.widget('personedit');
        view.down('form').loadRecord(record);

    },

    updatePerson:function (button)
    {
        console.log('{MyApp.controller.People} clicked the SAVE button');

        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();

        record.set(values);
        win.close();

    },

    syncPerson:function (button)
    {
        console.log('{MyApp.controller.Person} clicked the SYNC button');

        this.getPeopleStore().sync();
    }
});