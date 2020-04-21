<template>
    <v-container class="grey lighten-5">
             <message-form  :messageAttr="message"/>
             <message-row v-for="message in sortedMessages"
                          :key=message.id
                          :message="message"
                          :editMessage="editMessage"/>
    </v-container>
</template>
<script>
    import {mapGetters} from 'vuex'
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'

    export default{
        data: function(){
            return{
                message: null
            }
        },
        computed:mapGetters(['sortedMessages']),
        components:{
            MessageRow,
            MessageForm
        },
        methods:{
            editMessage(message) {
                this.message=message;
            }
        }
    }

</script>
<style>

</style>