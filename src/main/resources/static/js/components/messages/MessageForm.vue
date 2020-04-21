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
    import {mapActions} from 'vuex'
    export default {
        props:[ 'messageAttr'],
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
            ...mapActions(['addMessageAction', 'updateMessageAction']),
         save() {
             const auth={id:this.auth.id, name:this.auth.name, img:this.auth.img}
             const rec = {id:this.recipient, name:this.recipient.name, img:this.recipient.img}
             const message={id:this.id, author:auth, recipient:rec, text: this.text}

             if (this.id) {
                 this.updateMessageAction(message)
             } else {
                 this.addMessageAction(message)
             }
             this.text = ''
             this.id = ''
         }
     }
    }
</script>
<style>
    
</style>