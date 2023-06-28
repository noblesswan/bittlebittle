<template>
  <div class="container">
    <div class="admin-container">
      <h1>관리자 회원관리</h1>
      <div class="table-container">
        <table>
          <thead>
            <tr>
              <th><input type="checkbox" /></th>
              <th>회원번호</th>
              <th>이름</th>
              <th>이메일</th>
              <th>전화번호</th>
              <th>닉네임</th>
              <th>아이디</th>
              <th>탈퇴여부</th>
              <th>관리자여부</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in paginatedItems" :key="item.userNo">

        <td><input type="checkbox" v-model="item.checked" /></td>
        <td>{{ item.userNo }}</td>
        <td>{{ item.userName }}</td>
        <td>{{ item.email }}</td>
        <td>{{ item.phone }}</td>
        <td>{{ item.nickname }}</td>
        <td>{{ item.userId }}</td>
        <td>{{ item.status }}</td>
        <td>{{ item.adminYn }}</td>
      </tr>
          </tbody>
        </table>
      </div>

      <div v-if="memberList.length === 0 && searchExecuted" class="no-results">
  검색 결과가 없습니다.
</div>

      <div class="search-container">
  <label>
    검색 기준:
    <select v-model="searchCriteria">
      <option value="userName">이름</option>
      <option value="email">이메일</option>
      <option value="phone">전화번호</option>
    </select>
  </label>
  <label>
    검색어: <input type="text" v-model="searchKeyword" />
  </label>
  <button @click="searchSelected">검색</button>
</div>

      <div class="pagination">
    <button
      v-for="page in totalPages"
      :key="page"
      @click="setCurrentPage(page)"
      :class="{ active: currentPage === page }"
    >
      {{ page }}
    </button>
</div>

      <div class="button-container">
        <button @click="deleteSelected">선택 삭제</button>
        <button @click="openModal">수정</button>
      </div>

<!-- Modal -->
<div class="modal" :class="{ 'is-active': isModalOpen }">
  <div class="modal-background"></div>
  <div class="modal-card">
    <header class="modal-card-head">
      <p class="modal-card-title">회원 정보 수정</p>
      <button class="delete" aria-label="close" @click="closeModal"></button>
    </header>
    <section class="modal-card-body">
  <!-- User form inputs -->
</section>
    <footer class="modal-card-foot">
      <button class="button is-success" @click="updateUsermodal">저장</button>
      <button class="button" @click="closeModal">취소</button>
    </footer>
  </div>
</div>
    </div>

  </div>
  </template>

<script>
import { ref, computed, onMounted } from 'vue'
// user.js 파일에서 $getUser 함수를 가져옵니다.
import { $getUser, $searchUsers, $deleteUsers, $updateUsermodal } from '@/api/user.js'
import { getJsonAxiosInstance } from '@/api'
import { useUserStore } from '@/stores/users'

export default {
  name: 'AdminManaging',
  setup () {
    const currentPage = ref(1)
    const itemsPerPage = ref(10)
    const totalItems = ref(100) // 전체 회원 수를 설정하십시오. 실제 데이터에 따라 변경됩니다.
    const memberList = ref([]) // 회원 목록 데이터입니다. 실제 데이터에 따라 변경됩니다.
    const searchExecuted = ref(false)
    // 검색 기준 및 검색어를 저장하는 ref
    const searchCriteria = ref('')
    const searchKeyword = ref('')

    const isModalOpen = ref(false)
    const selectedUser = ref(null)

    const user = useUserStore()
    const axios = getJsonAxiosInstance(user.getLoginUserInfo)

    // 회원조회
    async function getUsers () {
      try {
        const response = await axios.get(`/api/admin/users?userNo=${user.getLoginUserInfo.userNo}`)
        if (response.status === 200) {
          memberList.value = response.data
          totalItems.value = response.data.length
        }
      } catch (error) {
        console.error('Error fetching member list:', error)
      }
    }

    const openModal = () => {
      const checkedItems = memberList.value.filter(item => item.checked)
      if (checkedItems.length !== 1) {
        alert('수정할 회원을 하나만 선택해주세요.')
        return
      }
      selectedUser.value = { ...checkedItems[0] }
      isModalOpen.value = true
    }

    const closeModal = () => {
      isModalOpen.value = false
    }

    const updateUsermodal = async () => {
      try {
        await $updateUsermodal(selectedUser.value)
        alert('회원 정보가 수정되었습니다.')
        closeModal()
        await fetchUserList()
      } catch (error) {
        console.error('Error updating user:', error)
      }
    }

    const searchSelected = async () => {
      try {
        const response = await $searchUsers({
          searchCriteria: searchCriteria.value,
          searchKeyword: searchKeyword.value
        })

        if (response.status === 200) {
          memberList.value = response.data
          totalItems.value = response.data.length
          setCurrentPage(1)
        } else {
          memberList.value = []
          totalItems.value = 0
        }
        searchExecuted.value = true
      } catch (error) {
        console.error('Error searching users:', error)
      }
    }

    // 선택 삭제 기능
    const deleteSelected = async () => {
      const checkedItems = memberList.value.filter(item => item.checked)

      if (checkedItems.length === 0) {
        alert('삭제할 회원을 선택해주세요.')
        return
      }

      try {
        await $deleteUsers(checkedItems.map(item => item.userNo))
        alert('선택된 회원이 삭제되었습니다.')
        await fetchUserList()
      } catch (error) {
        console.error('Error deleting users:', error)
      }
    }

    const fetchUserList = async () => {
      try {
        const response = await $getUser()
        if (response.status === 200) {
          memberList.value = response.data
          totalItems.value = response.data.length
        }
      } catch (error) {
        console.error('Error fetching user list:', error)
      }
    }

    const totalPages = computed(() => Math.ceil(totalItems.value / itemsPerPage.value))

    const paginatedItems = computed(() => {
      const start = (currentPage.value - 1) * itemsPerPage.value
      const end = start + itemsPerPage.value
      return memberList.value.slice(start, end)
    })

    const setCurrentPage = (page) => {
      currentPage.value = page
    }

    onMounted(() => {
      getUsers()
    })

    return {
      currentPage,
      itemsPerPage,
      totalItems,
      memberList,
      totalPages,
      paginatedItems,
      setCurrentPage,
      searchCriteria,
      searchKeyword,
      searchSelected,
      searchExecuted,
      deleteSelected,
      updateUsermodal,
      openModal,
      closeModal,
      selectedUser,
      isModalOpen
    }
  }
}
</script>

  <style scoped>
  .admin-container {
    font-family: 'Arial', sans-serif;
    background-color: white;
    padding: 20px;
  }

  h1 {
    font-size: 32px;
    font-weight: bold;
    color: orange;
    margin-bottom: 20px;
  }

  .table-container {
    width: 100%;
    overflow-x: auto;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
  }

  td {
    text-align: left;
    padding: 10px;
    border: 1px solid #cccccc;
    color: black;
  }

  th {
    text-align: left;
    padding: 10px;
    border: 1px solid #cccccc;
    color: white;
    font-weight: bold;
    background-color: #333;
  }
/*
  tbody tr:nth-child(even) {
    background-color: #f8f8f8;
  }
*/
  .search-container {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
  }

  .search-container input {
    padding: 5px;
  }

  .button-container {
    display: flex;
    justify-content: flex-end;
  }

  button {
    padding: 10px 20px;
    font-size: 14px;
    color: #ffffff;
    background-color: orange;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    outline: none;
    margin-left: 10px;
  }
  </style>
