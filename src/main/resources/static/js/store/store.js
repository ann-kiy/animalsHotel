import Vue from 'vue'
import Vuex from 'vuex'
import messagesApi from 'api/messages.js'
import userApi from 'api/users.js'

Vue.use(Vuex);
export default  new Vuex.Store({
    state: {
        src: frontendData?"/img/"+auth.img:null,
        messages: frontendData?frontendData.messages:null,
        profile: frontendData?frontendData.profile:null,
        auth:auth,
        drawer: null,
        isActivated:isActivated
    },
    getters:{
        sortedMessages: state=>(state.messages || []).sort((a,b)=>-(a.id-b.id))
    },
    mutations: {
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
        }
    },
    actions:{
        async addUserAction(user,file){
            var data = new FormData;
            data.set('user', user);
            data.set('file', file);
            const result= await userApi.add(data)
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