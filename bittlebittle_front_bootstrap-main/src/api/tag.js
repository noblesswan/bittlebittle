import { createAxiosInstance, getJsonAxiosInstance } from './index'
import { useUserStore } from '@/stores/users'

async function $getTags (boardNo, replyData) {
  try {
    const axios = createAxiosInstance()
    return axios.get('/api/tags')
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

async function $getUserTags (userNo) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return axios.get(`/api/users/${userNo}/tags`)
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

async function $addUserTags (userNo, tagNoList) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return axios.post(`/api/users/${userNo}/tags`, tagNoList)
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

async function $removeUserTags (userNo, tagNoList) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return axios.post(`/api/users/${userNo}/tags/deletion`, tagNoList)
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

export {
  $getTags, $getUserTags, $addUserTags, $removeUserTags
}
