Ext.define('MyApp.store.People', {
    extend:'Ext.data.Store',
    model:'MyApp.model.Person',
    autoLoad:true,

    proxy:{
        type:'ajax',
        api:{
            read:'services/person/list.json',
            update:'services/person/update.json'
        },
        reader:{
            type:'json'
        }
    }
});