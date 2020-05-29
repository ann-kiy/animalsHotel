<template>
    <v-row>
    <v-dialog v-model="dialog" scrollable >
        <template  v-slot:activator="{ on }">
            <v-card class="my-2" width="100%">
                <v-card-title>Тип:{{type}} Порода:{{breed}}
                    <v-btn v-if="advertisement.user.id!=auth.id && !isResp" v-on="on"  small color="primary" dark>Откликнуться</v-btn>
                    <v-btn v-if="advertisement.user.id!=auth.id && isResp" @click="delResp" small color="error" dark>Отменить</v-btn>
<!--                    <v-btn v-if="profile.id!=auth.id" :disabled="isResp" v-on="on" class="mx-2" fab small dark color="teal">-->
<!--                        <v-icon dark>mdi-plus-box-multiple</v-icon>-->
<!--                    </v-btn>-->
                </v-card-title>
                <v-card-subtitle>C {{advertisement.dateStart}} до {{advertisement.dateEnd}}</v-card-subtitle>
                <v-card-actions>{{advertisement.condition}}</v-card-actions>
                <v-card-text v-if="advertisement.age">{{advertisement.age}}</v-card-text>
                <v-card-text  v-if="advertisement.user.id!=auth.id">
                    <v-label> Хозяйн
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
                    </v-label>
                </v-card-text>
                <v-card-text v-if="advertisement.user.id!=auth.id">
                    {{advertisement.user.locale}}
                </v-card-text>
                <v-card-text v-if="advertisement.sex">{{advertisement.sex}}</v-card-text>
                <v-card-text v-if="advertisement.info">{{advertisement.info}}</v-card-text>
                <v-card-text>{{advertisement.createDate}}</v-card-text>
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
                <v-autocomplete v-model="dialogm1" :items="animalsAuthByUser" filled
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
                dialogm1: '',
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