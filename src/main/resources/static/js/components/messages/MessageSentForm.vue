<template>
    <v-card class="px-3; my-3">
        <v-card-title>
            Написать отзыв
            <v-list-item
                    :href=link
                    ripple>
                <v-list-item-avatar>
                    <img v-if="recipient.img" :src="'/img/'+recipient.img">
                    <img v-else :src=tempImg>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title v-html="recipient.name"></v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-card-title>
        <v-card-text>
            <v-row>
                <v-col>
                    <v-textarea
                            solo
                            name="text"
                            v-model="text"
                            label="Сообщение"
                    ></v-textarea>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-btn @click="send">
                        Опубликовать
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>
<script>
    import {mapState, mapActions} from 'vuex'

    const axios = require('axios')

    export default {
        props:['close', 'recipient'],
        computed: mapState(['auth', 'profile']),
        data(){
            return{
                text:null,
                link:'/user/'+this.advertisement.user.id,
                id:this.recipient?this.recipient.id:'',
                tempImg:"/img/no_foto.png",
            }
        },
        methods:{
            ...mapActions(['addMessageAction']),
            send() {
                const auth={id:this.auth.id, name:this.auth.name, img:this.auth.img}
                const rec = {id:this.recipient, name:this.recipient.name, img:this.recipient.img}
                const message={id:this.id, author:auth, recipient:rec, text: this.text}

                if (this.id) {
                    this.updateMessageAction(message)
                } else {
                    this.addMessageAction(message)
                }
                this.close()
            }
        }
    }

</script>
<style>

</style>