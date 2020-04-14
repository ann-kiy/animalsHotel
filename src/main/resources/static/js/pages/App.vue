<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <div>
        <div>
            <img v-bind:src="src">
        </div>
        <div>
            <h5>{{profile.name}}&nbsp</h5>
        </div>
        <div>
            <p>{{profile.email}}</p>
        </div>
        <div>
            <p>{{profile.phone}}</p>
        </div>
        <div>
            <p>{{profile.locale}}</p>
        </div>

        <div>
            <messages-list :messages="messages"/>'
        </div>
    </div>
</template>
<script>
    import MessagesList from 'components/messages/MessageList.vue'
    import {addHandler} from "util/ws"
    import {getIndex} from "util/collections"

    export default {
        components: {
            MessagesList
        },
        data(){
            if(frontendData)
            return {
            src: "/img/"+frontendData.profile.img,
            messages: frontendData.messages,
            profile: frontendData.profile
        }
        },
        created(){
            addHandler(data=>{
                let index=getIndex(this.messages,data.id)
                if(index>-1){
                    this.messages.splice(index,1,data)
                }else{
                    this.messages.push(data)
                }
            })
        }
    }

</script>
<style>
</style>