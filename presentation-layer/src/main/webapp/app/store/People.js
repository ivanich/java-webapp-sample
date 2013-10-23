Ext.define('MyApp.store.People', {
    extend:'Ext.data.Store',
    model:'MyApp.model.Person',
    autoLoad:true,

    proxy:{
        type:'rest',
        url : 'services/person',
        reader: {
            type: 'json'
//            root: 'person'
        },
        writer: {
            type: 'json'
        }
    }
});