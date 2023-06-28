<template>
  <div class="container">
    <div>
      <input type="text" v-model="keyword" placeholder="검색어를 입력하세요">
      <button @click="search">검색</button>

      <div class="row">

        <div class="col-12">
          <h2 class="text-center mb-lg-5 mb-4">NEW/BEST/등등</h2>
        </div>
        <template v-for="bottle in bottles" :key="bottle.bottleNo">
          <div class="col-lg-3 col-md-3 col-6">
            <div class="menu-thumb">
              <div class="menu-image-wrap">
                <img :src="getImage(bottle.imgUrl, bottle.imgCusUrl)" class="img-fluid menu-image" style="max-width: 150px;" alt="">
                <span class="menu-tag bg-warning">Breakfast</span>
              </div>

              <div class="menu-info d-flex flex-wrap align-items-center">
                <h4 class="mb-0">{{ bottle.bottleName }}</h4>

                <!-- <span class="price-tag bg-white shadow-lg ms-4"><small>$</small>12.50</span> -->

                <div class="d-flex flex-wrap align-items-center w-100 mt-2">
                    <h6 class="reviews-text mb-0 me-3">4.3/5</h6>

                    <div class="reviews-stars">
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star reviews-icon"></i>
                    </div>

                    <p class="reviews-text mb-0 ms-4">102 Reviews</p>
                </div>
              </div>
            </div>
        </div>
        </template>
      </div>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="bottle in bottles" :key="bottle.bottleNo">
            <td>{{ bottle.bottleNo }}</td>
            <td>
              <router-link :to="{ name:'BottleDetailView', params : { bottleNo : bottle.bottleNo} }">
                {{ bottle.bottleName }}
              </router-link>
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

export default {
  name: 'BottleView',

  setup () {
    const axios = getFormAxiosInstance()

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

    function getImage (imgUrl, imgCusUrl) {
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
      getImage
    }
  }
}
</script>

<style>
</style>
