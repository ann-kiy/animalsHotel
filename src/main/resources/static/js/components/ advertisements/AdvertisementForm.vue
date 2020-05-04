<template>
    <v-container fluid>
        <v-data-iterator
                :items="items"
                :items-per-page.sync="itemsPerPage"
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
                   <advertisement-profile :advertisement="item"></advertisement-profile>
                </v-row>
            </template>
        </v-data-iterator>
    </v-container>
</template>
<script>
    import AdvertisementProfile from 'components/ advertisements/Advertisement.vue'

    export default {
        props:['items'],
        components:{
            AdvertisementProfile
        },
        data () {
            return {
                itemsPerPageArray: [4, 8, 12],
                page: 1,
                itemsPerPage: 4,
                animals:this.items

            }
        },
        computed: {
            numberOfPages () {
                return Math.ceil(this.items.length / this.itemsPerPage)
            }
        },
        methods: {
            nextPage () {
                if (this.page + 1 <= this.numberOfPages) this.page += 1
            },
            formerPage () {
                if (this.page - 1 >= 1) this.page -= 1
            },
            updateItemsPerPage (number) {
                this.itemsPerPage = number
            },
        },
    }
</script>
<style>

</style>