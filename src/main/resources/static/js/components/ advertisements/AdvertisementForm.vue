<template>
    <v-container fluid>
        <v-data-iterator
                v-if="items.length>0"
                :items="items"
                hide-default-footer
        >

            <template v-slot:default="props">
                <v-row
                        v-for="item in props.items"
                        :key="item.id"
                        cols="12"
                        sm="6"
                        pd="10"
                        lg="3"
                >
                   <advertisement-profile :advertisement="item"
                                            :deleteAdvert="deleteAdvert"></advertisement-profile>
                </v-row>
            </template>
        </v-data-iterator>
    </v-container>
</template>
<script>
    import AdvertisementProfile from 'components/ advertisements/Advertisement.vue'
    const axios = require('axios')

    export default {
        props:['items'],
        components:{
            AdvertisementProfile
        },
        data () {
            return {
                animals:this.items
            }
        },
        methods: {
            deleteAdvert(advert){
                axios
                    .delete('/advertisement/'+advert.id);
            }
        },
    }
</script>
<style>

</style>