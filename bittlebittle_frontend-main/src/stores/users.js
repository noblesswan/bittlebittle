import { defineStore } from "pinia";
import { computed, reactive } from "vue";

export const useUserStore = defineStore("user", () => {

    const state = reactive({ user : {} });

    const getLoginUser = computed( () => state.user )

    function setLoginUser(userData) {
        console.log(userData);
        state.user = userData;
    }

    return { state, getLoginUser, setLoginUser }
})