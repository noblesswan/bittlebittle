<template>
     <div class="search-container">
       <select v-model="searchOption" style="margin-right: 10px;">
         <option value="boardTitle">제목</option>
         <option value="nickname">작성자</option>
         <option value="boardContent">내용</option>
       </select>
       <input v-model="searchText" placeholder="검색어를 입력하세요" />
       <button @click="filterBoardList">검색</button>
     </div>
     <table id="boardList">
      <thead>
         <tr>
           <th>No</th>
           <th style="width: 380px;">제목</th>
           <th>작성자</th>
           <th>작성일</th>
         </tr>
       </thead>
       <tbody>
         <template v-if="state.boardList.length">
           <tr
             v-for="item in state.boardList"
             :key="item"
             @click="boardDetail(item.boardNo)"
             id="table-hover"
           >
             <td class="bno">{{ item.boardNo }}</td>
             <td>{{ item.boardTitle }}</td>
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
     <template v-if="loginUser != null">
      <div class="write-button-container">
        <button id="write-button" @click="moveCreateBoard">작성하기</button>
      </div>
    </template>
</template>

<script>
import { ref, onMounted } from 'vue'
import { $getBoardList } from '@/api/board'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/users'


export default {
  name: 'BoardListComp',
  setup () {
    const theadList = [
      '글번호', '글제목', '작성일', '작성자'
    ]

    const state = ref({ boardList: [] })

    const setBoardList = () => {
      $getBoardList()
        .then(res => {
          console.log(res.data)
          state.value.boardList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    }

    const boardDetail = (boardNo) => {
      router.push(`/boards/${boardNo}`)
    }

    const searchOption = ref('boardTitle')
    const searchText = ref('')

    const filterBoardList = () => {
      if (searchText.value.trim() === '') {
        setBoardList()
      } else {
        const filteredList = state.value.boardList.filter((item) =>
          item[searchOption.value].toLowerCase().includes(searchText.value.toLowerCase())
        )
        state.value.boardList = filteredList
      }
    }

    // 라우터 전환
    const router = useRouter()
    function moveCreateBoard () {
      router.push('/boards/addition')
    }

    // 로그인 유저가 존재하는지 유무 확인
    const loginUser = useUserStore().getLoginUserInfo

    onMounted(() => {
      setBoardList()
    })

    return {
      theadList,
      state,
      boardDetail,
      searchOption,
      searchText,
      filterBoardList,
      moveCreateBoard,
      loginUser
    }
  }
}
</script>



<style scoped>
  /* 검색창 스타일링 */
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
  #boardList {
    width: 100%;
    border-collapse: collapse;
    table-layout: fixed;
  }

  #boardList th {
    padding: 10px;
    background-color: #333;
    color: #fff;
    text-align: center;
    font-size: 16px;
  }

  #boardList td {
    padding: 10px;
    border-bottom: 1px solid #ccc;
    font-size: 14px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }

  #boardList td.bno {
    width: 50px;
    text-align: center;
  }

  #boardList tbody tr:hover {
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
