<template>
<!-- 태그 목록 -->
  <div class="container mt-3">
    <div class="row">
      <div class="col-md-6">
        <input type="text" class="form-control" v-model="keyword" placeholder="검색어를 입력하세요">
      </div>
      <div class="col-md-2">
        <button class="btn btn-primary" @click="search">검색</button>
      </div>

      <div v-for="tagType in tagTypeList" :key="tagType.tagTypeNo">
        <div class="title">{{ tagType.tagTypeName }}</div>
        <div>
          <label>
            <input type="radio" :name="`tag-${tagType.tagTypeNo}`" :value = "null" v-model="selectedTags[tagType.tagTypeNo-1]">
            전체선택
          </label>
          <label v-for="tag in tagList.filter(tag => tag.keyTypeNo === tagType.tagTypeNo)" :key="tag.tagNo" class="tag-box">
            <input type="radio" :name="`tag-${tagType.tagTypeNo}`" :value="tag.tagNo" v-model="selectedTags[tagType.tagTypeNo-1]">
            {{ tag.tagName }}
          </label>

        </div>
      </div>
    </div>
    <div class="row mt-3">
      <router-view :bottles="filteredBottles" :favorites="favorites" />
    </div>
  </div>

  <!-- 보틀목록 -->
<div class="grid-container">
  <div v-for="bottle in bottles" :key="bottle.bottleNo">
    <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : bottle.bottleNo} }">
    <img :src="getBottleImage(bottle.imgUrl, bottle.imgCusUrl)" alt="보틀 이미지" width="200" height="200">
    <br>
      {{ bottle.bottleName }}
    </router-link>
    <div>{{bottle.reviewCount}}</div>
    <div>
      <img src="@/images/star.png" alt="별 이미지" width="20" height="20">
      {{bottle.grade}}</div>
  </div>
</div>

</template>

<script>
import { getFormAxiosInstance } from '@/api/index'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/users'

export default {
  name: 'BottleAll',

  setup () {
    // axios Instance 를 생성할 때, token 문제 때문에 인자로 데이터하나가 필요해
    const user = useUserStore()
    const axios = getFormAxiosInstance(user.getLoginUserInfo)

    const bottles = ref([])
    const favorites = ref([])
    const keyword = ref('') // 검색어 변수 선언
    const tags = ref([])
    const tagList = ref([])
    const tagTypeList = ref([])
    const selectedTags = ref([])
    const filteredBottles = ref([])

    // 전체조회를 하는 axios 를 하나의 함수로 만들구, onMounted 할 때랑 검색할 때 같이 쓰는게 좋을..?

    onMounted(() => {
      axios.get('/api/bottles/all')
        .then(res => {
          console.log('bottles data', res.data)
          bottles.value = res.data.bottle
          favorites.value = res.data.favorites
        })
        .catch(err => {
          console.log('error', err)
        }),

      axios.get('/api/tags')
        .then(res => {
          console.log('tags data', res.data)
          tags.value = res.data.tags
          tagList.value = res.data.tagList
          tagTypeList.value = res.data.tagTypeList
          selectedTags.value = Array(tagTypeList.value.length).fill('')
          console.log('tagTypeList', res.data.tagTypeList)

          if (selectedTags.value) {
            for (let i = 0; i < selectedTags.value.length; i++) {
              // your code here
              // console.log('bbb', selectedTags.value)
            }
          }
        })
        .catch(err => {
          console.log('error', err)
        })
    })

    const filterBottles = () => {
      let filtered = bottles.value
      for (let i = 0; i < selectedTags.value.length; i++) {
        if (selectedTags.value[i]) {
          filtered = filtered.filter(bottle => {
            return bottle.tags.find(tag => tag.tagNo === Number(selectedTags.value[i]))
          })
        }
      }
      filteredBottles.value = filtered
    }

    function search () {
      console.log('키워드 : ', keyword.value)
      // console.log('selectedTags', selectedTags.value);
      console.log('selectedTags', selectedTags.value.filter(tag => tag !== ''))

      axios.post('/api/bottles/all', {
        keyword: keyword.value,
        // ,tagNoList: selectedTags.value
        tagNoList: selectedTags.value.filter(tag => tag !== '')
      })
        .then(res => {
          console.log('RESULT', res.data)
          this.bottles = res.data.bottle
        })
        .catch(err => {
          console.log('error', err)
        })
    }

    const filteredTagList = (tagTypeNo) => {
      return tagList.value.filter(tag => tag.keyTypeNo === tagTypeNo)
    }

    function getImage (imgUrl, imgCusUrl) {
      if (imgCusUrl != null) {
        return `http://localhost:8080/bittlebittle/image?path=bottle&name=${imgCusUrl}`
      } else {
        return `http://localhost:8080/bittlebittle/image?path=bottle&name=${imgUrl}`
      }
    }

    const selectAllTags = () => {
      selectedTags.value = tagList.value.map(tag => tag.tagNo)
      tagList.value.forEach(tag => {
        selectedTags.value[tag.keyTypeNo - 1] = tag.tagNo
      })
      filterBottles()
    }

    function getBottleImage (imgUrl, imgCusUrl) {
      if (imgCusUrl != null) {
        return `http://localhost:8080/bittlebittle/image?path=bottle&name=${imgCusUrl}`
      } else {
        return `http://localhost:8080/bittlebittle/image?path=bottle&name=${imgUrl}`
      }
    }

    return {
      bottles,
      favorites,
      keyword,
      tags,
      tagList,
      tagTypeList,
      selectedTags,
      filteredBottles,
      filteredTagList,
      search,
      getBottleImage
    }
  },

  methods: {
    filteredTagList (tagTypeNo) {
      console.log('zzz', this.tagList)
      return this.tagList.filter(tag => tag.keyTypeNo === tagTypeNo)
    }

  }
}
</script>

<style scoped>
/* 글자색 흰색으로 바꾸려고 만들었어요 */
* {
  color: var(--white-color);
}

.grid-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 1rem;
  }

.title {
  color:orange;
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

.btn-primary {
    background-color: orange;
    color: black;
  }

</style>
