<template>
    <v-container >
        <v-row>
            <v-col cols="12">
                <v-card>
                    <v-card-title>
                        Поиск объявлений
                    </v-card-title>
                    <v-card-text>
                        <v-row>
                            <v-col cols="12">
                                <header>Укажите фильтры</header>
                            </v-col>
                            <v-col cols="3">
                                <v-combobox
                                        v-model="selectType"
                                        :items="typeAnimal"
                                        @click="this.breedAnimals=[]"
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
                        <v-row v-if="animalsAuthByUser.length!=0">
                            <v-autocomplete v-model="animal" :items="animalsAuthByUser" filled
                                            color="blue-grey lighten-2"
                                            label="Или выберете питомца"
                            >
                                <template v-slot:selection="data">
                                    <v-chip
                                            v-bind="data.item"
                                            :input-value="data.selected"
                                            close
                                    >
                                        <v-avatar left>
                                            <v-img v-if="data.item.img" :src="'/img/'+data.item.img"></v-img>
                                            <v-img v-else :src="tempImg"></v-img>
                                        </v-avatar>
                                        {{ data.item.name }}
                                    </v-chip>
                                </template>
                                <template v-slot:item="data">
                                    <template >
                                        <v-list-item-avatar>
                                            <img v-if="data.item.img" :src="'/img/'+data.item.img">
                                            <img v-else :src="tempImg">
                                        </v-list-item-avatar>
                                        <v-list-item-content>
                                            <v-list-item-title v-text="data.item.name"></v-list-item-title>
                                            <!--                                <v-list-item-subtitle v-html="data.item.group"></v-list-item-subtitle>-->
                                        </v-list-item-content>
                                    </template>
                                </template>

                            </v-autocomplete>
                        </v-row>
                        <v-row justify="space-around">
                            <v-col cols="12">
                                <header>Условие приема</header>
                            </v-col>
                            <v-switch v-model="if1" @change="selectCond(1)" class="mx-2" label="Безвозмездно"></v-switch>
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
            ...mapGetters(['typeAnimal', 'animalsAuthByUser'])
        },
        data(){
            return{
                selectType:null,
                selectBreed:null,
                selectAge: null,
                selectSex: null,
                breedAnimals:null,
                selectCondition:null,
                if1:false,
                if2:false,
                if3:false,
                startMenu:false,
                endMenu:false,
                end:null,
                start:null,
                searchAdvert:[],
                tempImg: "/img/no_foto.png",
                animal:null
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
                    this.selectCondition="Безвозмездно"
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
                let query=""
                if(this.animal){
                        query = "typeAnimal:"
                        query += this.animal.typeAnimal.id
                        query += "; typeAnimal:0"

                            query += ", breedAnimal:"
                            query += this.animal.breedAnimal.id
                            query += "; breedAnimal:0"

                        query += ", sex:"
                        query += this.animal.sex
                        query += "; sex:-1"

                        query += ", age:"
                        query += this.animal.age
                        query += "; age:0"
                }else {
                    if (this.selectType) {
                        query = "typeAnimal:"
                        query += this.selectType.id
                        query += "; typeAnimal:0"
                        if (this.selectBreed) {
                            query += ", breedAnimal:"
                            query += this.selectBreed.id
                            query += "; breedAnimal:0"
                        }

                    }
                    if (this.selectSex) {
                        query += ", sex:"
                        query += this.selectSex
                        query += "; sex:-1"
                    }
                    if (this.selectAge) {
                        query += ", age:"
                        query += this.selectAge
                        query += "; age:0"
                    }
                }
                // !((this.if1===false) && (this.if2===false) && (this.if3===false))
                if(!((this.if1===false) && (this.if2===false) && (this.if3===false))){
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
                return query;
            },
            search(){
                axios
                    .get('/advertisement?search= '+this.getStringQuery())
                    .then(response=>{this.searchAdvert=response.data})
            }
        }
    }
</script>
<style>

</style>