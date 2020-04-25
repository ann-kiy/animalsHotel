<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <v-row>
        <v-col cols="4">
            <v-card>
                <v-card-title>
                    <v-avatar size="80" class="mr-3">
                        <img v-bind:src="src">
                    </v-avatar>
                    {{profile.name}}
                    <v-rating  v-model="rating"></v-rating>

                </v-card-title>
                <v-divider></v-divider>
                <v-card-text>
                    <v-text-field
                            disabled
                            label="Prepend"
                            prepend-icon="mdi-phone-classic"
                    ></v-text-field>
                    <v-text-field
                            disabled
                            label="Prepend"
                            prepend-icon="mdi-email"
                    ></v-text-field>
                    <v-text-field
                            disabled
                            label="Prepend"
                            prepend-icon="mdi-map-marker"
                    ></v-text-field>
                </v-card-text>
            </v-card>
            <!--            <v-card-->
            <!--                    class="mx-auto"-->
            <!--                    max-width="400"-->
            <!--            >-->
            <!--                <v-img-->
            <!--                        class="white&#45;&#45;text align-end"-->
            <!--                        height="200px"-->
            <!--                        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"-->
            <!--                >-->
            <!--                    <v-card-title>Top 10 Australian beaches</v-card-title>-->
            <!--                </v-img>-->

            <!--                <v-card-subtitle class="pb-0">Number 10</v-card-subtitle>-->

            <!--                <v-card-text class="text&#45;&#45;primary">-->
            <!--                    <div>Whitehaven Beach</div>-->

            <!--                    <div>Whitsunday Island, Whitsunday Islands</div>-->
            <!--                </v-card-text>-->

            <!--                <v-card-actions>-->
            <!--                    <v-btn-->
            <!--                            color="orange"-->
            <!--                            text-->
            <!--                    >-->
            <!--                        Share-->
            <!--                    </v-btn>-->

            <!--                    <v-btn-->
            <!--                            color="orange"-->
            <!--                            text-->
            <!--                    >-->
            <!--                        Explore-->
            <!--                    </v-btn>-->
            <!--                </v-card-actions>-->
            <!--            </v-card>-->
        </v-col>
        <v-col cols="8"

        >
        </v-col>
    </v-row>
</template>
<script>
    import {mapState,mapMutations} from 'vuex'

    export default {
        computed:mapState(['profile', 'src']),
        data() {
            return {
                hasSaved: false,
                isEditing: null,
                model: null,
                states: [
                    {name: 'Florida', abbr: 'FL', id: 1},
                    {name: 'Georgia', abbr: 'GA', id: 2},
                    {name: 'Nebraska', abbr: 'NE', id: 3},
                    {name: 'California', abbr: 'CA', id: 4},
                    {name: 'New York', abbr: 'NY', id: 5},
                ],
            }
        },

        methods: {
            customFilter(item, queryText, itemText) {
                const textOne = item.name.toLowerCase()
                const textTwo = item.abbr.toLowerCase()
                const searchText = queryText.toLowerCase()

                return textOne.indexOf(searchText) > -1 ||
                    textTwo.indexOf(searchText) > -1
            },
            save() {
                this.isEditing = !this.isEditing
                this.hasSaved = true
            },
        },
    }
</script>
<style>

</style>