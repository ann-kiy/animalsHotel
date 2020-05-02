import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'pages/MessageList.vue'
import Registration from 'pages/Registration.vue'
import Login from 'pages/Login.vue'
import Main from 'pages/Main.vue'
import AddAnimal from 'pages/AddAnimal.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/registration', component: Registration },
    { path: '/login', component: Login },
    { path: '/animal', component: AddAnimal },
    { path: '/', component: Main },
    { path: '*', component: MessageList }
]

export  default new VueRouter({
    mode: 'history',
    routes
})