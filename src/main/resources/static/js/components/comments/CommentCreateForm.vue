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
                    <v-btn v-if="comment" @click="createComment">
                        Изменить
                    </v-btn>
                    <v-btn v-else @click="createComment">
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
        props:['close', 'comment'],
        computed: mapState(['auth', 'profile']),
        data(){
            return{
                text:this.comment?this.comment.text:null,
                rating:this.comment?this.comment.rating:0,
                id:this.comment?this.comment.id:''
            }
        },
        methods:{
            ...mapActions(['addCommentAction']),
            ...mapActions(['updateCommentAction']),
            createComment(){
                let comment={id:this.id,text:this.text, user:{id:this.profile.id, name:this.profile.name}, author:{id:this.auth.id, name:this.auth.name, img:this.auth.img},rating:this.rating}
                if(comment){
                    this.updateCommentAction(comment)
                }else {
                    this.addCommentAction(comment)
                }
                this.close()
            }
        }
    }

</script>
<style>

</style>