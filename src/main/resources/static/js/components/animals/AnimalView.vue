<template>
    <v-card>
        <v-card-title>
            Питомец
            <v-row >
                <v-col class="raz">
                    <v-btn  v-if="auth.id == animal.owner.id" @click="change" color="primary" fab x-small dark>
                        <v-icon>mdi-pencil</v-icon>
                    </v-btn>
                    <v-btn  @click="close" class="mx-2" fab dark x-small color="primary">
                        <v-icon dark>mdi-close</v-icon>
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-title>
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="4" class="text-center">
                        <v-avatar size="130" class="mr-3" >
                            <v-img :src="src"></v-img>
                        </v-avatar>
                    </v-col>
                    <v-col cols="8">
                        <v-row>
                            <v-text-field label="Кличка" v-model="animal.name" :disabled="!isEditing"required></v-text-field>
                        </v-row>
                        <v-row v-if="animal.typeAnimal">
                            <v-text-field label="Тип" v-model="animal.typeAnimal.type" :disabled="!isEditing"required></v-text-field>
                        </v-row>
                        <v-row v-if="animal.breedAnimal">
                            <v-text-field label="Порода" v-model="animal.breedAnimal.name" :disabled="!isEditing"required></v-text-field>
                        </v-row>
                        <v-row>
                            <v-col cols="6">
                                <v-text-field label="Пол" v-model="animal.sex" :disabled="!isEditing"required></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field label="Возраст" v-model="animal.age" :disabled="!isEditing"required></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-label> Хозяйн
                                <v-list-item
                                             :href=link
                                             ripple>
                                    <v-list-item-avatar>
                                        <img v-if="animal.owner.img" :src="'/img/'+this.animal.owner.img">
                                        <img v-else :src=tempImg>
                                    </v-list-item-avatar>
                                    <v-list-item-content>
                                        <v-list-item-title v-html="animal.owner.name"></v-list-item-title>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-label>

                            <v-textarea label="Дополнительная информация" v-model="animal.info" :disabled="!isEditing"
                                        required></v-textarea>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-card-text>
    </v-card>

</template>
<script>
    import {mapState, mapActions} from 'vuex'
    export default {
        props:['animal','isEditing', 'close'],
        computed: mapState(['auth','profile']),
        data(){
            return{
                src:this.animal.img?"/img/"+this.animal.img:"/img/no_foto.png",
                tempImg:"/img/no_foto.png",
                link:'/user/'+this.animal.owner.id
            }
        },
        methods:{
            ...mapActions(['resetChangeAnimalAction']),
            ...mapActions(['setChangeAnimalAction']),
            goUser(){
                this.$router.replace()
            },
            change(){
                this.setChangeAnimalAction(this.animal)
                this.$router.replace('/animal')
            }
        }
    }

</script>
<style>
    .raz {
        text-align: right;
    }
</style>