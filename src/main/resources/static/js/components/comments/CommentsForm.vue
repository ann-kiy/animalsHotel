<template>
    <div>
        <v-timeline dense>
            <v-timeline-item
                    v-for="item in items"
                    :key="item.id"
            >
                <template v-slot:icon>
                    <v-avatar :href="'/user/'+item.author.id">
                        <img  :src="'/img/'+item.author.img">
                    </v-avatar>
                </template>
                <v-card class="elevation-2">
                    <v-card-title>
                        <div>{{item.author.name}}</div>
                        <div class="text-right; pl-3" v-if="item.author.id==auth.id">
                            <v-btn
                                    dark
                                    icon
                                    x-small
                                    @click="dialogChange=true; changeItem=item" color="primary" >
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn
                                    dark
                                    icon
                                    x-small
                                    @click="del(item)" color="error">
                                <v-icon>mdi-trash-can</v-icon>
                            </v-btn>
                        </div>

                    </v-card-title>
                    <v-card-text >
                        <div>
                            <v-rating
                                    v-model=item.rating
                                    color="yellow accent-4"
                                    dense
                                    half-increments
                                    readonly
                                    size="18"
                                    class="pb-2"
                            ></v-rating>
                            {{item.text}}
                        </div>

                    </v-card-text>
                    <v-card-subtitle>
                        {{item.createDate}}
                    </v-card-subtitle>
                </v-card>
            </v-timeline-item>
        </v-timeline>
        <v-dialog v-model="dialogChange" max-width="500px">
            <comment-create-form :comment=changeItem :close="closeDialogComment"></comment-create-form>
        </v-dialog>
    </div>
</template>
<script>
    import {mapState, mapActions} from 'vuex'
    import CommentCreateForm from 'components/comments/CommentCreateForm.vue'
    const axios = require('axios')

    export default {
        props: ['items'],
        computed: {...mapState([ 'auth'])},
        data(){
            return{
                dialogChange:false,
                changeItem:null,
            }
        },
        methods:{
            ...mapActions(['removeCommentAction']),
            del(comment){
                this.removeCommentAction(comment)
            },
            closeDialogComment() {
                this.dialogChange=false
            }
        },
        components:{
            CommentCreateForm
        }
    }
</script>
<style>

</style>