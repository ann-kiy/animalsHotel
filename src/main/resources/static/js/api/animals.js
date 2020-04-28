import Vue from 'vue'

const animals=Vue.resource('/animal{/id}')
const animalsGet=Vue.resource('/animal/usr1')

export default {
    get:id=>animalsGet.get()
    // add:message=>messages.save({}, message),
    // update:message=>messages.update({id:message.id}, message),
    // remove:id=>messages.remove({id})
}