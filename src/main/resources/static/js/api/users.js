import Vue from 'vue'

const users=Vue.resource('/registration{/id}')

export default {
    add:data=>users.save({}, data),
    update:data=>Vue.resource('/profile').save({},data),
    // remove:id=>messages.remove({id})
}