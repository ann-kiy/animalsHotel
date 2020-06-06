<template>
    <v-row>
    <v-dialog v-model="dialog" scrollable >
        <template  v-slot:activator="{ on }">
            <v-card class="my-2" width="100%">
                <v-card-title>
                    <v-row>
                        <v-col cols="9">
                            <v-list-item
                                    :href=link
                                    ripple>
                                <v-list-item-avatar>
                                    <img v-if="advertisement.user.img" :src="'/img/'+advertisement.user.img">
                                    <img v-else :src=tempImg>
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-html="advertisement.user.name"></v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </v-col>
                        <v-col cols="3" class="text-right">
                            <v-btn v-if="advertisement.user.id!=auth.id && !isResp" v-on="on"  small color="primary" dark>Откликнуться</v-btn>
                            <v-btn v-if="advertisement.user.id!=auth.id && isResp" @click="delResp" small color="error" dark>Отменить</v-btn>
                        </v-col>
                    </v-row>
                </v-card-title>
<!--                <v-card-subtitle>C {{advertisement.dateStart}} до {{advertisement.dateEnd}}</v-card-subtitle>-->
<!--                <v-card-actions>{{advertisement.condition}}</v-card-actions>-->
                <v-card-text>
                    <v-row v-if="advertisement.user.id!=auth.id" class="my-3">
                        <v-icon class="mx-3">mdi-map-marker</v-icon>
                        {{advertisement.user.locale}}
                    </v-row>
                    <v-row>
                        <v-icon class="mx-3">mdi-calendar-range</v-icon>
                        Может принять с {{advertisement.dateStart}} до {{advertisement.dateEnd}}
                    </v-row>
                    <v-row md="0">
                        <v-col cols="6" >
                                <v-text-field
                                        :disabled="true"
                                        label="Тип"
                                        :value="type"
                                ></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            <v-text-field
                                    :disabled="true"
                                    label="Порода"
                                    :value="breed"
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row class="my-0">
                        <v-col cols="3" v-if="advertisement.sex">
                                <v-text-field
                                        :disabled="true"
                                        label="Пол"
                                        :value="advertisement.sex"
                                ></v-text-field>
                        </v-col>
                        <v-col cols="3" v-if="advertisement.age!=0">
                                <v-text-field
                                        :disabled="true"
                                        label="Возраст"
                                        :value="advertisement.age"
                                ></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            <v-text-field
                                    :disabled="true"
                                    label="Условие приема"
                                    :value="advertisement.condition"
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row v-if="advertisement.info" >
                        <v-textarea
                                outlined
                                :disabled="true"
                                rows="2"
                                label="Дополнительная информация"
                                :value="advertisement.info"
                        ></v-textarea>
                    </v-row>
                </v-card-text>


                <div class="text-right" v-if="advertisement.user.id===auth.id">
                    <v-btn @click="del" color="error" fab small dark>
                        <v-icon>mdi-trash-can-outline</v-icon>
                    </v-btn>
                    <v-btn @click="change" color="primary" fab small dark>
                        <v-icon>mdi-pencil</v-icon>
                    </v-btn>

                    <v-badge
                            v-if="countResp>0"
                            color="red"
                            :content="countResp"
                    >
                        <v-btn @click="dialog2 = !dialog2" class="mx-2" fab small dark color="teal">
                            <v-icon dark>mdi-format-list-bulleted-square</v-icon>
                        </v-btn>
                    </v-badge>
                </div>
            </v-card>
        </template>
        <v-card>
            <v-card-title>Выберите питомца, которого ходите пристроить</v-card-title>
            <v-divider></v-divider>
            <v-card-text style="height: 300px;">
                <v-autocomplete v-if="animalsAuthByUser.length!=0" v-model="dialogm1" :items="animalsAuthByUser" filled
                                color="blue-grey lighten-2"
                                label="Select"
                                name="name"
                                item-value="name"
                >
                    <template v-slot:selection="data">
                        <v-chip
                                v-bind="data.item"
                                :input-value="data.selected"
                                close
                        >
                            <v-avatar left>
                                <v-img v-if="data.item.img" :src="'/img/'+data.item.img"></v-img>
                                <v-img v-else :src="tempImg"></v-img>
                            </v-avatar>
                            {{ data.item.name }}
                        </v-chip>
                    </template>
                    <template v-slot:item="data">
                        <template >
                            <v-list-item-avatar>
                                <img v-if="data.item.img" :src="'/img/'+data.item.img">
                                <img v-else :src="tempImg">
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title v-text="data.item.name"></v-list-item-title>
<!--                                <v-list-item-subtitle v-html="data.item.group"></v-list-item-subtitle>-->
                            </v-list-item-content>
                        </template>
                    </template>

                </v-autocomplete>
                <div v-else>
                    Для отклика необходимо добавить питомца, для которого ищете место передержки
                    <router-link tag="v-btn" to="/animal" class="m-2" min-width="60">
                        <span>Добавить животное</span>
                        <v-icon>mdi-cat</v-icon>
                    </router-link>
                </div>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
                <v-btn color="blue darken-1" text @click="dialog = false">Отмена</v-btn>
                <v-btn color="blue darken-1" text @click="response">Откликнуться</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
    <v-dialog
            v-model="dialog2"
            max-width="500px"
            scrollable
    >
        <v-card>
            <v-card-title>
                Отклики
                <v-row >
                    <v-col class="raz">
                        <v-btn  @click="dialog2 = false" class="mx-2" fab dark x-small color="primary">
                            <v-icon dark>mdi-close</v-icon>
                        </v-btn>
                    </v-col>
                </v-row>
            </v-card-title>
            <v-card-text>
                <response-form :items="responses" :delResp="delRespByResp"></response-form>
            </v-card-text>
        </v-card>
    </v-dialog>
    </v-row>
