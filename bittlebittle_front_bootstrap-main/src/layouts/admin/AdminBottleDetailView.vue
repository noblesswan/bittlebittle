<template>
  <div class="bottle-detail-view" v-if="bottle">
    <button class="btn btn-primary" @click="showEditBottleModal()">보틀 정보 수정</button>
    <br><br>
    <div class="image-container">
      <img :src="getBottleImage(bottle.imgUrl, bottle.imgCusUrl)" style="max-width:350px" alt="보틀 이미지">
    </div>
    <div>보틀 번호: {{ bottle.bottleNo }}</div>
    <div>보틀 이름: {{ bottle.bottleName }}</div>
    <div>보틀 내용: {{ bottle.bottleContent }}</div>
    <div>보틀 브랜드: {{ bottle.bottleBrand }}</div>
    <div>보틀 도수: {{ bottle.bottleAbv }}</div>

    <!-- 태그 리스트 -->
    <div class="related-list">
      <div class="section-title">태그 리스트:</div>
          <div class="tag-box" v-for="tag in tagListByBottle" :key="tag.tagNo">{{ tag.tagName }}</div>
    </div>

<!-- bottle 수정 modal -->
<b-modal v-model="editBottleModal" title="보틀 정보 수정" v-if="editBottleModal">
  <div class="modal-content" style="padding: 20px;">
    <div class="modal-header">
      <h5 class="modal-title">보틀 정보 수정</h5>
      <button type="button" class="close" aria-label="Close" @click="closeEditBottleModal()">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <form>
        <template v-if="bottle.imgUrl != ''">
          <img ref="addBottleImage" :src="getBottleImage()" style="max-width:350px" alt="이미지 파일">
        </template>
        <div class="form-group">
          <label for="bottleName">첨부 이미지</label>
          <input type="file" class="form-control" id="bottleName" @change="handleImageUpload">
        </div>
        <div class="form-group">
          <label for="bottleName">보틀 이름</label>
          <input type="text" class="form-control" id="bottleName" v-model="editBottleName">
        </div>
        <div class="form-group">
          <label for="bottleContent">보틀 내용</label>
          <textarea class="form-control" id="bottleContent" rows="3" v-model="editBottleContent"></textarea>
        </div>
        <div class="form-group">
          <label for="bottleBrand">보틀 브랜드</label>
          <input type="text" class="form-control" id="bottleBrand" v-model="editBottleBrand">
        </div>
        <div class="form-group">
          <label for="bottleAbv">보틀 도수</label>
          <input type="number" class="form-control" id="bottleAbv" v-model="editBottleAbv">
        </div>
      </form>
    </div>
    <div>
      <div v-for="tagType in tagTypeList" :key="tagType.tagTypeNo">
        <div>{{ tagType.tagTypeName }}</div>
        <div>
          <label v-for="tag in tagList.filter(tag => tag.keyTypeNo === tagType.tagTypeNo)" :key="tag.tagNo" class="tag-box">
            <input type="radio" :name="`tag-${tagType.tagTypeNo}`" :value="tag.tagNo" v-model="selectedTags[tagType.tagTypeNo-1]">
            {{ tag.tagName }}
          </label>
        </div>
      </div>
    </div>
  </div>
  <div class="btn-group">
    <button class="btn btn-secondary" @click="closeEditBottleModal()">취소</button>
    <button class="btn btn-primary" @click="editBottle()">저장</button>
  </div>
</b-modal>

    <!-- 리뷰 리스트 -->
    <div class="related-list">
      <div class="section-title">리뷰 리스트:</div>
      <ul>
        <li v-for="review in reviewList" :key="review.reviewNo" @click="showReviewModal(review)">
          {{ review.reviewTitle }}
        </li>
      </ul>
    </div>

    <!-- modal -->
    <b-modal v-model="reviewModal" title="리뷰 상세 내용" v-if="reviewModal">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">{{ selectedReview.reviewTitle }}</h5>
          <button type="button" class="close" aria-label="Close" @click="closeModal()">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <p class="text-muted">{{ selectedReview.createDate }}</p>
          <p>{{ selectedReview.reviewContent }}</p>
          <p>평점: {{ selectedReview.grade }}</p>
        </div>
      </div>
      <div class="d-flex justify-content-end">
        <b-button class="btn btn-delete" @click="deleteReview()">삭제</b-button>
      </div>

    <!-- replyList 출력 -->
    <ul class="list-unstyled">
      <li v-for="reply in replyList" :key="reply.replyNo">
        {{ reply.userNo }}&nbsp;&nbsp;&nbsp;&nbsp;{{ reply.createTime }}&nbsp;&nbsp;&nbsp;&nbsp;{{ reply.replyContent }}
        <button class="btn-primary.custom-button" @click="deleteReply(reply.replyNo)">삭제</button>
      </li>
    </ul>

    </b-modal>

  </div>

