import { getJsonAxiosInstance } from './index'
import { useUserStore } from '@/stores/users'

// 만약 별도의 api 모듈을 생성하지 않았다면 axios 를 호출하는 .vue 파일의 script 태그 내부에 해당 부분 복붙.
// import { useUserStore } from '@/stores/users'
// const user = useUserStore()
// const axios = getJsonAxiosInstance(user.getLoginUserInfo)

function $getBoardList () {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get('/api/boards')
}

function $getBoardDetail (boardNo) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`/api/boards/${boardNo}`)
}

function $editBoard (boardNo, boardData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post('/api/boards/set-data', boardData)
}

function $addBoard (boardData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post('/api/boards', boardData)
}

function $removeBoard (boardNo) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`/api/boards/${boardNo}/deletion`)
}

function getReplyList (url) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(url)
}

function $addReply (url, replyData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post(url, replyData)
}

async function $editReply (boardNo, replyData) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return axios.post(`/boards/${boardNo}/replies/set-data`, replyData)
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

async function $removeReply (boardNo, boardReplyNo) {
  try {
    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)
    return axios.get(`/boards/${boardNo}/replies/${boardReplyNo}/deletion`)
  } catch (error) {
    console.error('Error deleting user tags:', error)
  }
}

export {
  $getBoardList, $getBoardDetail, $editBoard, $removeBoard,
  getReplyList, $addBoard, $addReply, $editReply, $removeReply
}
