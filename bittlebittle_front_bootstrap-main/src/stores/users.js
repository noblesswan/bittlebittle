import { defineStore } from 'pinia'
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'

export const useUserStore = defineStore('user', () => {
  // 로그인 시 header 에 전달된 jwt 와 refrechindex 정보를 전역에 저장

  const userInfo = ref(null)

  const router = useRouter()

  const getLoginUserInfo = computed(() => userInfo.value)

  const logout = () => {
    setLoginUserInfo(null)
    router.push('/')
  }
  function setLoginUserInfo (jwt) {
    userInfo.value = jwt
    console.log(userInfo.value)
  }

  const userTags = ref(null)

  const getUserTagsInfo = computed(() => userTags.value)

  function setUserTagsInfo (tags) {
    userTags.value = tags
    console.log(userInfo.value)
  }

  return { getLoginUserInfo, setLoginUserInfo, logout, setUserTagsInfo, getUserTagsInfo }
})
