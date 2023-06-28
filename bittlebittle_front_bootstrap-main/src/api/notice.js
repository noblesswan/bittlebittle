import { getJsonAxiosInstance } from './index'
import { useUserStore } from '@/stores/users'


function $getNoticeList () {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get('/api/notices')
}

function $getNoticeDetail (noticeNo) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`/api/notices/${noticeNo}`)
}

function $editNotice (noticeNo, noticeData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post(`/api/admin/notices/${noticeNo}/set-data`, noticeData)
}

function $addNotice (noticeData) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.post('/api/admin/notices', noticeData)
}

function $removeNotice (noticeNo) {
  const user = useUserStore()
  const axios = getJsonAxiosInstance(user.getLoginUserInfo)
  return axios.get(`/api/admin/notices/${noticeNo}/deletion`)
}

export { $getNoticeList, $getNoticeDetail, $editNotice, $addNotice, $removeNotice }
