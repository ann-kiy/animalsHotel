const settings = {
    apiKey: '01c977d4-510b-47ea-bdfc-2e5eb7e9235b',
    lang: 'ru_RU',
    coordorder: 'latlong',
    version: '2.1'
}

import Vue from 'vue'
import '@babel/polyfill'
import 'api/resourse'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store.js'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import {connect} from "./util/ws"
import vuetify from 'plagins/vuetify.js'
import Vuetify from 'vuetify'
import VueMask from 'v-mask'
Vue.use(VueMask);

import YmapPlugin from 'vue-yandex-maps'
Vue.use(YmapPlugin, settings)


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

