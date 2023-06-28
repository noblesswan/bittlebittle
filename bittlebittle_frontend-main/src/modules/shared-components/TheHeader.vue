<template>
        <v-navigation-drawer v-model="sidebar" app>
            <v-list>
                <v-list-tile
                v-for="item in menuItems"
                :key="item.title"
                :to="item.path">
                <v-list-tile-action>
                    <v-icon>{{ item.icon }}</v-icon>
                </v-list-tile-action>
                <v-list-tile-content>{{ item.title }}</v-list-tile-content>
                </v-list-tile>
            </v-list>
            </v-navigation-drawer>

            <v-toolbar floating app color="rgb(18, 18, 18)" dark class="ma-15">
                <span class="hidden-sm-and-up">
                    <v-toolbar-side-icon @click="sidebar = !sidebar">
                    </v-toolbar-side-icon>
                </span>
                <v-toolbar-title>
                    <router-link to="/" style="cursor: pointer; text-decoration: none; color:orange">
                    {{ appTitle }}
                    </router-link>
                </v-toolbar-title>
                <v-toolbar-items class="hidden-xs-only">
                    <v-btn
                    flat
                    v-for="item in menuItems"
                    :key="item.title"
                    :to="item.path">
                    <v-icon left dark>{{ item.icon }}</v-icon>
                    {{ item.title }}
                    </v-btn>
                </v-toolbar-items>
            </v-toolbar>
</template>

<script>
// import { useUserStore } from '@/stores/users';
// import { storeToRefs } from 'pinia';
// import { restApiGets } from '@/api/index';

export default {
    name : "TheHeader"
    , setup() {

        const appTitle = 'BITTLE BITTLE';
        const sidebar = false;
        const menuItems = [
          { title: 'Home', path: '/home', icon: 'home' },
          { title: 'Sign Up', path: '/signup', icon: 'face' },
          { title: 'Sign In', path: '/signin', icon: 'lock_open' }
        ]

        return {appTitle, sidebar, menuItems};
        // const user = useUserStore();

        // const {getLoginUser} = storeToRefs(user);

        // const resetUserStore = () => {
        //     user.setLoginUser({});
        // }
        // // restApiGets(get)

        // return {resetUserStore, getLoginUser };
    }
}
</script>

<style>

</style>