<template xmlns:th="http://www.w3.org/1999/xhtml">
    <div id="app">
        <v-app>
            <form  method="post" action="/login">
            <v-content>
                <v-container>
                    <v-card class="mx-auto my-12 outer-card"
                            max-width="374">
                        <v-card-title>
                            <v-card class="v-header py-2 mx-auto my-n12" max-width="364" raised>
                                <v-card-title>
                                    <v-row justify="center" color="white">
                                        <h4>Вход</h4>
                                    </v-row>
                                </v-card-title>
                                <v-card-actions>
                                    <v-row justify="center" color="white">

                                        <v-btn icon color="white" href="/login/facebook">
                                            <v-icon>mdi-facebook</v-icon>
                                        </v-btn>
                                        <v-btn icon color="white"  href="/login/google">
                                            <v-icon>mdi-google-plus</v-icon>
                                        </v-btn>
                                    </v-row>
                                </v-card-actions>
                            </v-card>
                        </v-card-title><br />
                        <v-card-text class="my-4">
                            <v-alert v-if="isActivated" type="success">
                                Аккаунт активирован
                            </v-alert>
                            <v-alert v-if="isActivated==='false'" type="error">
                                I'm an error alert.
                            </v-alert>
                            <v-text-field label="Email"
                                          name="username"
                                          prepend-icon="mdi-email"
                                          color="purple"></v-text-field>
                            <v-text-field label="Password"
                                          type="password"
                                          name="password"
                                          prepend-icon="mdi-lock-outline"
                                          color="purple"></v-text-field>
                            </v-card-text>
                            <v-card-actions>
                                <v-row justify="center">
                                    <v-btn block type="submit"  text color="success"> Войти
                                    </v-btn>
                                </v-row>
                            </v-card-actions>
                        <v-divider></v-divider>
                        <v-card-actions>
                            <v-row justify="center">
                                            <router-link
                                                to="/registration"> РЕГИСТРАЦИЯ
                                            </router-link>
                            </v-row>
                        </v-card-actions>

                    </v-card>
                </v-container>
            </v-content>
            </form>
        </v-app>
    </div>
</template>
<script>
    import {mapState} from 'vuex'

    export default {
        computed:mapState(['isActivated'])
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