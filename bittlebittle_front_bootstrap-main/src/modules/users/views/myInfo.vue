<template>
    <div class="my-info">
      <h2>나의 등록정보</h2>
      <div class="info-edit">
      <div class="input-group">
        <label for="name">이름</label>
        <input type="text" id="name" v-model="name" :disabled="!editMode" />
      </div>
      <div class="input-group">
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" v-model="nickname" :disabled="!editMode" />
      </div>
      <div class="input-group">
        <label for="email">이메일</label>
        <input type="email" id="email" v-model="email" :disabled="!editMode" />
      </div>
      <div class="input-group">
        <label for="phone">전화번호</label>
        <input type="text" id="phone" v-model="phone" :disabled="!editMode" />
      </div>
      <button @click="updateInfo">수정</button>
    </div>

      <!-- 내가 작성한 리뷰 및 댓글 테이블 -->
      <h3>내가 작성한 리뷰</h3>
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
      <td>{{ review.title }}</td>
  </tr>
  </tbody>
</table>
    <button @click="deleteSelected1">리뷰삭제</button>

    <h3>내가 작성한 댓글</h3>
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
      <td>{{ comment.content }}</td>
  </tr>
  </tbody>
</table>
<button @click="deleteSelected2">댓글삭제</button>

    <h3>내가 작성한 리뷰 상세보기</h3>
    <table class="review-details-table">
      <thead>
        <tr>
          <th>상품</th>
          <th>제목</th>
          <th>평점</th>
        </tr>
      </thead>
      <tbody>
        <!-- 내가 작성한 리뷰 상세보기 테이블 데이터 -->
        <!-- 실제 데이터를 가져와서 반복문으로 채우십시오 -->
      </tbody>
    </table>

    <button class="withdraw-button" @click="withdraw">회원탈퇴</button>

    </div>
  </template>

<script>
import { ref, onMounted, computed } from 'vue'
import { useUserStore } from '@/stores/users'
import { $getUser } from '@/api/user'

export default {
  name: 'myInfo',
  setup () {
    // 초기값 설정 (실제로는 SQL에서 가져온 값으로 설정해야 함)

    const userStore = useUserStore()
    const userInfo = computed(() => userStore.getLoginUserInfo.value)

    // const name = ref('홍길동')
    // const nickname = ref('gil-dong')
    // const email = ref('gildong@example.com')
    // const phone = ref('010-1234-5678')

    const name = ref('')
    const nickname = ref('')
    const email = ref('')
    const phone = ref('')
    const editMode = ref(false)
    const reviews = ref([])
    const comments = ref([])
    const allSelected1 = ref(false)
    const allSelected2 = ref(false)

    // 회원 탈퇴 메소드 구현
    const withdraw = async () => {
      try {
        const response = await $withdrawUser(userInfo.value.userNo)
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
        const response = await $getUser(userInfo.value.userNo)
        const user = response.data

        name.value = user.name
        nickname.value = user.nickname
        email.value = user.email
        phone.value = user.phone
      } catch (error) {
        console.error(error)
      }
    }

    // const updateInfo = () => {
    //   // SQL에 변경된 값 저장하는 코드 작성 필요
    //   console.log('변경된 정보:', { name: name.value, nickname: nickname.value, email: email.value, phone: phone.value })
    // }

    const updateInfo = async () => {
      if (!editMode.value) {
        name.value = ''
        nickname.value = ''
        email.value = ''
        phone.value = ''
      } else {
        try {
          const updatedInfo = {
            name: name.value,
            nickname: nickname.value,
            email: email.value,
            phone: phone.value
          }
          // $updateUser 함수를 호출하여 서버에 변경된 정보를 전송
          const response = await $updateUser(userInfo.value.userNo, updatedInfo)

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
      editMode.value = !editMode.value
    }

    onMounted(() => {
      fetchUser()
      fetchReviews()
      fetchComments()
    })

    const fetchReviews = async () => {
      // API를 호출하여 리뷰 데이터를 가져옵니다.
      try {
        const response = await $getReviews(userInfo.value.userNo)
        reviews.value = response.data
      } catch (error) {
        console.error(error)
      }
    }

    const fetchComments = async () => {
      // API를 호출하여 댓글 데이터를 가져옵니다.
      try {
        const response = await $getReply(userInfo.value.userNo)
        comments.value = response.data
      } catch (error) {
        console.error(error)
      }
    }

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
      withdraw
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
  </style>
