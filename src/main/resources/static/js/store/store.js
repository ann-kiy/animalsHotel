import Vue from 'vue'
import Vuex from 'vuex'
import messagesApi from 'api/messages.js'
import userApi from 'api/users.js'
import animalApi from 'api/animals.js'

Vue.use(Vuex);
const axios = require('axios')
export default  new Vuex.Store({
    state: {
        src: frontendData?"/img/"+auth.img:null,
        messages: frontendData?frontendData.messages:null,
        profile: frontendData?frontendData.profile:null,
        comments:frontendData?frontendData.comments:null,
        type:null,
        breed:null,
        auth:auth,
        drawer: null,
        response:null,
        isActivated:isActivated,
        anim:[],
        anim1:[],
        advert:[],
        changeAdvert:null,
        changeAnimal:null,
        itemsText: [
            'м',
            'ж'
        ],
        itemCondition:[
            "Бузвозмездно",
            "За вознаграждение",
            "За деньги"
        ],
        itemAge: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15],

    },
    getters:{
        sortedMessages: state=>(state.messages || []).sort((a,b)=>-(a.id-b.id)),
        animalsAuthByUser:state=>{
            axios
                .get('/animal/usr'+state.auth.id)
                .then(response => (state.anim1=response.data))
             return state.anim1

        },
        advertsByUser:state=>{
            axios
                .get('/advertisement/usr'+state.profile.id)
                .then(response => (state.advert = response.data));
            return state.advert
        },
        responsesByUser:state=>{
            axios
                .get('/response/user')
                .then(response => (state.response = response.data));
            return state.response
        },
        animalsByUser:state=>{
            axios
                .get('/animal/usr'+state.profile.id)
                .then(response => (state.anim=response.data))
            return state.anim

        },
        typeAnimal:state => {
            axios
                .get('/model')
                .then(response => (state.type = response.data))
            return state.type
        },
        // breedAnimal:state => {
        //     axios
        //         .get('/model/breed')
        //         .then(response => (state.breed = response.data))
        //     return state.breed
        // }
    },
    mutations: {
        setChangeAnimalMutation(state,animal){
            state.changeAnimal=animal
        },
        resetChangeAnimalMutation(state){
            state.changeAnimal=null
        },
        setChangeAdvertMutation(state,advert){
            state.changeAdvert=advert
        },
        resetChangeAdvertMutation(state){
            state.changeAdvert=null
        },
       addMessageMutation(state, message){
            state.messages=[
                ...state.messages,
                message
            ]
       },
        updateMessageMutation(state, message){
            const updateIndex=state.messages.findIndex(item=>item.id=message.id)
            state.messages=[
                ...state.messages.slice(0,updateIndex),
                message,
                ...state.messages.slice(updateIndex+1)
            ]

        },
        removeMessageMutation(state, message){
            const updateIndex=state.messages.findIndex(item=>item.id=message.id)
            if(updateIndex>-1){
                state.messages=[
                    ...state.messages.slice(0,updateIndex),
                    ...state.messages.slice(updateIndex+1)
                ]
            }
        },
        changeProfileMutation(state, user){
            state.profile.phone=user.phone
            state.profile.name=user.name
            state.profile.locale=user.locale
            state.profile.email=user.email

            state.auth.phone=user.phone
            state.auth.name=user.name
            state.auth.locale=user.locale
            state.auth.email=user.email

        }
    },
    actions:{
        async resetChangeAnimalAction({commit}){
            commit('resetChangeAnimalMutation')
        },
        async setChangeAnimalAction({commit}, animal){
            commit('setChangeAnimalMutation', animal);
        },
        async resetChangeAdvertAction({commit}){
          commit('resetChangeAdvertMutation')
        },
        async setChangeAdvertAction({commit}, advert){
          commit('setChangeAdvertMutation', advert);
        },
        async addUserAction(user,file){
            var data = new FormData;
            data.set('user', user);
            data.set('file', file);
            const result= await userApi.add(data)
        },
        async updateUserAction({state, commit},user){
            var data = new FormData;
            data.set('user', user);
            // const result= await userApi.add(data)
            axios
                .put('/profile',user)
                // .then(response => {
                     commit('changeProfileMutation',user)

        },
        async updateImgUserAction({state, commit},file){
            var data = new FormData;
            data.set('file', file);
            // const result= await userApi.add(data)
            axios
                .post('/profile',data)
                // .then(response=>(state.profile=response.data))
            // .then(response => {
            // commit('changeProfileMutation',user)

        },
        async getAnimalsAction({commit},id){
            const result= await animalApi.get(id).then(response => {
                this.animalsByUser = response.data;
            })
            commit('getAnimalsByUserMutation', result)
        },
        async addMessageAction({commit,state}, message){
            const result= await messagesApi.add(message)
            const data=await result.json()
            const index = state.messages.findIndex(item => item.id === data.id)
            if (index > -1) {
                commit('updateMessageMutation', data)
            } else {
                commit('addMessageMutation', data)
            }
        },
        async updateMessageAction({commit}, message){
            const result= await messagesApi.update(message)
            const data=await result.json()
            commit('updateMessageMutation', data)

        },
        async removeMessageAction({commit}, message){
            const result= await messagesApi.remove(message.id)
                if(result.ok){
                    commit('updateMessageMutation', message)
                }

        }

    }
})