<template>
  <div class="my-info">
    <h6>나의 등록정보</h6>
    <table class="info-table">
  <thead>
    <tr>
      <th>항목</th>
      <th>내용</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>이름</td>
      <td>
        <input type="text" id="name" v-model="name" :disabled="!editMode" />
      </td>
    </tr><br>
    <template v-if="editMode">
      <tr>
        <td>현재 비밀번호</td>
        <td>
          <input type="password" v-model="myUserInfo.userPwd" placeholder="비밀번호" />
          <p v-if="pwdErrorMsg" class="error-msg">{{ pwdErrorMsg }}</p>
        </td>
      </tr><br>
      <tr>
        <td>비밀번호 변경</td>
        <td>
          <input type="password" id="password-confirm" v-model="myUserInfo.newUserPwd" placeholder="비밀번호 확인" />
          <p v-if="pwdConfirmErrorMsg" class="error-msg">{{ pwdConfirmErrorMsg }}</p>
        </td>
      </tr><br>
      <tr>
        <td>비밀번호 변경 재확인</td>
        <td>
          <input type="password" id="password-confirm" v-model="myUserInfo.newChkPwd" placeholder="비밀번호 확인" />
          <p v-if="pwdConfirmErrorMsg" class="error-msg">{{ pwdConfirmErrorMsg }}</p>
        </td>
      </tr><br>
    </template>
    <tr>
      <td>닉네임</td>
      <td>
        <input type="text" id="nickname" v-model="nickname" :disabled="!editMode" />
      </td>
    </tr><br>
    <tr>
      <td>이메일</td>
      <td>
        <input type="email" id="email" v-model="email" :disabled="!editMode" />
      </td>
    </tr><br>
    <tr>
      <td>전화번호</td>
      <td>
        <input type="text" id="phone" v-model="phone" :disabled="!editMode" />
      </td>
    </tr><br>
    <tr>
      <td colspan="2" style="text-align: center;">
        <button @click="updateInfo" style="margin-right: 10px;">수정</button>
        <button @click="editModeClose">취소</button>
      </td>
    </tr>
  </tbody>
</table>
<br><br><br><br>
    <!-- 내가 작성한 리뷰 및 댓글 테이블 -->
    <h6>내가 설정한 태그</h6>
      <template v-for="tagType, index in tagTypeList" :key="index">
        <div class="tag-group"></div>
          <strong>{{ tagType.tagTypeName }} : </strong>
        <div>
          <template v-for="tag, index in tagList" :key="index">
              <button class="selected" v-if="tag.keyTypeNo == tagType.tagTypeNo">
                {{ tag.tagName }}
              </button>
          </template>
        </div>
      </template>
    <br><br><br>
    <router-link :to="{ name: 'TagRegisterComp' }">태그 설정하러 가기</router-link>

<br><br><br><br>
    <!-- 내가 작성한 리뷰 및 댓글 테이블 -->
    <h6>내가 작성한 리뷰</h6>
    <table class="reviews-table">
        <thead>
          <tr>
            <th><input type="checkbox" @change="selectAll1" v-model="allSelected1" /></th>
            <th>리뷰 제목</th>
          </tr>
        </thead>
        <tbody>
          <!-- 내가 작성한 리뷰 테이블 데이터 -->
          <!-- 실제 데이터를 가져와서 반복문으로 채우십시오 -->
          <tr v-for="(review, index) in reviews" :key="`review-${index}`">
            <td><input type="checkbox" v-model="review.selected" /></td>
            <td>{{ review.reviewTitle }}</td>
        </tr>
        </tbody>
      </table>
        <button @click="deleteSelected1">리뷰삭제</button>

        <br><br><br><br>
    <h6>내가 작성한 댓글</h6>
    <table class="comments-table">
      <thead>
        <tr>
          <th><input type="checkbox" @change="selectAll2" v-model="allSelected2" /></th>
          <th>댓글 내용</th>
        </tr>
      </thead>
      <tbody>
        <!-- 내가 작성한 댓글 테이블 데이터 -->
        <!-- 실제 데이터를 가져와서 반복문으로 채우십시오 -->
        <tr v-for="(comment, index) in comments" :key="`comment-${index}`">
          <td><input type="checkbox" v-model="comment.selected" /></td>
          <td>{{ comment.replyContent }}</td>
      </tr>
      </tbody>
    </table>
    <button @click="deleteSelected2">댓글삭제</button>

    <br><br><br><br>
    <div class="withdraw-btn-container">
      <button class="btn btn-danger btn-sm" @click="withdraw">회원탈퇴</button>
    </div>
    <br><br><br><br>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useUserStore } from '@/stores/users'
import { $getUser, $editUser, $removeUser, $getReviews, $getReply } from '@/api/user'
import { $getUserTags } from '@/api/tag'

