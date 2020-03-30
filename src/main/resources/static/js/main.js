function getIndex(list,id) {
    for(var i=0;i<list.length;i++){
        if(list[i].id == id){
            return i;
        }
    }
    return -1;
}
var messageApi = Vue.resource('/message{/id}');
Vue.component('message-form',{
    props:['messages', 'messageAttr'],
    data:function(){
        return {
            text: '',
            id: ''
        }
    },
    watch:{
        messageAttr: function(newVal, oldVal){
            this.text=newVal.text;
            this.id=newVal.id;
        }
    },
    template:
    '<div>'+
        '<div>'+
        '<a class="btn btn-primary" href="/pet" role="button">Добавить питомца</a>'+
        '</div'+
        '<div>'+
        '<a class="btn btn-primary" href="/profile" role="button">Change profile</a>'+
        '</div'+
        '<div>' +
            '<input type="text" placeholder="Write something" v-model="text"/>'+
            '<button type="button" class="btn btn-info" v-on:click="save">Save </button>'+
        '</div>'+
    '</div>',
    methods:{
        save: function () {
            var message = {text: this.text};
            if(this.id){
                messageApi.update({id:this.id}, message).then(result =>
                result.json().then(data =>{
                    var index=getIndex(this.messages,data.id);
                    this.messages.splice(index,1,data);
                    this.text=''
                    this.id=''
                }))
            }else {
                messageApi.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.text = ''
                    }));
            }
        }
    }
});
Vue.component('message-row',{
    props:  ['message','editMethod','messages'],
    template:
        '<div>' +
        '<i>({{message.id }})</i>{{message.text}}</i> '+'\t'+ '{{message.author.name}}' +
        '<span style="position: absolute; right: 0;">  ' +
            '<input type="button" value="Edit" @click="edit"/>'+
            '<input type="button" value="X" @click="del"/>'+
        '</span>'+
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        del: function () {
            messageApi.remove({id: this.message.id}).then(result=>{
                if(result.ok){
                    this.messages.splice(this.messages.indexOf(this.message),1)
                }
            })
        }
    }
});
Vue.component('messages-list', {
    props: ['messages'],
    data: function(){
        return{
            message: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<message-form :messages="messages" :messageAttr="message"/>'+
        '<message-row v-for="message in messages" :key=message.id :message="message" :editMethod="editMethod" :messages="messages"/>' +
        '</div>',

    methods:{
        editMethod: function (message) {
            this.message=message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<div >' +
        '<div  >'+
            '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>'+
        '<div>' +
            '<img v-bind:src="src">'+
        '</div>'+
        '   <messages-list :messages="messages"/>' +
        '</div>'+
        '</div>',
    data:        function () {
            if(frontendData)
            return {
                src: "/img/"+frontendData.profile.img,
                messages: frontendData.messages,
                profile: frontendData.profile
            }

        } ,
    created: function () {
        // messageApi.get().then(result =>
        //     result.json().then(data=>
        //         data.forEach(message=>this.messages.push(message))))
    },

});