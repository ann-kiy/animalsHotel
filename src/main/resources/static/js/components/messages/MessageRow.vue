<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <div>
        <i>({{ message.id }})</i>
        <img v-bind:src="'/img/'+message.author.img" style="max-height: 60px; max-width: 60px">
         {{ message.text }}
       {{message.author.name}}
        {{message.createDate}}
        <span v-if="message.author.id===auth" style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="X" @click="del" />
        </span>
    </div>

</template>
<script>
    export default {
         props:  ['message','editMessage','deleteMessage','messages'],
         data:function(){
             return{
             auth:auth
             }
         },
        methods:{
            edit() {
                this.editMessage(this.message)
                 },
            del() {
                this.deleteMessage(this.message)
                 }
             }
    }
</script>
<style>

</style>