<template>
    <v-row>
        <v-col cols="7">
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
        </v-col >
        <v-col cols="5"  align-self="center" class="raz">
        <v-btn v-if="!item.stateRecipient" color="blue" @click="confirm" dark small>Подтвердить</v-btn>
        <v-btn v-else color="error"  @click="cancel" dark small>Отменить</v-btn>
        <v-btn @click="del" color="error" fab small dark>
            <v-icon>mdi-trash-can-outline</v-icon>
        </v-btn>
        </v-col>
    <v-dialog
            v-model="dialog2"
            persistent
            max-width="600px"
    >
        <animal-view :animal="item.animal" :isEditing="false" :close="closeD"></animal-view>
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
            },
            closeD(){
                this.dialog2 = false
            }
        }
    }
</script>
<style>
    .raz {
        text-align: right;
    }
</style>