<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
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
                        <v-btn>Просмотреть</v-btn>
                    </v-overlay>
                </v-fade-transition>
            </v-card>
        </template>
    </v-hover>
</template>
<script>
    export default {
        props:  ['animal'],
        data() {
            return {
                overlay: false,
                src: this.animal.img?this.animal.img:"no_foto.png"
            }
        },
    }
</script>
<style>

</style>