export default {
  name: 'UserMyPageComp',
  setup () {
    const userStore = useUserStore()
    const userInfo = userStore.getLoginUserInfo

    const userNo = userInfo.userNo

    const name = ref('')
    const nickname = ref('')
    const email = ref('')
    const phone = ref('')
    const editMode = ref(false)
    const reviews = ref({})
    const comments = ref({})
    const allSelected1 = ref(false)
    const allSelected2 = ref(false)
    const myUserInfo = {
      userPwd: '',
      newUserPwd: '',
      newChkPwd: ''
    }
    const user = ref({})

    const editModeToggle = () => {
      editMode.value = !editMode.value
    }

    // 회원 탈퇴 메소드 구현
    const withdraw = async () => {
      try {
        const response = await $removeUser(userNo)
        if (response.status === 200) {
          console.log('회원 탈퇴가 완료되었습니다.')
          // 로그아웃 처리 및 홈으로 이동 등 필요한 작업을 수행합니다.
          userStore.logout() // 회원 탈퇴 후 로그아웃 처리
        } else {
          console.log('회원 탈퇴에 실패했습니다.')
        }
      } catch (error) {
        console.error('회원 탈퇴 중 에러가 발생했습니다:', error)
      }
    }

    // 여기부터 deleteSelected 구현
    const deleteSelected1 = async () => {
      const selectedReviews = reviews.value.filter(review => review.selected)

      try {
        for (const review of selectedReviews) {
          await $deleteReview(review.id)
        }

        // 삭제가 완료되면 리뷰 목록을 다시 가져옵니다.
        fetchReviews()
      } catch (error) {
        console.error(error)
      }
    }

    const deleteSelected2 = async () => {
      const selectedComments = comments.value.filter(comment => comment.selected)

      try {
        for (const comment of selectedComments) {
          await $deleteComment(comment.id)
        }

        // 삭제가 완료되면 댓글 목록을 다시 가져옵니다.
        fetchComments()
      } catch (error) {
        console.error(error)
      }
    }

    const selectAll1 = () => {
      for (const review of reviews.value) {
        review.selected = allSelected1.value
      }
    }

    const selectAll2 = () => {
      for (const comment of comments.value) {
        comment.selected = allSelected2.value
      }
    }

    const fetchUser = async () => {
      try {
        const response = await $getUser(userNo)
        user.value = response.data

        name.value = user.value.userName
        nickname.value = user.value.nickname
        email.value = user.value.email
        phone.value = user.value.phone
      } catch (error) {
        console.error(error)
      }
    }

    const updateInfo = async () => {
      if (!editMode.value) {
        name.value = user.value.userName
        nickname.value = user.value.nickname
        email.value = user.value.email
        phone.value = user.value.phone
      } else {
        try {
          const updatedInfo = {
            name: name.value,
            nickname: nickname.value,
            email: email.value,
            phone: phone.value
          }
          // $updateUser 함수를 호출하여 서버에 변경된 정보를 전송
          const response = await $editUser(userNo, updatedInfo)

          if (response.status === 200) {
            console.log('정보가 성공적으로 업데이트되었습니다.')
            fetchUser()
          } else {
            console.log('정보 업데이트에 실패했습니다.')
          }
        } catch (error) {
          console.error('정보 업데이트 중 에러가 발생했습니다:', error)
        }
      }
      editModeToggle()
    }

    const fetchReviews = async () => {
      // API를 호출하여 리뷰 데이터를 가져옵니다.
      try {
        const response = await $getReviews()
        reviews.value = response.data
      } catch (error) {
        console.error(error)
      }
    }

    const fetchComments = async () => {
      // API를 호출하여 댓글 데이터를 가져옵니다.
      try {
        const response = await $getReply()
        comments.value = response.data
      } catch (error) {
        console.error(error)
      }
    }

    function editModeClose () {
      if (editMode.value) {
        editModeToggle()
      }
    }

    const tagList = ref({})
    const tagTypeList = ref({})


    // 회원태그 조회
    function getUserTags () {
      $getUserTags(userNo)
        .then(res => {
          console.log(res.data)
          tagList.value = res.data.tagList
          tagTypeList.value = res.data.tagTypeList
          userStore.setUserTagsInfo(tagList)
        })
        .catch(err => console.log(err))
      }

    onMounted(() => {
      fetchUser()
      fetchReviews()
      fetchComments()
      getUserTags()
    })

    return {
      name,
      nickname,
      email,
      phone,
      editMode,
      updateInfo,
      reviews,
      comments,
      fetchReviews,
      fetchComments,
      deleteSelected1,
      deleteSelected2,
      allSelected1,
      allSelected2,
      selectAll1,
      selectAll2,
      withdraw,
      myUserInfo,
      editModeToggle,
      editModeClose,
      tagList,
      tagTypeList
    }
  }
}
</script>

<style scoped>
.my-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.input-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.withdraw-button {
position: fixed;
bottom: 20px;
right: 20px;
padding: 10px 20px;
background-color: red;
color: white;
border: none;
border-radius: 5px;
cursor: pointer;
}

.withdraw-button:hover {
background-color: darkred;
}

label {
  margin-bottom: 5px;
}

input[type='text'],
input[type='email'] {
  padding: 5px;
  width: 100%;
}

button {
  padding: 5px 10px;
}

.my-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.info-table,
.reviews-table,
.comments-table,
.review-details-table {
  width: 100%;
  margin-bottom: 20px;
  border-collapse: collapse;
  text-align: center;
}

.info-table th,
.info-table td,
.reviews-table th,
.reviews-table td,
.comments-table th,
.comments-table td,
.review-details-table th,
.review-details-table td {
  padding: 10px;
  border: 1px solid #ccc;
}

.info-table th,
.reviews-table th,
.comments-table th,
.review-details-table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.info-table td:first-child,
.reviews-table td:first-child,
.comments-table td:first-child {
  width: 30%;
}

.reviews-table td:last-child,
.comments-table td:last-child,
.review-details-table td:last-child {
  width: 70%;
}

.btn-danger {
  color: #fff;
  background-color: #dc3545;
  border-color: #dc3545;
}

.btn-danger:hover {
  color: #fff;
  background-color: #c82333;
  border-color: #bd2130;
}

.btn-sm {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem;
  line-height: 1.5;
  border-radius: 0.2rem;
}

.withdraw-btn-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.selected {
  background-color: rgb(250, 150, 0);
  color: white;
  border: 0px;
}

</style>
