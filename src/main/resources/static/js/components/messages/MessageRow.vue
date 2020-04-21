<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <v-card class="my-2">
        <v-card-title>{{message.author.name}}</v-card-title>
        <v-card-text>
            <v-avatar>
                <img v-bind:src="'/img/'+message.author.img">
            </v-avatar>
        <i>({{ message.id }})</i>
         {{ message.text }}

        </v-card-text>
        <v-card-actions v-if="message.author.id===auth.id" >
            <v-btn class="mr-2" @click="edit">Редактировать</v-btn>
            <v-icon @click="del">mdi-delete-forever</v-icon>
            {{message.createDate}}
        </v-card-actions>
    </v-card>

</template>
<script>
    import {mapActions} from 'vuex'
    export default {
         props:  ['message','editMessage'],
         data:function(){
             return{
             auth:auth
             }
         },
        methods:{
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
                 },
            del() {
                this.removeMessageAction(this.message)
                 }
             }
    }
</script>
<style>

</style>