</template>
<script>
    import {mapState,  mapGetters, mapActions} from 'vuex'
    import ResponseForm from 'components/responses/ResponseForm.vue'
    const axios = require('axios')

    export default {
        props: ['advertisement','deleteAdvert'],
        components:{
            ResponseForm
        },
        computed: {...mapState(['profile', 'auth']), ...mapGetters(['animalsAuthByUser'])},
        mounted() {
            axios
                .get('/response/'+this.advertisement.id)
                .then(response => (this.isResp = response.data));
            axios
                .get('/response/advert'+this.advertisement.id)
                .then(response => {this.responses = response.data; this.countResp=this.responses.length});

        },
        data() {
            return {
                tempImg:"/img/no_foto.png",
                selectItem:null,
                dialogm1: null,
                responses:[],
                countResp:0,
                isResp:false,
                dialog: false,
                dialog2: false,
                overlay: false,
                type: this.advertisement.typeAnimal ? this.advertisement.typeAnimal.type : "Любой",
                breed: this.advertisement.breedAnimal ? this.advertisement.breedAnimal.name : "Любая",
                link:'/user/'+this.advertisement.user.id
            }
        },
        methods:{
            response(){
                this.dialog = false
                let name=this.dialogm1
                axios
                    .post('/response',{
                        user:{id:this.auth.id},
                        advertisement:{id:this.advertisement.id},
                        animal:{id:this.animalsAuthByUser.filter(function (number) {
                            return number.name === name
                        })[0].id}
                    });
                this.isResp=true

            },
            delRespByResp(resp){
                axios
                    .delete('/response/by'+resp.id);
                const updateIndex=this.responses.findIndex(item=>item.id=resp.id)
                if(updateIndex>-1){
                    this.responses=[
                        ...this.responses.slice(0,updateIndex),
                        ...this.responses.slice(updateIndex+1)
                    ]
                }
            },
            delResp(){
                this.dialog = false
                axios
                    .delete('/response/'+this.advertisement.id);
                this.isResp=false
            },
            del(){
                this.deleteAdvert(this.advertisement)
            },
            ...mapActions(['setChangeAdvertAction']),
            change(){
                this.setChangeAdvertAction(this.advertisement)
                this.$router.replace('/advertisement')
            }
        }
    }
</script>
<style>
    .zx {
        with: 100vw
    }
</style>