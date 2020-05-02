<template>
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
                                <h4>Регистрация питомца</h4>
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
                            <v-col cols="10">
                                <v-text-field
                                        ref="name"
                                        v-model="name"
                                        name="name"
                                        :rules="[rules.required]"
                                        label="Кличка питомца"
                                        required

                                ></v-text-field>
                            </v-col>
                            <v-col cols="2">
                                <v-select
                                        v-model="selectSex"
                                        :items="itemsText"
                                        name="sex"
                                        :rules="[rules.required]"
                                        label="Пол"
                                        required
                                ></v-select>
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
                            <v-col cols="6">
                                <v-label> Фото питомца
                                    <input tag="v-file-input" type="file" id="file" ref="file"
                                           v-on:change="handleFileUpload()"/>
                                </v-label>
                            </v-col>
                            <v-col
                                    cols="6"
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
                        <v-btn block disable @click="sent" text color="#0f4bac"> Зарегистрировать
                        </v-btn>
                    </v-row>
                </v-card-actions>


            </v-card>
        </form>
    </v-container>
</template>
<script>
    import {mapActions} from 'vuex'
    import {TheMask} from 'vue-the-mask'

    const axios = require('axios')

    export default {
        mounted() {
            axios
                .get('/model')
                .then(response => (this.typeAnimals = response.data));
        },
        components: {TheMask},
        data() {

            return {
                typeAnimals: [],
                breedAnimals: [],
                itemAge: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15],
                selectType: '',
                errorMessages: '',
                info: '',
                name: '',
                file: "",
                selectAge: '',
                selectSex: '',
                selectBreed: '',
                rules: {
                    required: value => !!value || 'Не заполнено',
                    min: v => v.length >= 8 || 'Минимум 8 символов',
                    eqvelsPassword: () => this.password == this.password2 || "Пароли не совпадают"
                },
                itemsText: [
                    'м',
                    'ж'
                ],
                itemsValue: [
                    0,
                    1
                ],
                z: null
            }
        },
        computed: {
            form() {
                return {
                    name: this.name,
                    sex: this.sex,
                    typeAnimal: this.selectType,
                    breedAnimal: this.selectBreed,
                    age: this.selectAge,
                    info: this.info,
                    file: ''
                }
            }
        },
        methods: {
            ...mapActions(['addUserAction']),
            checkForm: function () {
                if (this.file !== "" && this.name !== "" && this.selectType !== "" && this.selectSex !== "" && this.selectAge !== "") {
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
            sent() {
                if (this.checkForm()) {
                    const form = new FormData()
                    form.append('file', this.file)
                    form.append('name', this.name)
                    form.append('sexx', this.selectSex)
                    form.append('type', this.selectType)
                    form.append('breed', this.selectBreed)
                    form.append('age', this.selectAge)
                    form.append('info', this.info)
                    axios
                        .post('/animal', form, {
                            'Content-Type': 'multipart/form-data'
                        })
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