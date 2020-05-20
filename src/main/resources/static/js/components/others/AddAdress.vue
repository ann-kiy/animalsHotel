<template>
    <v-card>
        <v-card-title class="headline">Изменение адреса</v-card-title>
        <v-card-text>
            <v-row>
                <v-col cols="4" align-self="center">
                    <v-label>Новый адрес</v-label>
                </v-col>
                <v-col cols="8">
                    <v-text-field
                            name="loc"
                            id="loc"
                            v-model="locale"
                            required
                    ></v-text-field>
                </v-col>
            </v-row>
        </v-card-text>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#007bb6" text @click="changeAddres">Изменить</v-btn>
            <v-btn color="#007bb6" text @click="cancel">Отмена</v-btn>
        </v-card-actions>
    </v-card>
</template>
<script>
    import { loadYmap } from 'vue-yandex-maps'
    const settings = {
        apiKey: '01c977d4-510b-47ea-bdfc-2e5eb7e9235b',
        lang: 'ru_RU',
        coordorder: 'latlong',
        version: '2.1'
    }
    function init() {
        // Создаем выпадающую панель с поисковыми подсказками и прикрепляем ее к HTML-элементу по его id.
        var suggestView = new ymaps.SuggestView('loс');
    }
    export default {
        props:['change', 'cancel'],
        async mounted() {
            await loadYmap({ ...settings, debug: true });
            ymaps.ready(init)
        },
        data(){
            return{
                locale:null
            }
        },
        methods: {
            changeAddres(){
                this.change(locale);
            }
        }
    }
</script>
<style>

</style>