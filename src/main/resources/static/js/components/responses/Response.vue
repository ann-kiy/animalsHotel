<template>
    <v-row>
        <v-col cols="6">
    <v-list-item
            @click="dialog2 = !dialog2"
            :key="item.id"
            ripple
    >
        <v-list-item-avatar>
            <img v-if="item.animal.img" :src="'/img/'+item.animal.img">
            <img v-else :src=tempImg>
        </v-list-item-avatar>
        <v-list-item-content>
            <v-list-item-title v-html="item.animal.name"></v-list-item-title>
            <v-list-item-subtitle v-html="item.createDate"></v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>
        </v-col>
        <v-col cols="6">
        <v-btn v-if="!item.stateRecipient" color="blue" @click="confirm" dark large>Подтвердить</v-btn>
        <v-btn v-else color="error"  @click="cancel" dark large>Отменить</v-btn>
        <v-btn @click="del" color="error" fab small dark>
            <v-icon>mdi-trash-can-outline</v-icon>
        </v-btn>
        </v-col>
    <v-dialog
            v-model="dialog2"
            persistent
            max-width="600px"
    >
        <v-card>
            <v-card-title>
                Отклики
            </v-card-title>
                <animal-view :animal="item.animal" :isEditing="false"></animal-view>
            <v-card-actions>
                <v-btn
                        color="primary"
                        text
                        @click="dialog2 = false"
                >
                    Выход
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
    </v-row>
</template>
<script>
    import AnimalView from 'components/animals/AnimalView.vue'

    const axios = require('axios')
    export default {
        props: ['item','delResp'],
        components:{
          AnimalView
        },
        data(){
            return{
                dialog2:false,
                tempImg:"/img/no_foto.png",
                model:null
            }
        },
        methods:{
            confirm(){
                axios
                    .post('/response/active/'+this.item.id);
                this.item.stateRecipient=true
            },
            cancel(){
                axios
                    .post('/response/cancel/'+this.item.id);
                this.item.stateRecipient=false
            },
            del(){
                this.delResp(this.item)
               // this.item=null
            }
        }
    }
</script>
<style>

</style>