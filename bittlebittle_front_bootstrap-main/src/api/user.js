import { getJsonAxiosInstance } from './index'
import { useUserStore } from '@/stores/users'

// 회원 관련 기능에서는 이미지가 필요없기 때문에 json 으로 axios 생성

// 만약 별도의 api 모듈을 생성하지 않았다면 axios 를 호출하는 .vue 파일의 script 태그 내부에 해당 부분 복붙.
// import { useUserStore } from '@/stores/users'
// const user = useUserStore()
// const axios = getJsonAxiosInstance(user.getLoginUserInfo)

function $loginUser (userData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post('/api/users/login', userData)
}

function $logoutUser () {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  try {
    return axios.post('/api/users/logout')
  } catch (error) {
    console.error('Error logging out user:', error)
  }
}

function $getUser () {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`/api/users/${user.getLoginUserInfo.userNo}`)
}

function $editUser (userData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post(`/api/users/${user.getLoginUserInfo.userNo}/set-data`, userData)
}

function $addUser (userData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post('api/users', userData)
}

function $removeUser (userNo) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`api/users/${userNo}/deletion`)
}

async function $addUserTags (tagNoList) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return await axios.post(`${user.getLoginUserInfo.userNo}/tags`, tagNoList)
  } catch (error) {
    console.error('Error adding user tags:', error)
  }
}

async function $deleteUserTags (tagNoList) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return await axios.post(`${user.getLoginUserInfo.userNo}/tags/deletion`, tagNoList)
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

async function $checkDuplicate (userId) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return await axios.post('api/users/check-duplicate', { userId: userId })
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

async function $checkDuplicateNickname (nickname) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return await axios.post('api/users/check-duplicate-nickname', { nickname: nickname})
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

// function $updateUser(userNo, updatedInfo) {
//   const user = useUserStore()
//   const axios = getJsonAxiosInstance(user.getLoginUserInfo)
//   return axios.post(`/api/users/set-data`, {
//     userNo,
//     ...updatedInfo
//   }) }

function $getReviews () {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`/api/users/${user.getLoginUserInfo.userNo}/reviews`)
}

function $getReply () {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`/api/users/${user.getLoginUserInfo.userNo}/comments`)
}

// async function $withdrawUser(userNo) {
//   const user = useUserStore()
//   const axios = getJsonAxiosInstance(user.getLoginUserInfo)
//   return axios.put(`/api/users/withdraw/${userNo}`)
// }


// function $getUserList() {
//   const user = useUserStore();
//   const axios = getJsonAxiosInstance(user.getLoginUserInfo);
//   return axios.get('/api/users');
// }

function $searchUsers(searchCriteria, searchKeyword) {
  const user = useUserStore();
  const axios = getJsonAxiosInstance(user.getLoginUserInfo);
  return axios.get('/api/users/search', {
    params: { searchCriteria, searchKeyword },
  });
}

function $deleteUsers(userNos) {
  const user = useUserStore();
  const axios = getJsonAxiosInstance(user.getLoginUserInfo);
  return axios.patch('/api/users/delete', { userNos });
}

async function $updateUsermodal(user) {
  const userStore = useUserStore();
  const axios = getJsonAxiosInstance(userStore.getLoginUserInfo);
  return axios.put(`/api/users/${userStore.getLoginUserInfo.userNo}`, user);
}



export {
  $loginUser, $getUser, $editUser, $addUser, $removeUser,
  $logoutUser, $checkDuplicate,
  $addUserTags, $deleteUserTags, $getReviews, $getReply
  , $searchUsers, $deleteUsers, $updateUsermodal  , $checkDuplicateNickname
}
