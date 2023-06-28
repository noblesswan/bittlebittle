import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useNoticeStore = defineStore('notice', () => {
  const noticeInfo = ref(null)

  const getNoticeInfo = computed(() => noticeInfo.value)

  function setNoticeInfo (notice) {
    noticeInfo.value = notice
  }

  return { getNoticeInfo, setNoticeInfo }
})