</template>

<script>
import { getFormAxiosInstance } from '@/api/index'
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/users.js'

export default {
  name: 'BottleDetailView',

  props: {
    bottleNo: {
      type: String,
      required: true
    }
  },

  setup (props) {
    const user = useUserStore()
    const axios = user.getLoginUserInfo ? getFormAxiosInstance(user.getLoginUserInfo) : getFormAxiosInstance('')

    const bottle = ref(null)
    const tagListByBottle = ref([])
    const reviewList = ref([])
    const reviewTitle = ref('')
    const reviewContent = ref('')
    const grade = ref(0)

    const getBottle = function (hhh) {
      let url = ''
      if (!hhh) {
        url = `/api/admin/bottles/${props.bottleNo}`
      } else {
        url = '/api/admin/bottles/' + hhh
      }
      axios.get(url)
        .then(res => {
          bottle.value = res.data.bottle
          tagListByBottle.value = res.data.tagListByBottle
          reviewList.value = res.data.reviewList
        })
        .catch(err => {
          console.log(err)
        })
    }

    const router = useRouter()

    onMounted(() => {
      getBottle()
    })
    // 보틀 이미지 불러오기
    function getBottleImage () {
      return `http://localhost:8080/bittlebittle/image?path=bottle&name=${bottle.value.imgCusUrl}`
    }

    // 이미지 첨부
    const addBottleImage = ref(null)
    const handleImageUpload = function (event) {
      addBottleImage.value = event.target.files[0]
    }

    // bottle 수정
    const editBottleName = ref('')
    const editBottleContent = ref('')
    const editBottleBrand = ref('')
    const editBottleAbv = ref(0)
    const editBottleModal = ref(false)
    const tagTypeList = ref([])
    const tagList = ref([])
    const selectedTags = ref([])

    const showEditBottleModal = function () {
      editBottleModal.value = true

      editBottleName.value = bottle.value.bottleName
      editBottleContent.value = bottle.value.bottleContent
      editBottleBrand.value = bottle.value.bottleBrand
      editBottleAbv.value = bottle.value.bottleAbv
      selectedTags.value = tagListByBottle.value.map(tag => tag.tagNo)

      axios.get('/api/admin/tagtypes')
        .then(res => {
          tagTypeList.value = res.data
        })
        .catch(err => {
          console.log(err)
        })

      axios.get('/api/admin/tags')
        .then(res => {
          tagList.value = res.data
        })
        .catch(err => {
          console.log(err)
        })
    }



    // 보틀 수정
    const editBottle = function () {
      const url = '/api/admin/bottles/set-data'

      const data = new FormData()
      data.append('bottleNo', bottle.value.bottleNo)
      data.append('bottleName', editBottleName.value)
      data.append('bottleContent', editBottleContent.value)
      data.append('bottleBrand', editBottleBrand.value)
      data.append('bottleAbv', editBottleAbv.value)
      data.append('tagNoList', selectedTags.value)
      if (bottle.value.imgUrl != null && bottle.value.imgUr !== '') {
        data.append('imgUrl', bottle.value.imgUrl)
      }
      if (bottle.value.imgCusUrl != null && bottle.value.imgCusUrl !== '') {
        data.append('imgCusUrl', bottle.value.imgCusUrl)
      }
      if (addBottleImage.value) {
        data.append('reupfile', addBottleImage.value)
      } else {
        data.append('reupfile', null)
      }

      axios.post(url, data)
        .then(res => {
          bottle.value = res.data.bottle
          tagListByBottle.value = res.data.tagListByBottle
          editBottleModal.value = false
          // 업로드 한 뒤에는 <input type="file" 의 value 를 null 로 수정
          addBottleImage.value = null
        })
    }

    const closeEditBottleModal = function () {
      editBottleModal.value = false
    }

    // Function to show the modal with review details
    const selectedReview = ref(null)
    const reviewModal = ref(false)
    const replyList = ref([])

    const showReviewModal = (review) => {
      axios.get(`/api/admin/bottles/${bottle.value.bottleNo}/reviews/${review.reviewNo}`)
        .then(res => {
          replyList.value = res.data.replyList
        })
        .catch(err => {
          console.log(err)
        })

      selectedReview.value = review
      reviewModal.value = true
    }

    const closeReviewModal = () => {
      selectedReview.value = null
      reviewModal.value = false
    }

    // 리뷰 삭제
    const deleteReview = function () {
      axios.get(`/api/admin/bottles/${bottle.value.bottleNo}/reviews/${selectedReview.value.reviewNo}/deletion`)
        .then(res => {
          reviewList.value = res.data
          reviewModal.value = false
        })
    }

    // 리플 삭제
    const deleteReply = function (replyNo) {
      axios.get(`/api/admin/bottles/${bottle.value.bottleNo}/reviews/${selectedReview.value.reviewNo}/replies/${replyNo}/deletion`)
        .then(res => {
          replyList.value = res.data
        })
    }

    return {
      bottle,
      tagListByBottle,
      reviewList,
      reviewTitle,
      reviewContent,
      grade,
      getBottle,
      selectedReview,
      reviewModal,
      showReviewModal,
      replyList,
      closeEditBottleModal,
      deleteReview,
      deleteReply,
      editBottleName,
      editBottleContent,
      editBottleBrand,
      editBottleAbv,
      editBottleModal,
      showEditBottleModal,
      editBottle,
      tagTypeList,
      tagList,
      selectedTags,
      closeReviewModal,
      user,
      handleImageUpload,
      getBottleImage
    }
  }
}
</script>

