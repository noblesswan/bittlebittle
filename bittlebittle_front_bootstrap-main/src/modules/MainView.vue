<template>

<section class="hero">
  <div class="container">
    <div class="row">

      <div class="col-lg-5 col-12 m-auto">
        <div class="heroText">

          <h1 class="text-white mb-lg-5 mb-3">오늘의 추천</h1>
          <div class="c-reviews my-3 d-flex flex-wrap align-items-center">
            <div class="d-flex flex-wrap align-items-center">
              <h4 class="text-white mb-0 me-3">{{ mainBottle.grade }}/5</h4>

              <div class="reviews-stars">
                <i class="bi-star-fill reviews-icon"></i>
                <i class="bi-star-fill reviews-icon"></i>
                <i class="bi-star-fill reviews-icon"></i>
                <i class="bi-star-fill reviews-icon"></i>
                <i class="bi-star reviews-icon"></i>
              </div>
            </div>

            <p class="text-white w-100">From <strong>1,206+</strong> Customer Reviews</p>
          </div>
        </div>
      </div>

      <div class="col-lg-7 col-12">
        <div id="carouselExampleCaptions" class="carousel carousel-fade hero-carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <div class="carousel-image-wrap">
                <!-- 전체 평점 기반 술 중에서 1등을 뽑아서 이미지 -->
                <img style="max-width: 600px; max-height: 500px;" :src="getBottleImage(mainBottle.imgUrl, mainBottle.imgCusUrl)" class="img-fluid carousel-image" alt="">
              </div>

              <div class="carousel-caption">
                                          <span class="text-white">
                                              <i class="bi-geo-alt me-2"></i>
                                              Manhattan, New York
                                          </span>

                <h4 class="hero-text">{{mainBottle.bottleName}}</h4>
              </div>
            </div>

            <div class="carousel-item">
              <div class="carousel-image-wrap">
                <img src="@/assets/images/slide/jason-leung-O67LZfeyYBk-unsplash.jpg" class="img-fluid carousel-image" alt="">
              </div>

              <div class="carousel-caption">
                <div class="d-flex align-items-center">
                  <h4 class="hero-text">Steak</h4>

                  <span class="price-tag ms-4"><small>$</small>26.50</span>
                </div>

                <div class="d-flex flex-wrap align-items-center">
                  <h5 class="reviews-text mb-0 me-3">3.8/5</h5>

                  <div class="reviews-stars">
                    <i class="bi-star-fill reviews-icon"></i>
                    <i class="bi-star-fill reviews-icon"></i>
                    <i class="bi-star-fill reviews-icon"></i>
                    <i class="bi-star reviews-icon"></i>
                    <i class="bi-star reviews-icon"></i>
                  </div>
                </div>
              </div>
            </div>

            <div class="carousel-item">
              <div class="carousel-image-wrap">
                <img src="@/assets/images/slide/ivan-torres-MQUqbmszGGM-unsplash.jpg" class="img-fluid carousel-image" alt="">
              </div>

              <div class="carousel-caption">
                <div class="d-flex align-items-center">
                  <h4 class="hero-text">Sausage Pasta</h4>

                  <span class="price-tag ms-4"><small>$</small>18.25</span>
                </div>

                <div class="d-flex flex-wrap align-items-center">
                  <h5 class="reviews-text mb-0 me-3">4.2/5</h5>

                  <div class="reviews-stars">
                    <i class="bi-star-fill reviews-icon"></i>
                    <i class="bi-star-fill reviews-icon"></i>
                    <i class="bi-star-fill reviews-icon"></i>
                    <i class="bi-star-fill reviews-icon"></i>
                    <i class="bi-star reviews-icon"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>

  <div class="video-wrap">
    <video autoplay="" loop="" muted="" class="custom-video" poster="">
      <source src="video/production_ID_3769033.mp4" type="video/mp4">
      Your browser does not support the video tag.
    </video>
  </div>

  <div class="overlay"></div>
