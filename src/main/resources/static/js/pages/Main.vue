<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <div>
        <v-row>
            <v-col cols="4">
                <v-card>
                    <v-card-title class="text-center">
                        <v-avatar size="80" class="mr-3">
                            <img v-bind:src="'/img/'+profile.img">
                        </v-avatar>
                        {{profile.name}}
                        <v-btn v-if="profile.id==auth.id" @click="dialog1=true" icon class="text-right" color="pink" x-small>
                            <v-icon>mdi-lead-pencil</v-icon>
                        </v-btn>
                        <v-rating v-model="profile.rating"
                                  color="yellow accent-4"
                                  dense
                                  half-increments
                                  readonly
                                  class="pb-2"></v-rating>

                    </v-card-title>
                    <v-divider></v-divider>
                    <v-card-text>
                        <v-row>
                            <v-col cols="10">
                                <v-icon>mdi-phone-classic</v-icon>
                                {{profile.phone}}
                            </v-col>
                            <v-col cols="2">
                                <v-btn v-if="profile.id==auth.id" @click="dialog2=true" icon class="text-right" color="pink" x-small>
                                    <v-icon>mdi-lead-pencil</v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="10">
                                <v-icon>mdi-email</v-icon>
                                {{profile.email}}
                            </v-col>
                            <v-col cols="2">
                                <v-btn v-if="profile.id==auth.id" @click="dialog3=true" icon class="text-right" color="pink" x-small>
                                    <v-icon>mdi-lead-pencil</v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="10">
                                <v-icon>mdi-map-marker</v-icon>
                                {{profile.locale}}
                            </v-col>
                            <v-col cols="2">
                                        <v-btn v-if="profile.id==auth.id" @click="open" icon class="text-right" color="pink" x-small>
                                            <v-icon>mdi-lead-pencil</v-icon>
                                        </v-btn>
                                    <v-dialog v-model="dialog4" id="loc">
                                    <v-card>
                                        <v-card-title class="headline">Изменение адреса</v-card-title>
                                        <v-card-text>
                                            <v-row>
                                                <v-col cols="4" align-self="center">
                                                    <v-label>Новый адрес</v-label>
                                                </v-col>
                                                <v-col cols="8">
                                                    <v-text-field
                                                            id="locale"
                                                            v-model="locale"
                                                            prepend-icon="mdi-map-marker"
                                                            label="Адрес"
                                                    ></v-text-field>
                                                </v-col>
                                            </v-row>
                                        </v-card-text>
                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn color="#007bb6" text @click="change(4)">Изменить</v-btn>
                                            <v-btn color="#007bb6" text @click="dialog4=!dialog4">Отмена</v-btn>
                                        </v-card-actions>
                                    </v-card>


                                </v-dialog>
                            </v-col>
                            <v-dialog v-model="dialog1" persistent>
                                <v-card>
                                    <v-card-title class="headline">Изменение имени пользователя</v-card-title>
                                    <v-card-text>
                                        <v-row>
                                            <v-col cols="4" align-self="center">
                                                <v-label>Новое имя</v-label>
                                            </v-col>
                                            <v-col cols="8">
                                                <v-text-field v-model="name"></v-text-field>
                                            </v-col>
                                        </v-row>
                                        <v-row>
                                            <v-col cols="4" align-self="center">
                                                <v-label>Новое фото</v-label>
                                            </v-col>
                                            <v-col cols="8">
                                                <input tag="v-file-input" type="file" id="file" ref="file"
                                                       v-on:change="handleFileUpload()"/>
                                            </v-col>
                                        </v-row>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="#007bb6" text @click="change(1)">Изменить</v-btn>
                                        <v-btn color="#007bb6" text @click="dialog1 = false">Отмена</v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>

                            <v-dialog v-model="dialog2" persistent>
                                <v-card>
                                    <v-card-title class="headline">Изменение номера телефона</v-card-title>
                                    <v-card-text>
                                        <v-row>
                                            <v-col cols="4" align-self="center">
                                                <v-label>Новый номер</v-label>
                                            </v-col>
                                            <v-col cols="8">
                                                <v-text-field v-mask="'8(###)-###-##-##'" v-model="phone"></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="#007bb6" text @click="change(2)">Изменить</v-btn>
                                        <v-btn color="#007bb6" text @click="dialog2 = false">Отмена</v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                            <v-dialog v-model="dialog3" persistent>
                                <v-card>
                                    <v-card-title class="headline">Изменение электронного адреса</v-card-title>
                                    <v-card-text>
                                        <v-row>
                                            <v-col cols="4" align-self="center">
                                                <v-label>Новый адрес</v-label>
                                            </v-col>
                                            <v-col cols="8">
                                                <v-text-field type="email" v-model="email"></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="#007bb6" text @click="change(3)">Изменить</v-btn>
                                        <v-btn color="#007bb6" text @click="dialog3 = false">Отмена</v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>

                        </v-row>
                        <v-row>
                            <v-col class="text-center">
                                <v-btn v-if="profile.id!=auth.id" @click="dialogCreate=true">
                                    Оставить отзыв
                                </v-btn>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col class="text-center">
                                <router-link tag="v-btn" to="/message" v-if="profile.id==auth.id">
                                    Мои сообщения
                                </router-link>
                                <router-link tag="v-btn" to="/message" v-else>
                                   Написать
                                </router-link>
                            </v-col>
                        </v-row>
                    </v-card-text>
                </v-card>

            </v-col>


            <v-col fluid cols="8">
                <v-row>
                    <v-bottom-navigation
                            v-if="profile.id==auth.id"
                            grow
                            color="primary"
                            class="m-2"

                    >
                        <router-link tag="v-btn" to="/animal" class="m-2" min-width="60">
                            <span>Добавить животное</span>
                            <v-icon>mdi-cat</v-icon>
                        </router-link>
                        <router-link tag="v-btn" to="/advertisement" class="m-2">
                            <span>Добавить объявление</span>
                            <v-icon>mdi-view-grid-plus</v-icon>
                        </router-link>
                        <router-link tag="v-btn" to="/search" class="m-2">
                            <span>Найти объявление</span>
                            <v-icon>mdi-magnify</v-icon>
                        </router-link>
                    </v-bottom-navigation>
                </v-row>
                <v-row>
                    <v-tabs
                            fixed-tabs
                            background-color="indigo"
                            v-model="tabs"
                            dark
                    >
                        <v-tab href="#tabs-1">
                            Животные
                        </v-tab>
                        <v-tab href="#tabs-2">
                            Объявления
                        </v-tab>
                        <v-tab v-if="profile.id==auth.id" href="#tabs-3">
                            Отклики
                        </v-tab>
                        <v-tab href="#tabs-4">
                            Отзывы
                        </v-tab>
                    </v-tabs>
                </v-row>
                <v-tabs-items tag="v-row" v-model="tabs">
                    <v-tab-item
                            v-for="i in 4"
                            :key="i"
                            :value="'tabs-' + i"
                    >
                        <v-row fluid>
                            <v-col cols-12>
                                <animals-form v-if="i==1" :items=animalsByUser></animals-form>
                                <advertisement-form v-if="i==2" :items=advertsByUser></advertisement-form>
                                <response-form v-if="i==3":items=responsesByUser :delResp="delRespByResp"></response-form>
                                <comments-form v-if="i==4" :items=sortedComments ></comments-form>
                            </v-col>
                        </v-row>
                    </v-tab-item>
                </v-tabs-items>
            </v-col>
            <v-dialog v-model="dialogCreate" max-width="500px">
                <comment-create-form :close="closeDialogComment"></comment-create-form>
            </v-dialog>
        </v-row>
    </div>
