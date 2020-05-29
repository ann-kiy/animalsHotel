<template>
    <v-card  class="px-3; my-3" width="100%">
        <v-row>
            <v-col class="text-left">
                <v-list-item
                        :href=link
                        ripple>
                    <v-list-item-avatar>
                        <img v-if="response.advertisement.user.img" :src="'/img/'+response.advertisement.user.img">
                        <img v-else :src=tempImg>
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title v-html="response.advertisement.user.name"></v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-col>
            <v-col  v-if="response.stateRecipient" class="text-left">
                <v-btn
                        dark
                        icon
                        x-small
                        absolute
                >
                    <v-icon large color="blue darken-2">mdi-message-text</v-icon>
                </v-btn>
            </v-col>
            <v-col class="text-right">
                <v-chip
                        v-if="response.stateRecipient"
                        color="green"
                        fab
                        dark
                        bottom
                        left
                        class="v-btn--example"
                >
                    <v-icon class="pr-2">mdi-checkbox-marked-circle-outline</v-icon>
                    Подтверждено
                </v-chip>
                <v-chip
                        v-else
                        color="yellow"
                        fab
                        dark
                        bottom
                        left
                        class="v-btn--example"
                >
                    <v-icon class="pr-2">mdi-timer-sand</v-icon>
                    В ожидании
                </v-chip>
            </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row>
            <v-col >
                <v-icon class="pr-2">mdi-dog</v-icon>{{response.animal.name}}
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-icon class="pr-2">mdi-calendar-range</v-icon>{{response.advertisement.dateStart}} - {{response.advertisement.dateEnd}}
            </v-col>
        </v-row>
        <v-row>
            <v-col >
                <v-icon class="pr-2">mdi-map-marker</v-icon>{{response.user.locale}}
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-icon class="pr-2">mdi-cash-register</v-icon>{{response.advertisement.condition}}
            </v-col>
        </v-row>
        <v-fab-transition>
            <v-btn
                    color="red"
                    fab
                    dark
                    small
                    absolute
                    bottom
                    right
                    @click="del"
            >
                <v-icon>mdi-trash-can-outline</v-icon>
            </v-btn>
        </v-fab-transition>
    </v-card>

</template>
<script>
    export default {
        props: ['response', 'delResp']   ,
        data(){
            return {
                tempImg: "/img/no_foto.png",
                link:'/user/'+this.response.advertisement.user.id
            }
        },
        methods:{
            del(){
                this.delResp(this.response)
            }
        }
    }

</script>
<style>

</style>