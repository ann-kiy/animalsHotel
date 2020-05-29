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
                    <response :response="item" :delResp="delResp"></response>
                </v-row>
            </template>
        </v-data-iterator>
    </v-container>
</template>
<script>
    import Response from 'components/responses/MyResponse.vue'
    export default {
        props:['items', 'delResp'],
        components:{
            Response
        },
        data(){
            return{
                tempImg:"/img/no_foto.png",
                model:null
            }
        }
    }

</script>
<style>

</style>