<style scoped>

  .bottle-detail-view {
    background-color: black;
    color: white;
    padding: 20px;
  }

  /* 각 섹션 제목 스타일 */
  .section-title {
    font-size: 1.2rem;
    margin-bottom: 10px;
    color: orange;
  }

  /* 관련 보틀/음식/태그/리뷰 리스트 스타일 */
  .related-list {
    border: 1px solid orange;
    border-radius: 5px;
    padding: 10px;
    margin-top: 10px;
    margin-bottom: 20px;
  }
  .related-list ul {
    list-style: none;
    margin: 0;
    padding: 0;
  }
  .related-list li {
    text-decoration: underline;
    text-decoration-color: orange;
    margin-bottom: 5px;
    color: white;
  }
  .related-list li:hover {
    text-decoration: underline;
    cursor: pointer;
  }

  .form-group {
      margin-bottom: 10px;
    }

  .review-form-control {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 2px solid black;
    border-radius: 5px;
  }

  .btn-primary {
  background-color: orange;
  border-color: orange;
  color: white;
}

  .tag-box {
  display: inline-block;
  background-color: #555;
  color: white;
  padding: 5px;
  border-radius: 5px;
  margin-right: 10px;
  margin-bottom: 4px;
}

  .modal-dialog {
    max-width: 80%;
    margin: 1.75rem auto;
  }

  .modal-content {
    background-color: #000;
    color: #ff9933;
    border: 3px solid #ff9933;
  }

  .modal-header {
    border-bottom: none;
  }

  .modal-title {
    color: #fff;
  }

  .modal-body {
    color: #fff;
  }

  .modal-body p {
    color: white;
  }

  .close {
    color: #ff9933;
  }

  .btn-edit, .btn-delete {
    color: #fff;
    border: 1px solid #ff9933;
  }

  .btn-primary.custom-button {
  background-color: orange;
  border-color: orange;
  color: white;
}
.btn-primary.custom-button:hover {
  background-color: white;
  border-color: orange;
  color: orange;
}

.form-control {
  height: 50px;
  width: 600px;
}

 input[type="text"],
  input[type="number"],
  textarea {
    border-color: orange;
    background-color: black;
    color: white;
  }
 .btn-group button {
    margin-right: 10px;
  }
/* modal 테두리 여백 */

</style>