</template>
<script>
    const settings = {
        apiKey: '01c977d4-510b-47ea-bdfc-2e5eb7e9235b',
        lang: 'ru_RU',
        coordorder: 'latlong',
        version: '2.1'
    }
    import {mapState, mapActions, mapGetters} from 'vuex'
    import AnimalsForm from 'components/animals/AnimalsForm.vue'
    import AdvertisementForm from 'components/ advertisements/AdvertisementForm.vue'

    const axios = require('axios')
    import {TheMask} from 'vue-the-mask'
    import {  loadYmap } from 'vue-yandex-maps'
    import ResponseForm from 'components/responses/MyResponsesForm.vue'
    import CommentsForm from 'components/comments/CommentsForm.vue'
    import CommentCreateForm from 'components/comments/CommentCreateForm.vue'


    function init() {
        var suggestView1 = new ymaps.SuggestView('locale');
    }
    export default {
        computed: {
            ...mapState(['profile', 'src', 'auth','comments']),
            ...mapGetters(['advertsByUser', 'responsesByUser','animalsByUser', 'sortedComments']),
            form() {
                return {
                    name: this.name ? this.name : this.auth.name,
                    email: this.email ? this.email : this.auth.email,
                    locale: this.locale ? this.locale : this.auth.locale,
                    phone: this.phone ? this.phone : this.auth.phone,
                }
            },
        },
        methods: {
            ...mapActions(['getAnimalsAction']),
            ...mapActions(['updateUserAction']),
            ...mapActions(['updateImgUserAction']),
            routeAnimal() {
                this.$router.replace('/animal')
            },
            goChange() {
                this.$router.replace('/registration')
            },
            change(i) {
                this.updateUserAction(this.form)
                if(this.file!=null){
                    this.updateImgUserAction(this.file)
                }
                if(i==1)
                    this.dialog1 = false
                else if(i==2)
                    this.dialog2 = false
                else if(i==3)
                    this.dialog3 = false
                else if(i==4)
                    this.dialog4 = false
            },
            async open() {
                this.dialog4=true
                await loadYmap({ ...settings, debug: true });
                ymaps.ready(init)
            },
            handleFileUpload() {
                this.file = this.$refs.file.files[0];
            },
            delRespByResp(resp){
                axios
                    .delete('/response/by'+resp.id);
            },
            closeDialogComment(){
                this.dialogCreate=false
            }
        },
        components: {
            TheMask,
            AnimalsForm,
            AdvertisementForm,
            ResponseForm,
            CommentsForm,
            CommentCreateForm
        },
        data() {
            return {
                tabs: null,
                dialogCreate:false,
                dialog1: false,
                dialog2: false,
                dialog3: false,
                dialog4: false,
                name: null,
                email: null,
                phone: null,
                locale: null,
                file: null
            }
        }
    }
</script>

<style>

</style>