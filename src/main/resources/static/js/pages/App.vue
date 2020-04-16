<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app id="inspire">
        <v-navigation-drawer
                v-model="drawer"
                app
        >
            <v-list dense>
                <v-list-item link>
                    <v-list-item-action>
                        <v-avatar>
                            <img v-bind:src="src">
                        </v-avatar>
<!--                        <v-icon>mdi-home</v-icon>-->
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Home</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>mdi-contact-mail</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Contact</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
            <v-app-bar
                    app
                    dark
                    prominent
                    src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
            >
                <v-app-bar-nav-icon  @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

                <v-toolbar-title><a href="/"> Mu-xru-mu</a></v-toolbar-title>

                <v-spacer></v-spacer>
                <div class="text-center">
                <v-menu offset-y>
                    <template v-slot:activator="{ on }">
                        <v-btn text
                                color="light-blue lighten-5"
                                dark
                                v-on="on"
                        >
                            <v-avatar>
                                <img
                                        v-bind:src="src"
                                        alt="John"
                                >
                            </v-avatar>
                            {{auth.name}}
                            <v-icon>mdi-chevron-down</v-icon>
                        </v-btn>
                    </template>
                    <v-list>
                        <v-list-item @click="" href="">
                            <v-list-item-title>Мои животные</v-list-item-title>
                        </v-list-item>
                        <v-list-item @click="" href="">
                            <v-list-item-title>Мои объявления</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>
                </div>
                <v-btn icon href="">
                    <v-icon>mdi-view-grid-plus</v-icon>
                </v-btn>
                <v-btn icon href="">
                    <v-icon>mdi-magnify</v-icon>
                </v-btn>
                <v-btn icon href="/logout">
                    <v-icon>mdi-export</v-icon>
                </v-btn>
            </v-app-bar>
        <v-content>
            <v-container class="fill-height" fluid>
                <messages-list :messages="messages"/>'
            </v-container>
        </v-content>
    </v-app>


</template>
<script>
    import MessagesList from 'components/messages/MessageList.vue'
    import HeaderLine from 'components/head/HeaderLine.vue'
    import {addHandler} from "util/ws"
    import {getIndex} from "util/collections"

    export default {
        props: {
            source: String,
        },
        components: {
            MessagesList,
            HeaderLine
        },
        data(){
            if(frontendData)
            return {
            src: "/img/"+auth.img,
            messages: frontendData.messages,
            profile: frontendData.profile,
                auth:auth,
                drawer: null,
        }
        },
        created(){
            addHandler(data=>{
                let index=getIndex(this.messages,data.id)
                if(index>-1){
                    this.messages.splice(index,1,data)
                }else{
                    this.messages.push(data)
                }
            })
        }
    }

</script>
<style>
</style>