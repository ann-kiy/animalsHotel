import Vue from 'vue'
import '@babel/polyfill'
import 'api/resourse'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store.js'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import {connect} from "./util/ws"
import vuetify from 'plagins/vuetify.js'
import Vuetify from "vuetify";


if(frontendData){
    connect()
}
new Vue({
    vuetify,
    el:'#app',
    store,
    router,
    render: a => a(App)
})

