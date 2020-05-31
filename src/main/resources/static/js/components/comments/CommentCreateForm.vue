<template>
    <v-card class="px-3; my-3">
        <v-card-title>
            Написать отзыв
        </v-card-title>
        <v-card-text>
            <v-row>
                <v-col>
                    <v-label>Общая оценка
                        <v-rating
                                v-model="rating"
                                color="yellow accent-4"
                                dense
                                half-increments
                                size="30"
                                class="pb-2"
                        ></v-rating>
                    </v-label>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-textarea
                            solo
                            name="text"
                            v-model="text"
                            label="Текст отзыва"
                    ></v-textarea>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-btn @click="createComment">
                        Опубликовать
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>
<script>
    import {mapState} from 'vuex'

    const axios = require('axios')

    export default {
        computed: mapState(['auth', 'profile']),
        data(){
            return{
                text:null,
                rating:0
            }
        },
        methods:{
            createComment(){
                axios
                    .post('/comment', {text:this.text, user:{id:this.profile.id}, rating:this.rating})
            }
        }
    }

</script>
<style>

</style>