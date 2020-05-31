import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'pages/MessageList.vue'
import Registration from 'pages/Registration.vue'
import Login from 'pages/Login.vue'
import Main from 'pages/Main.vue'
import AddAnimal from 'pages/AddAnimal.vue'
import AddAdvert from 'pages/AddAdvertisement.vue'
import SearchForm from 'components/search/SearchForm.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/registration', component: Registration },
    { path: '/message', component: MessageList},
    { path: '/login', component: Login },
    { path: '/advertisement', component: AddAdvert },
    { path: '/animal', component: AddAnimal },
    { path: '/search', component: SearchForm },
    { path: '/', component: Main },
    { path: '*', component: Main }
]

export  default new VueRouter({
    mode: 'history',
    routes
})