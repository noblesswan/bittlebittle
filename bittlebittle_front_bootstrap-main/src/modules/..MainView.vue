<template>
<section class="hero">
  <div class="container">
    <div class="row">

      <div class="col-lg-5 col-12 m-auto">
        <div class="heroText">

          <h1 class="text-white mb-lg-5 mb-3">오늘의 추천</h1>

          <div class="c-reviews my-3 d-flex flex-wrap align-items-center">
            <div class="d-flex flex-wrap align-items-center">
              <h4 class="text-white mb-0 me-3">4.4/5</h4>

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
                <img src="@/assets/images/slide/jay-wennington-N_Y88TWmGwA-unsplash.jpg" class="img-fluid carousel-image" alt="">
              </div>

              <div class="carousel-caption">
                                          <span class="text-white">
                                              <i class="bi-geo-alt me-2"></i>
                                              Manhattan, New York
                                          </span>

                <h4 class="hero-text">Fine Dining Restaurant</h4>
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

          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>

          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
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
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h2 class="text-center mb-lg-5 mb-4">NEW</h2>
      </div>
        <swiper :options="swiperOption" ref="swiperRef">
          <swiper-slide v-for="newbottle in newBottles" :key="newbottle.bottleNo">
            <div class="slide-container" style="display : flex">
              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : newbottle.bottle1.bottleNo} }">
                <img :src="getBottleImage(newbottle.bottle1.imgUrl, newbottle.bottle1.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ newbottle.bottle1.bottleName }}</h4>
                </router-link>
              </div>
  
              <!-- <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : newbottle.bottle2.bottleNo} }">
                <img :src="getBottleImage(newbottle.bottle2.imgUrl, newbottle.bottle2.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0" style="min-height:100px">{{ newbottle.bottle2.bottleName }}</h4>
                </router-link>
              </div> -->

              
              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : newbottle.bottle3.bottleNo} }">
                <img :src="getBottleImage(newbottle.bottle3.imgUrl, newbottle.bottle3.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ newbottle.bottle3.bottleName }}</h4>
                </router-link>
              </div>

            </div>
          </swiper-slide>
        </swiper>
    </div>
  </div>


<!-- -----------------BEST----------------- -->
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h2 class="text-center mb-lg-5 mb-4">BEST</h2>
      </div>
        <swiper :options="swiperOption" ref="swiperRef">
          <swiper-slide v-for="bestBottle in bestBottles" :key="bestBottle.bottleNo">
            <div class="slide-container" style="display : flex">
              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : bestBottle.bottle1.bottleNo} }">
                <img :src="getBottleImage(bestBottle.bottle1.imgUrl, bestBottle.bottle1.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ bestBottle.bottle1.bottleName }}</h4>
                </router-link>
              </div>

              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : bestBottle.bottle2.bottleNo} }">
                <img :src="getBottleImage(bestBottle.bottle2.imgUrl, bestBottle.bottle2.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ bestBottle.bottle2.bottleName }}</h4>
                </router-link>
              </div>

              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : bestBottle.bottle3.bottleNo} }">
                <img :src="getBottleImage(bestBottle.bottle3.imgUrl, bestBottle.bottle3.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ bestBottle.bottle3.bottleName }}</h4>
                </router-link>
              </div>

            </div>
          </swiper-slide>
        </swiper>
    </div>
  </div>

  <!-- -----------------찜한 상품 관련----------------- -->
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h2 class="text-center mb-lg-5 mb-4">~~님이 좋아할만한 !</h2>
      </div>
        <swiper :options="swiperOption" ref="swiperRef">
          <swiper-slide v-for="relatedFavorite in relatedFavorite" :key="relatedFavorite.bottleNo">
            <div class="slide-container" style="display : flex">
              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : relatedFavorite.bottle1.bottleNo} }">
                <img :src="getBottleImage(relatedFavorite.bottle1.imgUrl, relatedFavorite.bottle1.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ relatedFavorite.bottle1.bottleName }}</h4>
                </router-link>
              </div>

              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : relatedFavorite.bottle2.bottleNo} }">
                <img :src="getBottleImage(relatedFavorite.bottle2.imgUrl, relatedFavorite.bottle2.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ relatedFavorite.bottle2.bottleName }}</h4>
                </router-link>
              </div>

              <div class="slide-image">
                <router-link class="title" :to="{ name:'BottleDetailView', params : { bottleNo : relatedFavorite.bottle3.bottleNo} }">
                <img :src="getBottleImage(relatedFavorite.bottle3.imgUrl, relatedFavorite.bottle3.imgCusUrl)" alt="보틀 이미지" width="300" height="300">
                <h4 class="mb-0">{{ relatedFavorite.bottle3.bottleName }}</h4>
                </router-link>
              </div>

            </div>
          </swiper-slide>
        </swiper>
    </div>
  </div>






