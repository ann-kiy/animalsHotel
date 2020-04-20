<template xmlns:v-on="http:www.w3.org/1999/xhtml">
    <v-container>
        <v-row>
                <v-btn class="mr-2" href="/pet">Добавить питомца</v-btn>

                <v-btn  href="/profile">Change profile</v-btn>

        </v-row>
            <v-row>
                <v-text-field
                        placeholder="Write something" v-model="text"/>
                <v-btn type="button"v-on:click="save">Save </v-btn>
            </v-row>

    </v-container>
</template>
<script>
    import messagesApi from "api/messages";
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
             const auth={id:this.auth.id, name:this.auth.name, img:this.auth.img}
             const rec = {id:this.recipient, name:this.recipient.name, img:this.recipient.img}
             const message={id:this.id, author:auth, recipient:rec, text: this.text}

             if (this.id) {
                 messagesApi.update(message).then(result =>
                     result.json().then(data => {
                         const index = this.messages.findIndex(item => item.id === data.id)
                         this.messages.splice(index, 1, data)
                     })
                 )
             } else {
                 messagesApi.add(message).then(result =>
                     result.json().then(data => {
                         const index = this.messages.findIndex(item => item.id === data.id)
                         if (index > -1) {
                             this.messages.splice(index, 1, data)
                         } else {
                             this.messages.push(data)
                         }
                     })
                 )
             }
             this.text = ''
             this.id = ''
         }
     }
    }
</script>
<style>
    
</style>