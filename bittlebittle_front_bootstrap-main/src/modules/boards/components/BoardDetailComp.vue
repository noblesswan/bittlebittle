<template>
    <div class="bright-section detail-view">
      <table id="list">
        <thead>
          <tr>
            <th colspan="2">제목</th>
            <th colspan="2">{{ board.boardTitle }}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td colspan="2">작성자</td>
            <td colspan="2">{{ board.nickname}}</td>
          </tr>
          <tr>
            <td colspan="2">작성일</td>
            <td colspan="2">{{ board.createDate }}</td>
          </tr>
          <tr>
            <td colspan="4" class="board-content" style="min-height: 300px;">{{ board.boardContent }}</td>
          </tr>
        </tbody>
      </table>
      <template v-if="loginUser != null && loginUser.userNo == board.userNo">
          <div class="button-container">
          <button class="button-edit btn-sm" @click="editBoard">수정</button>
          <button class="button-remove btn-sm" @click="removeBoard()">삭제</button>
        </div>
      </template>
    </div>

    <br><br><br><br><br>

    <h6>댓글</h6>
    <div class="button-container"></div>
    <div class="bright-section comments">
        <table id="list">
          <thead>
            <tr>
              <th>작성자</th>
              <th>작성날짜</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <template v-for="(comment, index) in comments" :key="index">

                <tr>
                  <td>{{ comment.nickname }}</td>
                  <td>{{ comment.createDate }}</td>
                  <td>
                    <template v-if="loginUser != null && loginUser.userNo == board.userNo">
                      <!-- 수정 모드가 아니라면 -->
                      <template v-if="!editMode">
                        <div class="button-container">
                        <button class="button-edit btn-sm" @click="editModeToggles(comment)">수정</button>
                        <button class="button-remove btn-sm" @click="removeReply(comment.replyNo)">삭제</button>
                      </div>
                      </template>
                      <template v-else>
                        <div class="button-container">
                        <button class="button-edit btn-sm" @click="editReply()">저장</button>
                        <button class="button-remove btn-sm" @click="editMoveToggle()">취소</button>
                      </div>
                      </template>
                    </template>
                  </td>
                </tr>
                  <tr>
                    <!-- 수정 모드라면 -->
                    <template v-if="editMode && editReplyInfo.editReplyNo == comment.replyNo">
                      <input type="text" v-model="editReplyInfo.editReplyContent">
                    </template>
                    <template v-else>
                      <td colspan="3" class="comment-content">{{ comment.replyContent }}</td>
                    </template>
                  </tr>
                </template>
          </tbody>
        </table>
    </div>
    <br><br><br>
    <!-- Add this code right after the comments table and before the closing </div> tag -->
    <template v-if="loginUser != null">
      <div class="comment-form">
        <textarea v-model="newComment" class="comment-input" placeholder="댓글을 입력하세요..."></textarea>
        <button @click="addComment" class="comment-submit button-edit">댓글 작성</button>
      </div>
    </template>
</template>

<script>
import { $getBoardDetail, getReplyList, $addReply, $removeBoard, $editReply, $removeReply } from '@/api/board'
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/users'
import { useBoardStore } from '@/stores/boards'

