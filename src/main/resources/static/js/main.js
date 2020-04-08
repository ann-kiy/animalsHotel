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
            id: '',
            recipient: frontendData.profile.id
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
        '</div>'+
        '<div>'+
        '<a class="btn btn-primary" href="/profile" role="button">Change profile</a>'+
        '</div>'+
        '<div>' +
        '<input type="text" placeholder="Write something" v-model="text"/>'+
        '<button type="button" class="btn btn-info" v-on:click="save">Save </button>'+
        '</div>'+
        '</div>',
    methods:{
        save: function () {
            var rec = {id:this.recipient};
            var message={recipient:rec, text: this.text};
            if(this.id){
                messageApi.update({id:this.id}, {text:this.text}).then(result =>
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
        '<span v-if="message.author.id===${#authentication.getPrincipal().getImg()}" style="position: absolute; right: 0;">  ' +
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
    '        <div class="card mb-3" style="max-width: 1000px;">' +
        '            <div class="row no-gutters">' +
        '                <div class="col-md-5">' +
        '                    <img v-bind:src="src" class="card-img">' +
        '                </div>' +
        '                <div class="col-md-7">' +
        '                    <div class="card-body">' +
        '<div class="row p-2">' +
            '<svg class="bi bi-person-fill pr-1" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
            '  <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd"/>' +
            '</svg>'+
            '<h5  class="card-title">{{profile.name}}&nbsp</h5>' +
        '</div>'+
        '<div class="row p-2">' +
            '<svg class="bi bi-envelope-open-fill card-text pr-1" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
            '  <path fill-rule="evenodd" d="M8.941.435a2 2 0 00-1.882 0l-6 3.2A2 2 0 000 5.4v.125l8 4.889 8-4.889V5.4a2 2 0 00-1.059-1.765l-6-3.2zM16 6.697l-5.875 3.59L16 13.743V6.697zm-.168 8.108L9.246 10.93l-.089-.052-.896.548-.261.159-.26-.16-.897-.547-.09.052-6.585 3.874A2 2 0 002 16h12a2 2 0 001.832-1.195zM0 13.743l5.875-3.456L0 6.697v7.046z" clip-rule="evenodd"/>' +
            '</svg>'+
        '   <p class="card-text">{{profile.email}}</p>'+
        '</div>'+
        '<div class="row p-2" >'+
            '<svg class="bi bi-chat-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
            '  <path d="M8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6-.097 1.016-.417 2.13-.771 2.966-.079.186.074.394.273.362 2.256-.37 3.597-.938 4.18-1.234A9.06 9.06 0 008 15z"/>' +
            '</svg>'+
             '<p class="card-text">{{profile.phone}}</p>' +
        '</div>'+
            '<div class="row p-2" >'+
            '<svg class="bi bi-geo-alt" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
            '  <path fill-rule="evenodd" d="M8 16s6-5.686 6-10A6 6 0 002 6c0 4.314 6 10 6 10zm0-7a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd"/>' +
            '</svg>'+
            '<p class="card-text">{{profile.locale}}</p>' +
        '</div>'+
        '<div>'+
                '<a class="btn btn-primary" href="/profile" role="button">Change profile</a>'+
        '</div>'+
        '</div>'+
        '   <messages-list :messages="messages"/>' +
        '</div>'+
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>',
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