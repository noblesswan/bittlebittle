<template>
   <div class="content"> <!-- 수정 -->
     <p class="title">커뮤니티</p>
     <div class="search-container">
       <select v-model="searchOption" style="margin-right: 10px;">
         <option value="noticeTitle">제목</option>
         <option value="nickname">작성자</option>
         <option value="noticeContent">내용</option>
       </select>
       <input v-model="searchText" placeholder="검색어를 입력하세요" />
       <button @click="filterNoticeList">검색</button>
     </div>
     <table id="noticeList">
      <thead>
         <tr>
           <th>No</th>
           <th style="width: 380px;">제목</th>
           <th>작성자</th>
           <th>작성일</th>
         </tr>
       </thead>
       <tbody>

        <template v-if="state.noticeList.length">
  <tr
    v-for="item in state.noticeList"
    :key="item.noticeNo"
    @click="noticeDetail(item.noticeNo)"
    id="table-hover"
  >
    <td class="bno">{{ item.noticeNo }}</td>
    <td>{{ item.noticeTitle }}</td>
    <td>{{ item.nickname }}</td>
    <td>{{ item.createDate }}</td>
  </tr>
</template>

         <template v-else>
           <tr>
             <td colspan="4">게시글이 존재하지 않습니다.</td>
           </tr>
         </template>
       </tbody>
     </table>

     <!-- Add the 'Delete Selected' button next to the 'Write' button -->
<div class="write-button-container">
  <button id="write-button" @click="moveCreateBoard">작성하기</button>
</div>

</div> <!-- 수정 -->
</template>

<style scoped>
    .search-container {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }

  .search-container select {
    height: 30px;
    font-size: 14px;
    padding: 0 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    margin-right: 10px;
  }

  .search-container input[type="text"] {
    height: 30px;
    font-size: 14px;
    padding: 0 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    flex: 1;
  }

  .search-container button {
    height: 30px;
    font-size: 14px;
    padding: 0 10px;
    border-radius: 5px;
    background-color: orange;
    border: none;
    color: #fff;
    cursor: pointer;
  }

  /* 게시글 목록 스타일링 */
  #noticeList {
    width: 100%;
    border-collapse: collapse;
    table-layout: fixed;
  }

  #noticeList th {
    padding: 10px;
    background-color: #333;
    color: #fff;
    text-align: center;
    font-size: 16px;
  }

  #noticeList td {
    padding: 10px;
    border-bottom: 1px solid #ccc;
    font-size: 14px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }

  #noticeList td.bno {
    width: 50px;
    text-align: center;
  }

  #noticeList tbody tr:hover {
    background-color: #f5f5f5;
  }

  /* 글쓰기 버튼 스타일링 */
  .write-button-container {
    text-align: center;
    margin-top: 20px;
  }

  #write-button {
    height: 40px;
    font-size: 16px;
    padding: 0 20px;
    border-radius: 5px;
    background-color: orange;
    border: none;
    color: #fff;
    cursor: pointer;
  }
</style>

<script>
import { ref, onMounted } from 'vue'
import { $getNoticeList } from '@/api/notice'
// import router from '@/router'
import { useRouter } from 'vue-router'

export default {
  name: 'AdminNoticeListComp',
  setup () {
    const selectAll = ref(false)

    const toggleAllCheckboxes = () => {
      state.value.noticeList.forEach(item => {
        item.checked = selectAll.value
      })
    }

    const deleteSelected = () => {
      state.value.noticeList = state.value.noticeList.filter(item => !item.checked)
    }

    const theadList = [
      '글번호', '글제목', '작성일', '작성자'
    ]

    const state = ref({ noticeList: [] })

    const setNoticeList = () => {
      $getNoticeList('/api/notices')
        .then(res => {
          console.log(res.data)
          state.value.noticeList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    }

    const noticeDetail = (noticeNo) => {
      router.push(`/admin/notices/${noticeNo}`)
    }

    const searchOption = ref('noticeTitle')
    const searchText = ref('')

    const filterNoticeList = () => {
      if (searchText.value.trim() === '') {
        setNoticeList()
      } else {
        const filteredList = state.value.noticeList.filter((item) =>
          item[searchOption.value].toLowerCase().includes(searchText.value.toLowerCase())
        )
        state.value.noticeList = filteredList
      }
    }

    const router = useRouter()
    function moveCreateBoard () {
      router.push('/admin/notices/addition')
    }


    onMounted(() => {
      setNoticeList()
    })

    return {
      theadList,
      state,
      noticeDetail,
      searchOption,
      searchText,
      filterNoticeList,
      selectAll, // 추가
      toggleAllCheckboxes, // 추가
      deleteSelected, // 추가,
      moveCreateBoard
    }
  }
}
</script>