export default {
  name: 'BoardDetailComp',
  setup () {
    // Add this code inside the `setup()` function
    const comments = ref([])
    const editMode = ref(false)
    const fetchComments = () => {
      const boardNo = route.params.boardNo
      console.log(boardNo)
      getReplyList(`/boards/${boardNo}/replies`)
        .then((res) => {
          comments.value = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    }

    const loginUser = useUserStore().getLoginUserInfo

    const board = ref({})

    const route = useRoute()
    const setBoardDetail = () => {
      const boardNo = route.params.boardNo
      $getBoardDetail(boardNo)
        .then((res) => {
          board.value = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    }

    const router = useRouter()
    // 수정창 이동 + 현재 데이터를 router 를 통해 전송
    function editBoard () {
      useBoardStore().setBoardInfo(board.value)
      router.push({ name: 'BoardEditComp' })
    }

    function removeBoard () {
      $removeBoard(board.value.boardNo)
        .then(res => {
          console.log(res.data)
          router.push('/boards')
        })
        .catch(err => console.log(err))
    }

    // 현재 수정창의 정보
    const editReplyInfo = ref({
      editReplyNo: '',
      editReplyContent: ''
    })

    function editModeToggles (comment) {
      editMode.value = !editMode.value
      editReplyInfo.value.editReplyNo = comment.replyNo
      editReplyInfo.value.editReplyContent = comment.replyContent
      console.log(editReplyInfo.value)
    }

    function editModeToggle () {
      editMode.value = !editMode.value
    }

    function editReply () {
      const editReplyData = {
        replyNo: editReplyInfo.value.editReplyNo,
        replyContent: editReplyInfo.value.editReplyContent
      }
      $editReply(board.value.boardNo, editReplyData)
        .then(res => {
          console.log(res.data)
          editModeToggle()
          fetchComments()
        })
        .catch(err => console.log(err))
    }

    function removeReply (replyNo) {
      $removeReply(board.value.boardNo, replyNo)
        .then(res => {
          console.log(res.data)
          fetchComments()
        })
        .catch(err => console.log(err))
    }

    const newComment = ref('')

    const addComment = () => {
      if (newComment.value.trim() === '') {
        alert('댓글을 입력하세요.')
        return
      }

      // Send the new comment to the API
      const reply = {
        userNo: loginUser.userNo,
        boardNo: board.value.boardNo,
        replyContent: newComment.value
      }
      $addReply(`/boards/${board.value.boardNo}/replies`, reply)
        .then(res => {
          if (res.data.request) {
            newComment.value = ''
            fetchComments()
          } else {
            alert('댓글 작성 중 오류가 발생했습니다.')
          }
        })
        .catch((err) => {
          console.log(err)
        })
    }

    onMounted(() => {
      setBoardDetail()
      fetchComments()
    })

    // Add 'newComment' and 'addComment' to the returned object
    return {
      board,
      comments,
      newComment,
      addComment,
      loginUser,
      editBoard,
      removeBoard,
      editReply,
      removeReply,
      editMode,
      editModeToggle,
      editModeToggles,
      editReplyInfo
    }
  }
}

</script>

<style scoped >
/*디테일뷰 관련*/
/* .container {
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
  background-color: transparent;
} */

.bright-section table {
  width: 100%;
  border-collapse: collapse;
}

.bright-section th,
.bright-section td {
  padding: 8px;
  text-align: left;
  border: 1px solid #cccccc;
}

.board-content {
  white-space: pre-wrap;
  min-height: 300px;
}

.bright-section {
  background-color: #ffffff;
}

/* .detail-view {
  padding-bottom: 20px;
  border-bottom: 1px solid #cccccc;
} */

.author-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.comments {
  margin-top: 20px;
}

.comment-list {
  margin-top: 10px;
}

.comment-item {
  padding: 10px;
  border-bottom: 1px solid #cccccc;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}

.comments h2 {
  font-size: 1.2em;
}

.button-edit {
  background-color: orange;
  color: white;
  margin-left: 10px;
  border: none;
  border-radius: 20px;
}

.button-remove {
  background-color: red;
  color: white;
  margin-left: 10px;
  border: none;
  border-radius: 20px;
}

.button-container {
  text-align: right;
}

/* 수정된 footer 스타일 추가 */
#footer {
  background-color: transparent;
}

.comment-input {
  width: 100%;
  min-height: 80px;
  margin-bottom: 10px;
  resize: none;
  border: 1px solid #cccccc;
  border-radius: 4px;
  padding: 10px;
}

.comment-submit {
  background-color: orange;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 1em;
  font-weight: bold;
  transition: background-color 0.3s;
}

.comment-submit:hover {
  background-color: orange;
}

.button-container {
  display: flex;
  justify-content: flex-end;
  /* margin-bottom: 20px; */
}

.custom-button {
  background-color: orange;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 1em;
  font-weight: bold;
  transition: background-color 0.3s;
  margin-left: 10px;
}

.custom-button:hover {
  background-color: orange;
}

  #list {
    width: 100%;
    border-collapse: collapse;
    table-layout: fixed;
  }

  #list th {
    padding: 10px;
    background-color: #333;
    color: #fff;
    text-align: center;
    font-size: 16px;
  }

  #list td {
    padding: 10px;
    border-bottom: 1px solid #ccc;
    font-size: 14px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }

  #list td.bno {
    width: 50px;
    text-align: center;
  }

  #list tbody tr:hover {
    background-color: #f5f5f5;
  }
</style>
