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
                                    @click="change" color="primary" >
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn
                                    dark
                                    icon
                                    x-small
                                    @click="del" color="error">
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
                </v-card>
            </v-timeline-item>
        </v-timeline>
    </div>
</template>
<script>
    import {mapState} from 'vuex'

    export default {
        props: ['items'],
        computed: {...mapState([ 'auth'])},
        data(){
            return{
            }
        }
    }
</script>
<style>

</style>