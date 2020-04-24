<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app id="inspire">
        <header-line v-if="profile"/>
        <v-content>

            <router-view></router-view>
            <v-container class="fill-height" fluid>
            </v-container>
        </v-content>
    </v-app>


</template>
<script>
    import {mapState,mapMutations} from 'vuex'
    import HeaderLine from 'components/head/HeaderLine.vue'
    import {addHandler} from "util/ws"

    export default {
        props: {
            source: String,
        },
        methods:mapMutations(['addMessageMutation','removeMessageMutation','updateMessageMutation']),
        components: {
            HeaderLine
        },
        computed:mapState(['profile']),
        created(){
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        },
        beforeMount() {
            if (this.profile==null) {
                this.$router.replace('/login')
            }
        }
    }

</script>
<style>
</style>