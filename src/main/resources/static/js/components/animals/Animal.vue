<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container>
    <v-hover>
        <template v-slot:default="{ hover }">
            <v-card
                    class="mx-auto"
                    max-width="344"
            >
                <v-img :src="'/img/' + src"></v-img>

                <v-card-text>
                    <h2 class="title primary--text">{{animal.name}}</h2>
                    {{animal.info}}
                </v-card-text>
                <v-card-title>
                    <v-rating
                            :value=animal.rating
                            :disabled="true"
                            dense
                            color="orange"
                            background-color="orange"
                            hover
                            class="mr-2"
                    ></v-rating>
                </v-card-title>
                <v-fade-transition>
                    <v-overlay
                            v-if="hover"
                            absolute
                            color="#036358"
                    >
                        <v-row>
                            <v-col class="text-center">
                                <v-btn @click="dialog2=!dialog2" color="warning" fab small dark>
                                    <v-icon>mdi-eye</v-icon>
                                </v-btn>
                                <v-btn @click="change" color="primary" fab small dark>
                                    <v-icon>mdi-pencil</v-icon>
                                </v-btn>
                                <v-btn @click="del" color="error" fab small dark>
                                    <v-icon>mdi-trash-can-outline</v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-overlay>
                </v-fade-transition>
            </v-card>
        </template>
    </v-hover>
        <v-dialog
                v-model="dialog2"
                persistent
                max-width="600px"
        >
            <animal-view :animal="animal" :isEditing="false" :close="closeD"></animal-view>
        </v-dialog>
    </v-container>
</template>
<script>
    import AnimalView from 'components/animals/AnimalView.vue'
    import {mapState, mapActions} from 'vuex'

    export default {
        props:  ['animal','deleteAnimal'],
        components:{
            AnimalView
        },
        data() {
            return {
                overlay: false,
                src: this.animal.img?this.animal.img:"no_foto.png",
                dialog2:false
            }
        },
        methods:{
            ...mapActions(['resetChangeAnimalAction']),
            ...mapActions(['setChangeAnimalAction']),
            closeD(){
                this.dialog2 = false
            },
            change(){
                this.setChangeAnimalAction(this.animal)
                this.$router.replace('/animal')
            },
            del(){
                this.deleteAnimal(this.animal)
            },
        }
    }
</script>
<style>

</style>