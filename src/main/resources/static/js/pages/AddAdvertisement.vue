<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container>
        <form id="app"
              action="/animal"
              method="post"
              enctype="multipart/form-data">
            <v-card class="mx-auto my-12 outer-card" max-width="1000">
                <v-card-title>
                    <v-card class="v-header py-2 mx-auto my-n12" max-width="700" raised>
                        <v-card-title>
                            <v-row justify="center" color="white">
                                <h4>Взять животное</h4>
                            </v-row>
                        </v-card-title>
                    </v-card>
                </v-card-title>
                <br/>
                <v-card-text class="my-4">
                    <v-container>
                        <v-alert v-if="errorMessages" type="error">
                            {{errorMessages}}
                        </v-alert>
                        <v-row
                                cols="12"
                                md="4"
                        >
                            <v-col cols="12">
                                <v-label> Введите временной промежуток возможного приема питомцев
                                    <v-menu
                                            ref="startMenu"
                                            v-model="startMenu"
                                            :close-on-content-click="false"
                                            :nudge-right="40"
                                            :return-value.sync="start"
                                            transition="scale-transition"
                                            min-width="290px"
                                            offset-y
                                    >
                                        <template v-slot:activator="{ on }">
                                            <v-text-field
                                                    v-model="start"
                                                    class="mt-3"
                                                    label="Начало"
                                                    prepend-icon="mdi-calendar"
                                                    dense
                                                    readonly
                                                    outlined
                                                    hide-details
                                                    v-on="on"
                                            ></v-text-field>
                                        </template>
                                        <v-date-picker
                                                v-model="start"
                                                no-title
                                                scrollable
                                        >
                                            <v-spacer></v-spacer>
                                            <v-btn
                                                    text
                                                    color="primary"
                                                    @click="startMenu = false"
                                            >
                                                Cancel
                                            </v-btn>
                                            <v-btn
                                                    text
                                                    color="primary"
                                                    @click="$refs.startMenu.save(start)"
                                            >
                                                OK
                                            </v-btn>
                                        </v-date-picker>
                                    </v-menu>
                                    <v-menu
                                            ref="endMenu"
                                            v-model="endMenu"
                                            :close-on-content-click="false"
                                            :nudge-right="40"
                                            :return-value.sync="end"
                                            transition="scale-transition"
                                            min-width="290px"
                                            offset-y
                                    >
                                        <template v-slot:activator="{ on }">
                                            <v-text-field
                                                    v-model="end"
                                                    class="mt-3"
                                                    label="Конец"
                                                    prepend-icon="mdi-calendar"
                                                    dense
                                                    readonly
                                                    outlined
                                                    hide-details
                                                    v-on="on"
                                            ></v-text-field>
                                        </template>
                                        <v-date-picker
                                                v-model="end"
                                                no-title
                                                scrollable
                                        >
                                            <v-spacer></v-spacer>
                                            <v-btn
                                                    text
                                                    color="primary"
                                                    @click="endMenu = false"
                                            >
                                                Cancel
                                            </v-btn>
                                            <v-btn
                                                    text
                                                    color="primary"
                                                    @click="$refs.endMenu.save(end)"
                                            >
                                                OK
                                            </v-btn>
                                        </v-date-picker>
                                    </v-menu>
                                </v-label>
                            </v-col>

                        </v-row>

                        <v-row
                                cols="12"
                                md="4"
                        >
                            <v-col cols="6">
                                <v-select
                                        v-model="selectType"
                                        :items="typeAnimals"
                                        item-text="type"
                                        name="typeAnimal"
                                        @click="clear"
                                        :rules="[rules.required]"
                                        label="Тип питомца"
                                        required
                                ></v-select>
                            </v-col>
                            <v-col cols="6">
                                <v-select
                                        v-model="selectBreed"
                                        :items="breedAnimals"
                                        item-text="name"
                                        name="breedAnimal"
                                        @click="getBreed"
                                        :rules="[rules.required]"
                                        label="Порода питомца"
                                        required
                                ></v-select>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3">
                                <v-select
                                        v-model="selectSex"
                                        :items="itemsText"
                                        name="sex"
                                        :rules="[rules.required]"
                                        label="Пол"
                                        required
                                ></v-select>
                            </v-col>
                            <v-col
                                    cols="3"
                            >
                                <v-select
                                        v-model="selectAge"
                                        :items=this.itemAge
                                        name="age"
                                        :rules="[rules.required]"
                                        label="Возраст"
                                        required
                                ></v-select>
                            </v-col>
                            <v-col cols="6">
                                <v-select
                                        v-model="selectCondition"
                                        :items=this.itemCondition
                                        name="condition"
                                        :rules="[rules.required]"
                                        label="Условие приема"
                                        required
                                ></v-select>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-textarea
                                    solo
                                    name="info"
                                    v-model="info"
                                    label="Дополнительная информация"
                            ></v-textarea>
                        </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-row justify="center">
                        <v-btn v-if="!changeAdvert" block disable @click="sent" text color="#0f4bac"> Подать</v-btn>
                        <v-btn v-else block disable @click="sent" text color="#0f4bac"> Изменить</v-btn>
                    </v-row>
                </v-card-actions>


            </v-card>
        </form>
    </v-container>
