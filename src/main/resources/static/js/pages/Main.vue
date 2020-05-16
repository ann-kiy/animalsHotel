<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <v-container>
        <v-row>
            <v-col cols="4">
                <v-card>
                    <v-card-title>
                        <v-avatar size="80" class="mr-3">
                            <img v-bind:src="'/img/'+profile.img">
                        </v-avatar>
                        {{profile.name}}
                        <v-rating v-model="profile.rating"></v-rating>

                    </v-card-title>
                    <v-divider></v-divider>
                    <v-card-text>
                        <p>
                            <v-icon>mdi-phone-classic</v-icon>
                            {{profile.phone}}
                        </p>
                        <p>
                            <v-icon>mdi-email</v-icon>
                            {{profile.email}}
                        </p>
                        <p>
                            <v-icon>mdi-map-marker</v-icon>
                            {{profile.locale}}
                        </p>
                    </v-card-text>
                    <v-card-actions>
                        <v-fab-transition>
                            <v-btn
                                    @click=""
                                    color="pink"
                                    dark
                                    bottom
                                    absolute
                                    right
                                    fab
                            >
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </v-card-actions>
                </v-card>

            </v-col>
            <v-col  fluid cols="8">
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

                        <router-link tag="v-btn" to="/animal" class="m-2">
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
                            Мои животные
                        </v-tab>
                        <v-tab href="#tabs-2">
                            Мои объявления
                        </v-tab>
                        <v-tab href="#tabs-3">
                            Мои подписки
                        </v-tab>
                    </v-tabs>
                </v-row>
                    <v-tabs-items tag="v-row" v-model="tabs">
                        <v-tab-item
                                    v-for="i in 3"
                                    :key="i"
                                    :value="'tabs-' + i"
                        >
                            <v-row fluid>
                                <v-col cols-12>
                                <animals-form v-if="i==1" :items=animalsByUser></animals-form>
                                    <advertisement-form v-if="i==2" :items=advertsByUser></advertisement-form>
                                </v-col>
                            </v-row>
                            <!--                        <v-card flat>-->
                            <!--                            <v-card-text v-text="text"></v-card-text>-->
                            <!--                        </v-card>-->
                        </v-tab-item>
                    </v-tabs-items>

            </v-col>

        </v-row>
    </v-container>
</template>
<script>
    import {mapState, mapActions, mapGetters} from 'vuex'
    import AnimalsForm from 'components/animals/AnimalsForm.vue'
    import AdvertisementForm from 'components/ advertisements/AdvertisementForm.vue'

    const axios = require('axios')

    export default {
        computed: {...mapState(['profile', 'src', 'auth']), ...mapGetters(['advertsByUser', 'animalsByUser'])},
        methods: {
            ...mapActions(['getAnimalsAction']),
            routeAnimal(){
                this.$router.replace('/animal')
            }
        },
        components: {
            AnimalsForm,
            AdvertisementForm
        },
        data() {
           return {
                tabs: null,
                text: 'Здесь инфа ...........................................................ююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююююю...........',
            }
        }
    }
</script>

<style>

</style>