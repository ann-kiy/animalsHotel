<template xmlns:v-on="http:www.w3.org/1999/xhtml">
             <div>
             <div>
                 <a class="btn btn-primary" href="/pet" role="button">Добавить питомца</a>
                 </div>
             <div>
                 <a class="btn btn-primary" href="/profile" role="button">Change profile</a>
                 </div>
             <div> 
                 <input type="text" placeholder="Write something" v-model="text"/>
                 <button type="button" class="btn btn-info" v-on:click="save">Save </button>
                 </div>
             </div>
</template>
<script>
    import {sendMessage} from "util/ws";
    export default {
        props:['messages', 'messageAttr'],
         data:function(){
            return {
                text: '',
                id: '',
                recipient: frontendData.profile.id,
                auth: auth
            }
        },
        watch:{
            messageAttr: function(newVal, oldVal){
                this.text=newVal.text
                this.id=newVal.id
            }
        },
        methods:{
         save() {
             const auth={id:this.auth}
             const rec = {id:this.recipient}
             const message={id:this.id, author:auth, recipient:rec,text: this.text}
             sendMessage(message)
             this.text=''
             this.id=''
             // const rec = {id:this.recipient}
             // const message={recipient:rec, text: this.text}
             // if(this.id){
             //     messageApi.update({id:this.id}, {text:this.text}).then(result =>
             //         result.json().then(data =>{
             //             const index=getIndex(this.messages,data.id)
             //             this.messages.splice(index,1,data)
             //             this.text=''
             //             this.id=''
             //         }))
             // }else {
             //     this.$resource('/message{/id}').save({}, message).then(result =>
             //         result.json().then(data => {
             //             this.messages.push(data)
             //             this.text = ''
             //         }))
             // }
         }
     }
    }
</script>
<style>
    
</style>