</template>
<script>
    import {TheMask} from 'vue-the-mask'
    import {mapState, mapActions} from 'vuex'

    const axios = require('axios')

    export default {
        computed: mapState(['itemAge', 'itemsText', 'itemCondition','changeAdvert']),
        mounted() {
            axios
                .get('/model')
                .then(response => (this.typeAnimals = response.data));
            if(this.changeAdvert!=null) {
                if(this.changeAdvert.typeAnimal!=null) {
                    axios
                        .get('/model/' + this.changeAdvert.typeAnimal.type)
                        .then(response => (this.breedAnimals = response.data));
                }
                this.info = this.changeAdvert.info?this.changeAdvert.info:""
                this.start= this.changeAdvert.dateStart
                this.end = this.changeAdvert.dateEnd
                this.selectType=this.changeAdvert.typeAnimal!=null?this.changeAdvert.typeAnimal:""
                this.selectBreed=this.changeAdvert.breedAnimal!=null?this.changeAdvert.breedAnimal:""
                this.selectAge=this.changeAdvert.age?this.changeAdvert.age:""
                this.selectCondition=this.changeAdvert.condition
                this.selectSex=this.changeAdvert.sex?this.changeAdvert.sex:null
            }
        },
        data() {

            return {
                typeAnimals: [],
                breedAnimals: [],
                selectType: '',
                errorMessages: '',
                info: "",
                start: '',
                endMenu: null,
                startMenu: null,
                selectCondition:'',
                end: '',
                name: '',
                file: "",
                selectAge: null,
                selectSex: null,
                selectBreed: '',
                rules: {
                    required: value => !!value || 'Не заполнено',
                },

            }
        },
        methods: {
            ...mapActions(['addUserAction']),
            checkForm: function () {
                if (this.start !== "" && this.end !== "" && this.selectCondition!=="") {
                    this.errorMessages = ""
                    return true
                } else {
                    this.errorMessages = "Не все поля заполнены"
                    return false;
                }

            },
            getBreed() {
                axios
                    .get('/model/' + this.selectType)
                    .then(response => (this.breedAnimals = response.data));
            },
            clear() {
                this.breedAnimals = []
            },
            handleFileUpload() {
                this.file = this.$refs.file.files[0];
            },
            ...mapActions(['resetChangeAdvertAction']),
            sent() {
                if (this.checkForm()) {
                    var type = this.selectType
                    var breed = this.selectBreed
                    let adv={
                            'dateStart': new Date(this.start).toISOString().split("T")[0],
                            'dateEnd': new Date(this.end).toISOString().split("T")[0],
                            'sex': this.selectSex,
                            'age': this.selectAge,
                            'info': this.info,
                            'condition':this.selectCondition,
                            'typeAnimal': this.typeAnimals.filter(function (number) {
                                return number.type === type
                            })[0],
                            'breedAnimal': this.breedAnimals.filter(function (number) {
                                return number.name === breed
                            })[0]
                        }
                        if(this.changeAdvert==null) {
                            axios
                                .post('/advertisement', adv, {
                                    'Content-Type': 'multipart/form-data'
                                })
                        }else{
                            axios
                                .put('/advertisement/'+this.changeAdvert.id, adv, {
                                    'Content-Type': 'multipart/form-data'
                                })
                        }
                    this.resetChangeAdvertAction()
                    this.$router.replace('/')
                }
            }

        }
    }
</script>
<style>
    .v-header {
        background-image: linear-gradient(60deg, #007bb6, #0f4bac);
        text-align: center;
        width: 100%;
        margin-top: -40px;
    }

    h4 {
        font-family: "Times New Roman", Times, serif;
        color: white;
    }

    .outer-card {
        border-radius: 8px !important;
    }

    .v-input__icon--prepend .v-icon {
        color: black;
    }
</style>