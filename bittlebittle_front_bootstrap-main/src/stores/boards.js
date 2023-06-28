import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useBoardStore = defineStore('board', () => {
  // 로그인 시 header 에 전달된 jwt 와 refrechindex 정보를 전역에 저장
  const baordInfo = ref(null)

  const bottleInfo = ref(null)

  const getBoardInfo = computed(() => bottleInfo.value)

  function setBoardInfo (board) {
    baordInfo.value = board
  }

  const getBottleInfo = computed(() => bottleInfo.value)

  function setBottleInfo (board) {
    bottleInfo.value = board
  }


  return { getBoardInfo, setBoardInfo, getBottleInfo, setBottleInfo }
})
