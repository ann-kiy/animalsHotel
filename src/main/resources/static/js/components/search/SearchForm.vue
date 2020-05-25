<template>
    <v-container >
        <v-row>
            <v-col cols="12">
                <v-card>
                    <v-card-title>
                        Задайте фильтры
                    </v-card-title>
                    <v-card-text>
                        <v-row>
                            <v-col cols="3">
                                <v-combobox
                                        v-model="selectType"
                                        :items="typeAnimal"
                                        @click="this.breedAnimals=null"
                                        item-text="type"
                                        name="typeAnimal"
                                        label="Тип питомца"
                                        outlined
                                        dense
                                ></v-combobox>
                            </v-col>
                            <v-col cols="3">
                                <v-combobox
                                        v-model="selectBreed"
                                        :items="breedAnimals"
                                        item-text="name"
                                        name="breedAnimal"
                                        label="Порода питомца"
                                        @click="getBreed"
                                        outlined
                                        dense
                                ></v-combobox>
                            </v-col>
                            <v-col cols="3">
                                <v-combobox
                                        v-model="selectSex"
                                        :items="itemsText"
                                        name="sex"
                                        label="Пол"
                                        outlined
                                        dense
                                ></v-combobox>
                            </v-col>
                            <v-col cols="3">
                                <v-combobox
                                        v-model="selectAge"
                                        :items="itemAge"
                                        label="Возраст"
                                        outlined
                                        dense
                                ></v-combobox>
                            </v-col>
                        </v-row>
                        <v-row justify="space-around">
                            <v-col cols="12">
                                <header>Условие приема</header>
                            </v-col>
                            <v-switch v-model="if1" @change="selectCond(1)" class="mx-2" label="Бузвозмездно"></v-switch>
                            <v-switch v-model="if2" @change="selectCond(2)" class="mx-2" label="За вознаграждение"></v-switch>
                            <v-switch v-model="if3" @change="selectCond(3)" class="mx-2" label="За деньги"></v-switch>
                        </v-row>

                        <v-row
                                cols="12"
                        >
                            <v-col cols="12">
                                <header>Временной промежуток</header>
                            </v-col>
                                <v-col cols="6">
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
                                </v-col>
                            <v-col cols="6">
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
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col class="text-right">
                                <v-btn
                                        color="primary"
                                        depressed
                                        @click="search"
                                >
                                    <v-icon left>mdi-layers-search-outline</v-icon>
                                    Найти
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
        <v-row>
            <v-col  cols="12">
                <advertisement-form  :items=searchAdvert></advertisement-form>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
    import {mapState, mapActions, mapGetters} from 'vuex'
    import AdvertisementForm from 'components/ advertisements/AdvertisementForm.vue'
    const axios = require('axios')

    export default {
        components:{
            AdvertisementForm
        },
        computed:{
            ...mapState(['itemAge', 'itemsText', 'itemCondition']),
            ...mapGetters(['typeAnimal'])
        },
        data(){
            return{
                selectType:null,
                selectBreed:null,
                selectAge: null,
                selectSex: null,
                breedAnimals:null,
                selectCondition:null,
                if1:null,
                if2:null,
                if3:null,
                startMenu:false,
                endMenu:false,
                end:null,
                start:null,
                searchAdvert:[]
            }
        },
        methods:{
            getBreed() {
                axios
                    .get('/model/' + this.selectType.type)
                    .then(response => (this.breedAnimals = response.data));
            },
            selectCond(i){
                if(i==1) {
                    this.if2 = false
                    this.if3 = false
                    this.selectCondition="Бузвозмездно"
                }else if(i==2){
                    this.if1 = false
                    this.if3 = false
                    this.selectCondition="За вознаграждение"
                }else{
                    this.if2 = false
                    this.if1 = false
                    this.selectCondition="За деньги"
                }
            },
            getStringQuery(){

                // query+=" ,breedAnimal:"+this.selectBreed!=null?this.selectBreed.id:null
                return query;
            },
            search(){
                let query=""
                if(this.selectType) {
                    query = "typeAnimal:"
                    query+=this.selectType.id
                    query += "; typeAnimal:0"
                    if(this.selectBreed){
                        query += ", breedAnimal:"
                        query+=this.selectBreed.id
                        query += "; breedAnimal:0"
                    }

                }
                if(this.selectSex){
                    query += ", sex:"
                    query+=this.selectSex
                    query += "; sex:-1"
                }
                if(this.selectAge){
                    query += ", age:"
                    query+=this.selectAge
                    query += "; age:0"
                }
                if(this.selectCondition){
                    query += ", condition:"
                    query+=this.selectCondition
                }
                if(this.start) {
                    query += ", dateStart<"
                    query += this.start
                }
                if(this.end) {
                    query += ", dateEnd>"
                    query += this.end
                }

                query+=", state:true"
                axios
                    .get('/advertisement?search= '+query)
                    .then(response=>{this.searchAdvert=response.data})
            }
        }
    }
</script>
<style>

</style>