Ext.define("MyApp.controller.Person", {
    extend:'Ext.app.Controller',

    stores:['People'],
    models:['Person'],
    views:['person.List', 'person.Edit', 'person.Add'],

    init:function ()
    {
        console.log("{MyApp.controller.People} Initialized People! This happens before the Application launch function is called");

        this.control({
            'personlist button[action=edit]':{
                click:this.editPerson
            },
            'personlist button[action=add]':{
                click:this.addPerson
            },
            'personlist button[action=delete]':{
            	click:this.deletePerson
            },
            'personedit button[action=update]':{
                click:this.updatePerson
            },
            'personadd button[action=save]':{
            	click:this.savePerson
            }
        });
    },

    editPerson:function (button)
    {
    	var selection = button.up('grid').getSelectionModel().getSelection();
    	
    	if(selection.length != 1){
    		Ext.Msg.alert("Select a Record", "Please select a record to edit.");
    		return;
    	}
    	
        console.log('{MyApp.controller.People} Clicked Edit Record Button');

        var view = Ext.widget('personedit');
        view.down('form').loadRecord(selection[0]);

    },
    
    addPerson:function (button)
    {
    	console.log('{MyApp.controller.People} Clicked Add Record Button');
    	
    	var view = Ext.widget('personadd');
    	view.down('form').loadRecord(Ext.create('MyApp.model.Person'));
    },

    updatePerson:function (button)
    {
        console.log('{MyApp.controller.People} clicked the UPDATE button');

        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();

        record.set(values);
        this.getPeopleStore().update(record);
        this.getPeopleStore().sync();
        this.getPeopleStore().load();
        
        win.close();
    },
    
    savePerson:function (button)
    {
    	console.log('{MyApp.controller.People} clicked the SAVE button');
    	
    	var win = button.up('window'),
    	form = win.down('form'),
    	record = form.getRecord(),
    	values = form.getValues();
    	
    	record.set(values);

    	this.getPeopleStore().add(record);
    	this.getPeopleStore().load();
    	win.close();
    	
    },
    
    deletePerson:function (button)
    {
        console.log('{MyApp.controller.People} clicked the DELETE button');

    	var selection = button.up('grid').getSelectionModel().getSelection();
    	
    	if(selection.length != 1){
    		Ext.Msg.alert("Select a Record", "Please select a record to delete.");
    		return;
    	}
    	
    	this.getPeopleStore().remove(selection[0]);
    	this.getPeopleStore().load();
    }
});