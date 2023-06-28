<template>
  <div class="bright-section detail-view">
    <table id="list">
      <thead>
        <tr>
          <th colspan="2">제목</th>
          <th colspan="2">{{ notice.noticeTitle }}</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td colspan="2">작성자</td>
          <td colspan="2">{{ notice.nickname }}</td>
        </tr>
        <tr>
          <td colspan="2">작성일</td>
          <td colspan="2">{{ notice.createDate }}</td>
        </tr>
        <tr>
          <td colspan="4" class="notice-content" style="min-height: 300px">
            {{ notice.noticeContent }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <template v-if="loginUser != null && loginUser.userNo == notice.userNo">
    <div class="button-container">
      <button class="button-edit" @click="editNotice">수정</button>
      <button class="button-remove" @click="removeNotice">삭제</button>
    </div>
  </template>
</template>

<script>
import { $getNoticeDetail, $removeNotice } from '@/api/notice'
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/users'
import { useNoticeStore } from '@/stores/notices'

export default {
  name: 'AdminNoticeDetailComp',
  setup () {
    // Add this code inside the `setup()` function

    const loginUser = useUserStore().getLoginUserInfo

    const notice = ref({})

    const route = useRoute()
    const setNoticeDetail = () => {
      const noticeNo = route.params.noticeNo
      $getNoticeDetail(noticeNo)
        .then((res) => {
          notice.value = res.data
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    }

    const router = useRouter()
    // 수정창 이동 + 현재 데이터를 router 를 통해 전송
    function editNotice () {
      useNoticeStore().setNoticeInfo(notice.value)
      router.push({ name: 'AdminNoticeEditComp' })
    }

     function removeNotice () {
      $removeNotice(notice.value.noticeNo)
        .then(res => {
          console.log(res.data)
          router.push('/admin/notices')
        })
        .catch(err => console.log(err))
    }

    onMounted(() => {
      setNoticeDetail()
    })

    // Add this code inside the `setup()` function
    const newComment = ref('')

    return {
      notice,
      loginUser,
      editNotice,
      removeNotice
    }
  }
}
</script>

<style scoped>
/*디테일뷰 관련*/
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

.notice-content {
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
  
}

.button-remove {
  background-color: red;
  color: white;
  margin-left: 10px;
  border: none;
 
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
