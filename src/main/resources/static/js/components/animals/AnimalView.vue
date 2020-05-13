<template>
    <v-card>
        <v-card-title>
            Питомец
        </v-card-title>
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="4">
                        <v-img :src="src"></v-img>
                        <v-row>
                            <v-rating
                                    v-model=animal.rating
                                    color="yellow darken-3"
                                    background-color="grey darken-1"
                                    dense
                                    half-increments
                                    hover
                                    size="18"
                            ></v-rating>
                        </v-row>
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
                            <v-textarea label="Дополнительная информация" v-model="animal.info" :disabled="!isEditing"required></v-textarea>
                        </v-row>

                    </v-col>
                </v-row>
            </v-container>
        </v-card-text>
    </v-card>

</template>
<script>
    export default {
        props:['animal','isEditing'],
        data(){
            return{
                src:this.animal.img?"/img/"+this.animal.img:"/img/no_foto.png"
            }
        }
    }

</script>
<style>

</style>