<template>
        <v-app>
            <v-content>
                    <v-container>
                        <form id="app"
                              @submit="checkForm"
                              action="/registration"
                              method="post"
                              enctype="multipart/form-data">
                        <v-card class="mx-auto my-12 outer-card" max-width="1000">
                            <v-card-title>
                                <v-card class="v-header py-2 mx-auto my-n12" max-width="700" raised>
                                    <v-card-title>
                                        <v-row justify="center" color="white">
                                            <h4>Регистрация</h4>
                                        </v-row>
                                    </v-card-title>
                                </v-card>
                            </v-card-title><br />
                            <v-card-text class="my-4">
                                <v-container>
                                        <v-row
                                                cols="12"
                                                md="4"
                                        >
                                            <v-text-field
                                                    ref="name"
                                                    v-model="name"
                                                    name="name"
                                                    :rules="[rules.required]"
                                                    prepend-icon="mdi-account"
                                                    label="ФИО"
                                                    :error-messages="errors[0]"
                                                    required

                                            ></v-text-field>
                                        </v-row>

                                        <v-row
                                                cols="12"
                                                md="4"
                                        >
                                            <v-text-field
                                                    v-model="email"
                                                    type="email"
                                                    name="email"
                                                    :rules="[rules.required]"
                                                    prepend-icon="mdi-email"
                                                    label="Email"
                                                    :error-messages="errors[1]"
                                                    required
                                            ></v-text-field>
                                        </v-row>

                                        <v-row
                                                cols="12"
                                                md="4"
                                        >
                                            <v-text-field
                                                    name="phone"
                                                    v-model="phone"
                                                    v-mask="'8(###)-###-##-##'"
                                                    :rules="[rules.required]"
                                                    id="phone"
                                                    prepend-icon="mdi-phone-classic"
                                                    label="Телефон"
                                                    required
                                            ></v-text-field>
                                        </v-row>
                                    <v-row
                                            cols="12"
                                            md="4"
                                    >
                                        <v-text-field
                                                name="locale"
                                                v-model="locale"
                                                :rules="[rules.required]"
                                                prepend-icon="mdi-map-marker"
                                                label="Адрес"
                                                required
                                        ></v-text-field>
                                    </v-row>
                                    <v-row>
                                        <v-file-input
                                                name="file"
                                                type="file"
                                                v-model="file"
                                                accept="image/png, image/jpeg, image/bmp"
                                                id="file"
                                                ref="file"
                                                prepend-icon="mdi-camera"
                                                label="Фото"
                                        ></v-file-input>
                                    </v-row>
                                    <v-row
                                            cols="12"
                                            md="4"
                                    >
                                        <v-text-field
                                                name="password"
                                                prepend-icon="mdi-lock-question"
                                                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                :rules="[rules.required, rules.min]"
                                                :type="show1 ? 'text' : 'password'"
                                                v-model="password"
                                                :error-messages="errorMessages"
                                                label="Пароль"
                                                hint="At least 8 characters"
                                                counter
                                                required
                                                @click:append="show1 = !show1"
                                        ></v-text-field>
                                        <v-text-field
                                                prepend-icon="mdi-lock-question"
                                                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                                                :rules="[rules.required,rules.eqvelsPassword, rules.min]"
                                                :type="show1 ? 'text' : 'password'"
                                                v-model="password2"
                                                :error-messages="errorMessages"
                                                label="Повторите пароль"
                                                hint="At least 8 characters"
                                                counter
                                                required
                                                @click:append="show1 = !show1"
                                        ></v-text-field>
                                    </v-row>

                                </v-container>
                            </v-card-text>
                            <v-card-actions>
                                <v-row justify="center">
                                    <v-btn block disable  type="submit" text color="success"> Зарегистрироваться
                                    </v-btn>
                                </v-row>
                            </v-card-actions>


                        </v-card>
                        </form>
                    </v-container>
                </v-content>
        </v-app>
</template>
<script>
    import {mapActions} from 'vuex'
    import {TheMask} from 'vue-the-mask'
    export default {
        components: {TheMask},
        data () {
            return {
                errors : [],
                errorMessages: '',
                name: null,
                email: null,
                locale: null,
                phone: null,
                img:null,
                file: null,
                show1: null,
                password: '',
                password2: '',
                rules: {
                    required: value => !!value || 'Не заполнено',
                    min: v => v.length >= 8 || 'Минимум 8 символов',
                    eqvelsPassword:()=>this.password==this.password2||"Пароли не совпадают"
                },
            }
        },
        computed: {
            form () {
                return {
                    name: this.name,
                    email: this.email,
                    locale: this.locale,
                    password: this.password,
                    phone: this.phone,
                }
            },
        },
        methods: {
            ...mapActions(['addUserAction']),
            created() {
                if(this.password==this.password2){
                    this.addUserAction(this.form, this.file)
                    this.$router.replace('/login')
                }else{
                    this.errorMessages='Пароли не совпадают'
                }
            },
            checkForm:function(e) {
                if(this.password2==this.password && this.password.length >= 8 ) return true;
                e.preventDefault();
            }

        }
    }

</script>
<style>
    .v-header{
        background-image:linear-gradient(60deg,#66bb6a,#388e3c);
        text-align: center;
        width: 100%;
        margin-top: -40px;
    }
    h4{
        font-family: "Times New Roman", Times, serif;
        color: white;
    }
    .outer-card{
        border-radius: 8px !important;
    }
    .v-input__icon--prepend .v-icon {
        color: black;
    }
</style>