</section>

<!-- -----------------NEW----------------- -->
    <div class="row">
      <div class="col-12">
        <h3 class="text-center" style="color:lightgrey">이 주의 신상 랭킹 ></h3>
        <h6 class="text-center title-de" style="color:gold" >매주 업데이트! 화려한 신상 라인업</h6>

        <h4></h4>
      </div>
        <swiper :options="swiperOption" ref="swiperRef">

          <swiper-slide v-for="newbottle in newBottles" :key="newbottle">
            <template v-if="newbottle.length != 0">
                <div style="margin-left: 10px; display : flex" v-for="newb in newbottle" :key="newb" class="slide-container">
                  <div class="slide-image">
                    <div class="title" @click="movePage(newb.bottleNo)">
                      <img :value="newb.bottleNo" :src="getBottleImage(newb.imgUrl, newb.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                      <h5 style="min-height: 80px;" class="mb-0">{{ newb.bottleName }}</h5>
                    </div>
                  </div>
                </div>
            </template>
          </swiper-slide>

        </swiper>
    </div>

<!-- -----------------BEST----------------- -->
    <div class="row">
      <div class="col-12">
        <h3 class="text-center" style="color:lightgrey">인기 BEST 상품 ></h3>
        <h6 class="text-center title-de" style="color:gold" >리뷰 4점대 돌파 상품입니다 !</h6>

      </div>
        <swiper :options="swiperOption" ref="swiperRef">

          <swiper-slide v-for="bestBottle in bestBottles" :key="bestBottle">
            <template v-if="bestBottle.length != 0 ">
                <div v-for="bestb in bestBottle" :key="bestb" class="slide-container" style="margin-left: 10px; display : flex">
                  <div class="slide-image">
                    <div class="title" @click="movePage(bestb.bottleNo)">
                      <img :value="bestb.bottleNo" :src="getBottleImage(bestb.imgUrl, bestb.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                      <h5 style="min-height: 80px;" class="mb-0">{{ bestb.bottleName }}</h5>
                    </div>
                  </div>
                </div>
            </template>
          </swiper-slide>

        </swiper>
    </div>

  <!-- -----------------찜한 상품 관련----------------- -->
    <div class="row" v-if="isLoggedIn">
      <div class="col-12">
        <template v-if="userInformation != null">
          <h3 class="text-center"  style="color:lightgrey">{{userInformation.nickname}} 님이 좋아할만한 ></h3>
          <h6 class="text-center title-de" style="color:gold" >찜한 목록에 담은 상품과 비슷한 상품을 추천드립니다. </h6>
        </template>
      </div>
        <swiper :options="swiperOption" ref="swiperRef">

          <swiper-slide v-for="relatedFavorite in relatedFavorites" :key="relatedFavorite">
            <template v-if="relatedFavorite.length != 0 ">
                <div v-for="relb in relatedFavorite" :key="relb" class="slide-container" style="margin-left: 10px; display : flex">
                  <div class="slide-image">
                    <div class="title" @click="movePage(relb.bottleNo)">
                      <img :value="relb.bottleNo" :src="getBottleImage(relb.imgUrl, relb.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                      <h5 style="min-height: 80px;" class="mb-0">{{ relb.bottleName }}</h5>
                    </div>
                  </div>
                </div>
            </template>
          </swiper-slide>

        </swiper>
    </div>

</template>
<script>
import { getFormAxiosInstance } from '@/api/index'
import { onMounted, ref } from 'vue'

import { useUserStore } from '@/stores/users'
import { Swiper, SwiperSlide } from 'swiper/vue'

// swiper 스타일 추가
import 'swiper/swiper-bundle.css'
import '../../node_modules/swiper/swiper.scss'
import { useBoardStore } from '@/stores/boards'
import { useRouter } from 'vue-router'
import { $getUser } from '@/api/user'

