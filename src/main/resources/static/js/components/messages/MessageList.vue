<template>
    <v-container class="grey lighten-5">
             <message-form :messages="messages" :messageAttr="message"/>
             <message-row v-for="message in messages"
                          :key=message.id
                          :message="message"
                          :editMessage="editMessage"
                          :deleteMessage="deleteMessage"
                          :messages="messages"/>
    </v-container>
</template>
<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import messagesApi from "api/messages";
    export default{
        props: ['messages'],
        data: function(){
            return{
                message: null
            }
        },
        components:{
            MessageRow,
            MessageForm
        },
        methods:{
            editMessage(message) {
                this.message=message;
            },
            deleteMessage(message){
                messagesApi.remove(message.id).then(result=>{
                if(result.ok){
                    this.messages.splice(messages.findIndex(item=>item.id===message.id),1)
                }
            })
            }
        }
    }

</script>
<style>

</style>