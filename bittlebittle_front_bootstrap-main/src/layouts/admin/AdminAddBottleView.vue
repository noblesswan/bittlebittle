<template>

<div style="background-color: black;">
  <div>
    <div>
      <h5>보틀 추가</h5>
    </div>
    <div>
      <form>
        <div class="form-group">
          <label for="imgUrl">보틀 이미지</label>
          <input type="file" class="form-control" id="imgUrl" accept="image/*" @change="handleImageUpload">
        </div>
        <div class="form-group">
          <label for="bottleName">보틀 이름</label>
          <input type="text" class="form-control" id="bottleName" v-model="addBottleName">
        </div>
        <div class="form-group">
          <label for="bottleContent">보틀 내용</label>
          <textarea class="form-control" id="bottleContent" rows="3" v-model="addBottleContent"></textarea>
        </div>
        <div class="form-group">
          <label for="bottleBrand">보틀 브랜드</label>
          <input type="text" class="form-control" id="bottleBrand" v-model="addBottleBrand">
        </div>
        <div class="form-group">
          <label for="bottleAbv">보틀 도수</label>
          <input type="number" class="form-control" id="bottleAbv" v-model="addBottleAbv">
        </div>
        <div>
          <div v-for="tagType in tagTypeList" :key="tagType.tagTypeNo">
            <div style="color: orange;">{{ tagType.tagTypeName }}</div>
            <div>
              <label v-for="tag in tagList.filter(tag => tag.keyTypeNo === tagType.tagTypeNo)" :key="tag.tagNo" class="tag-box">
                <input type="radio" :name="`new-tag-${tagType.tagTypeNo}`" :value="tag.tagNo" v-model="selectedAddTags[tagType.tagTypeNo-1]">
                {{ tag.tagName }}
              </label>
            </div>
          </div>
        </div>
      </form>
    </div>
    <div>
      <br>
      <div class="btn-group">
      <router-link :to="{ name:'AdminBottleView' }">
        <button class="btn btn-secondary">취소</button>
      </router-link>
      <button class="btn btn-primary" @click="addBottle()">추가</button>
      </div>
    </div>
  </div>
</div>
  
</template>

<script>
import { getFormAxiosInstance } from '@/api/index'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/users'
import { useRouter } from 'vue-router'

export default {
  name: 'AdminBottleView',

  setup () {
    const user = useUserStore()
    const axios = getFormAxiosInstance(user.getLoginUserInfo)

    const addBottleName = ref('')
    const addBottleContent = ref('')
    const addBottleBrand = ref('')
    const addBottleAbv = ref(0)
    const tagTypeList = ref([])
    const tagList = ref([])
    const selectedAddTags = ref([])
    
    // 이미지
    const addBottleImage = ref()
    const handleImageUpload = function (event) {
      addBottleImage.value = event.target.files[0]
    }
    


    onMounted(() => {
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
    })

    const router = useRouter()

    const addBottle = function () {
      const url = '/api/admin/bottles'

      const data = new FormData()
      data.append('bottleName', addBottleName.value)
      data.append('bottleContent', addBottleContent.value)
      data.append('bottleBrand', addBottleBrand.value)
      data.append('bottleAbv', addBottleAbv.value)
      data.append('tagNoList', selectedAddTags.value.filter(Boolean));
      data.append('imgUrlOrigin', addBottleImage.value)
      // imgUrl에 이미지 루트 넣어야됨!

      axios.post(url, data)
        .then(res => {
          router.push('/admin/bottles')
        })
    }


   
    

    return {
      addBottleName,
      addBottleContent,
      addBottleBrand,
      addBottleAbv,
      tagTypeList,
      tagList,
      selectedAddTags,
      addBottle,
      addBottleImage,
      handleImageUpload
    }
  }
}
</script>

<style scoped>
  

  .form-group {
      margin-bottom: 10px;
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


.form-control {
  height: 50px;
  width: 600px;
}

 h5 {
    color: orange;
  }
  label {
    color: white;
  }
  input[type="text"],
  input[type="number"],
  textarea {
    border-color: orange;
    background-color: black;
    color: white;
  }
  .tag-box {
    border-color: orange;
    color: white;
  }
  .btn-secondary {
    background-color: orange;
    color: black;
  }
  .btn-primary {
    background-color: orange;
    color: black;
  }
  .btn-group button {
    margin-right: 10px;
  }
</style>
