import { getFormAxiosInstance } from './index'
import { useUserStore } from '@/stores/users'

// 만약 별도의 api 모듈을 생성하지 않았다면 axios 를 호출하는 .vue 파일의 script 태그 내부에 해당 부분 복붙.
// import { useUserStore } from '@/stores/users'
// const user = useUserStore()
// const axios = getJsonAxiosInstance(user.getLoginUserInfo)

function addFood (url, FoodData) {
  const user = useUserStore()
  const axios = getFormAxiosInstance(user.getLoginUserInfo)
  return axios.post(url, FormData)
}

function getFood (url) {
  const user = useUserStore()
  const axios = getFormAxiosInstance(user.getLoginUserInfo)
  return axios.get(url)
}

export { addFood, getFood }
