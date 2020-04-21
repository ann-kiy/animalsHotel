import Vue from 'vue'

const users=Vue.resource('/user{/id}')

export default {
    add:message=>messages.save({}, message),
    // update:message=>messages.update({id:message.id}, message),
    // remove:id=>messages.remove({id})
}