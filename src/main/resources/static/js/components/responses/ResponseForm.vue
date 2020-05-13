<template>
    <v-row justify="center">
        <v-col cols="12">
            <v-card width="100%">
                <v-list two-line >
                    <template v-for="(item, index) in items">
                        <response :item="item" :delResp="delResp"></response>
                    </template>
                </v-list>
            </v-card>
        </v-col>

    </v-row>
</template>
<script>
    import Response from 'components/responses/Response.vue'
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
