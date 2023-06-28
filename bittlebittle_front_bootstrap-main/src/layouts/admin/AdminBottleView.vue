<template>

  <router-link :to="{ name:'AdminAddBottleView' }">
            <button class="btn btn-primary">보틀 추가</button>
  </router-link>
  <br>
  <div>AdminBottleView</div>
  <div>
    <div>
   <div class="parent">
        <input type="text" class="form-control" v-model="keyword" placeholder="검색어를 입력하세요">
        <button class="btn btn-primary" @click="search">검색</button>
   </div>

    <table>
      <thead>
        <tr>
          <th>번호</th>
          <th>이름</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="bottle in bottles" :key="bottle.bottleNo">
          <td>{{ bottle.bottleNo }}</td>
          <td>
            <router-link :to="{ name:'AdminBottleDetailView', params : { bottleNo : bottle.bottleNo} }">
              {{ bottle.bottleName }}
            </router-link>
          </td>
          <td>
            <button class="delete-button" @click="deleteBottle(bottle.bottleNo)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script>
import { getFormAxiosInstance } from '@/api/index'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/users'

export default {
  name: 'AdminBottleView',

  setup () {
    const user = useUserStore()
    const axios = getFormAxiosInstance(user.getLoginUserInfo)

    const bottles = ref([])
    const favorites = ref([])
    const keyword = ref('') // 검색어 변수 선언

    onMounted(() => {
      axios.get('/api/bottles/all')
        .then(res => {
          bottles.value = res.data.bottle
          favorites.value = res.data.favorites
		    })
        .catch(err => {
          console.log(err)
        })
    })

    const deleteBottle = (bottleNo) => {
      axios.get(`/api/admin/bottles/${bottleNo}/deletion`)
        .then(res => {
          bottles.value = res.data
        })
        .catch(err => {
          console.log(err)
        })
    }

    // 이미지
    const addBottleImage = ref()
    const handleImageUpload = function (event) {
      addBottleImage.value = event.target.files[0]
    }

    // 검색

    const search = function () {
      axios.post('/api/bottles/all', {
        keyword: keyword.value
      })
        .then(res => {
          console.log('RESULT', res.data)
          this.bottles = res.data.bottle
        })
        .catch(err => {
          console.log('error', err)
        })
    }

    return {
      bottles,
      favorites,
      keyword,
      deleteBottle,
      search
    }
  }
}
</script>

<style scoped>
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

.delete-button {
  background-color: black; /* 검정 바탕 */
  color: white; /* 하얀색 글씨 */
  border: 2px solid orange; /* 주황색 테두리 */
  border-radius: 4px; /* 모서리를 둥글게 */
  font-size: 16px; /* 글자 크기 */
  }

button {
  margin-right: 10px;
}

/* 수평정렬 */
.parent {
  display: flex;
  align-items: center;
}

.input-box {
  flex: 1;
  margin-right: 10px;
}
</style>
