import Vue from 'vue'

const users=Vue.resource('/registration{/id}')

export default {
    add:data=>users.save({}, data)
    // update:message=>messages.update({id:message.id}, message),
    // remove:id=>messages.remove({id})
}