export default {
  name: 'MainView',
  components: {
    Swiper,
    SwiperSlide
  },
  setup () {
    const user = useUserStore()
    const userNo = ref(0)
    const axios = getFormAxiosInstance(user.getLoginUserInfo)
    const newBottles = ref([])
    const bestBottles = ref([])
    const relatedFavorites = ref([])
    const swiperRef = ref(null)
    const isLoggedIn = ref(false)

    const router = useRouter()

    const userInformation = ref(null)
    function getUserInformation () {
      $getUser().then(
        res => {
          userInformation.value = res.data
          console.log(userInformation.value)
        }
      ).catch(err => {
        console.log(err)
      })
    }

    // 보틀 이미지
    function getBottleImage (imgUrl, imgCusUrl) {
      return `http://localhost:8080/bittlebittle/image?path=bottle&name=${imgCusUrl}`
    }

    const swiperOption = {
      loop: true,
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },

      // 페이지네이션
      pagination: { // 페이징
        el: '.swiper-pagination',
        clickable: true // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
      },

      slidesPerView: 3, // 동시에 보이는 슬라이드 갯수
      slidesPerGroup: 3, // 그룹으로 묶을 수. perView와 동일하게 지정했음
      spaceBetween: 10 // 슬라이드간 간격

    }

    // 3개씩 슬라이드에 담는 함수
    function setThreeBottle (setBottleList, bottleData) {
      for (let i = 0; i < bottleData.length; i += 3) {
        const bThreeBottle = []
        bThreeBottle.push(bottleData[i])
        if (i + 1 < bottleData.length) bThreeBottle.push(bottleData[i + 1])
        if (i + 2 < bottleData.length) bThreeBottle.push(bottleData[i + 2])
        setBottleList.value.push(bThreeBottle)
      }
    }

    const mainBottle = ref({})

    function getMainBottles () {
      if (user.getLoginUserInfo != null && user.getLoginUserInfo.userNo != 0) {
        userNo.value = user.getLoginUserInfo.userNo
      }
      axios.get(`/api/bottles?userNo=${userNo.value}`)
        .then(res => {
          // NEW
          setThreeBottle(newBottles, res.data.newBottle)
          console.log('new data', newBottles.value)
          // BEST
          setThreeBottle(bestBottles, res.data.bestBottle)
          console.log(res.data.bestBottle)
          mainBottle.value = res.data.bestBottle[0]
          console.log('best data', bestBottles.value)
          // 찜하기 관련
          if (res.data.relatedFavorite != null) {
            setThreeBottle(relatedFavorites, res.data.relatedFavorite)
            console.log('relatedFavorite data', relatedFavorites.value)
            getUserInformation()
          }
        })
    }

    function loginCheck () {
      if (user.getLoginUserInfo != null && user.getLoginUserInfo.userNo !== 0) {
        isLoggedIn.value = true
      } else {
        isLoggedIn.value = false
      }
    }

    onMounted(() => {
      loginCheck()
      getMainBottles()
    })

    const bottle = useBoardStore()

    function movePage (bottleNo) {
      bottle.setBottleInfo(bottleNo)
      console.log(`/bottles/${bottleNo}`)
      router.push(`/bottles/${bottleNo}`)
    }

    return {
      newBottles,
      bestBottles,
      relatedFavorites,
      getBottleImage,
      swiperOption,
      swiperRef,
      isLoggedIn,
      movePage,
      userInformation,
      mainBottle
    }
  }
}
</script>
<style scoped>
.swiper-buttons {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
}

.swiper-button-prev,
.swiper-button-next {
  border: none;
  background: none;
  font-size: 1.2rem;
  cursor: pointer;
  padding: 0 1rem;
}

.slide-container{
  width:300px;
	padding:30px 0;
}

.swiper-slide {
	text-align:center;
	display:flex; /* 내용을 중앙정렬 하기위해 flex 사용 */
	align-items:center; /* 위아래 기준 중앙정렬 */
	justify-content:center; /* 좌우 기준 중앙정렬 */
}
</style>
