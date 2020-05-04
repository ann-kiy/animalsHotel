<template>
    <v-dialog v-model="dialog" scrollable >
        <template  v-slot:activator="{ on }">
            <v-card class="my-2" width="100%">
                <v-card-title>Тип:{{type}} Порода:{{breed}}
                    <v-btn v-if="profile.id!=auth.id" v-on="on" class="mx-2" fab small dark color="teal">
                        <v-icon dark>mdi-plus-box-multiple</v-icon>
                    </v-btn>
                </v-card-title>
                <v-card-subtitle>C {{advertisement.dateStart}} до {{advertisement.dateEnd}}</v-card-subtitle>
                <v-card-actions>{{advertisement.condition}}</v-card-actions>
                <v-card-text v-if="advertisement.age">{{advertisement.age}}</v-card-text>
                <v-card-text v-if="advertisement.sex">{{advertisement.sex}}</v-card-text>
                <v-card-text v-if="advertisement.info">{{advertisement.info}}</v-card-text>
                <v-card-text>{{advertisement.createDate}}</v-card-text>
                <div class="text-right" v-if="profile.id===auth.id">
                    <v-btn color="error" fab small dark>
                        <v-icon>mdi-trash-can-outline</v-icon>
                    </v-btn>
                    <v-btn color="primary" fab small dark>
                        <v-icon>mdi-pencil</v-icon>
                    </v-btn>
                    <v-badge
                            color="red"
                            content="6"
                    >
                        <v-btn class="mx-2" fab small dark color="teal">
                            <v-icon dark>mdi-format-list-bulleted-square</v-icon>
                        </v-btn>
                    </v-badge>
                </div>
            </v-card>
        </template>
        <v-card>
            <v-card-title>Выберите питомца, которого ходите пристроить</v-card-title>
            <v-divider></v-divider>
            <v-card-text style="height: 300px;">
                <v-autocomplete v-model="dialogm1" :items="animalsByUser" filled
                                color="blue-grey lighten-2"
                                label="Select"
                                name="name"
                                item-value="name"
                >
                    <template v-slot:selection="data">
                        <v-chip
                                v-bind="data.item"
                                :input-value="data.selected"
                                close
                        >
                            <v-avatar left>
                                <v-img v-if="data.item.img" :src="'/img/'+data.item.img"></v-img>
                                <v-img v-else :src="'/img/'+no_foto.png"></v-img>
                            </v-avatar>
                            {{ data.item.name }}
                        </v-chip>
                    </template>
                    <template v-slot:item="data">
                        <template >
                            <v-list-item-avatar>
                                <img v-if="data.item.img" :src="'/img/'+data.item.img">
                                <img v-else :src="'/img/'+no_foto.png")>
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title v-text="data.item.name"></v-list-item-title>
<!--                                <v-list-item-subtitle v-html="data.item.group"></v-list-item-subtitle>-->
                            </v-list-item-content>
                        </template>
                    </template>

                </v-autocomplete>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
                <v-btn color="blue darken-1" text @click="dialog = false">Отмена</v-btn>
                <v-btn color="blue darken-1" text @click="dialog = false">Откликнуться</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>
<script>
    import {mapState,  mapGetters} from 'vuex'

    export default {
        props: ['advertisement'],
        computed: {...mapState(['profile', 'auth']), ...mapGetters(['animalsByUser'])},
        data() {
            return {
                selectItem:null,
                dialogm1: '',
                dialog: false,
                overlay: false,
                type: this.advertisement.typeAnimal ? this.advertisement.typeAnimal.type : "Любой",
                breed: this.advertisement.breedAnimal ? this.advertisement.breedAnimal.name : "Любая"
            }
        },
        metod
    }
</script>
<style>
    .zx {
        with: 100vw
    }
</style>