<!-- 
<div class="swiper-pagination"></div>
    <div class="swiper-buttons">
      <button class="swiper-button-prev" @click="swiperRef && swiperRef.slidePrev()">이전</button>
      <button class="swiper-button-next" @click="swiperRef && swiperRef.slideNext()">다음</button>
    </div>
 -->

</template>
<script>
import { getFormAxiosInstance } from '@/api/index'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/users'
import { Swiper, SwiperSlide } from 'swiper/vue'
// import SwiperCore, { Navigation } from 'swiper/core';

// swiper 스타일 추가
import 'swiper/swiper-bundle.css'
import '../../node_modules/swiper/swiper.scss'

// Swiper 컴포넌트에서 사용할 navigation 추가
// SwiperCore.use([Navigation]);

export default {
  name: '_MainView',
  components: {
    Swiper,
    SwiperSlide
  },
  setup() {
    const user = useUserStore()
    const userNo = ref(0)
    const axios = getFormAxiosInstance(user.getLoginUserInfo)
    const newBottles = ref([])
    const bestBottles = ref([]);
    const relatedFavorite = ref([]);
    const swiperRef = ref(null)

    // 보틀 이미지
    function getBottleImage(imgUrl, imgCusUrl) {
      return `http://localhost:8080/bittlebittle/image?path=bottle&name=${imgCusUrl}`
    }

    const swiperOption = {
      loop: true,
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
      },

    // 페이지네이션
    pagination: { // 페이징
		el : '.swiper-pagination',
		clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
	},

    slidesPerView: 3, // 동시에 보이는 슬라이드 갯수
    slidesPerGroup: 3, // 그룹으로 묶을 수. perView와 동일하게 지정했음
    spaceBetween: 10, // 슬라이드간 간격
  
  
    }

    function getMainBottles () {
      if(user.getLoginUserInfo != null && user.getLoginUserInfo.userNo != 0) {
        userNo.value = user.getLoginUserInfo.userNo
      }
      axios.get(`/api/bottles?userNo=${userNo.value}`)
        .then(res => {
          console.log(res)
          console.log('bottles data', res.data.bottle)
        // NEW
         let Nbottles = res.data.newBottle;
         let bottlesArr = [];
         let count = 0;
          for (let i = 0; i< Nbottles.length; i++) {
            let threeBottle = {}
            threeBottle.bottle1 = Nbottles[i];
            threeBottle.bottle2 = Nbottles[i+1];
            threeBottle.bottle3 = Nbottles[i+2];

            bottlesArr[count] = threeBottle;
            i++;
            i++;
            count++;
          }

          newBottles.value = bottlesArr;
          console.log('new data', newBottles);

        // BEST
         let Bbottles = res.data.bestBottle;
         let BbottlesArr = [];
         let Bcount = 0;
          for (let i = 0; i< Bbottles.length; i++) {
            let bThreeBottle = {}
            bThreeBottle.bottle1 = Bbottles[i];
            bThreeBottle.bottle2 = Bbottles[i+1];
            bThreeBottle.bottle3 = Bbottles[i+2];

            BbottlesArr[Bcount] = bThreeBottle;
            i++;
            i++;
            Bcount++;
          }

          bestBottles.value = BbottlesArr;
          console.log('best data', bestBottles);


          // 찜하기 관련
        //  let Rbottles = res.data.relatedFavorite;
        //  let RbottlesArr = [];
        //  let Rcount = 0;
        //   for (let i = 0; i< Rbottles.length; i++) {
        //     let rThreeBottle = {}
        //     rThreeBottle.bottle1 = Rbottles[i];
        //     rThreeBottle.bottle2 = Rbottles[i+1];
        //     rThreeBottle.bottle3 = Rbottles[i+2];

        //     RbottlesArr[Rcount] = rThreeBottle;
        //     i++;
        //     i++;
        //     Rcount++;
        //   }

          relatedFavorite.value = RbottlesArr;
          console.log('relatedFavorite data', relatedFavorite);




        })

    }

    onMounted(() => {
      getMainBottles()
    })

    return {
      newBottles,
      bestBottles,
      relatedFavorite,
      getBottleImage,
      swiperOption,
      swiperRef

    }
  },
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