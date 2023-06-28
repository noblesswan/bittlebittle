<template>
<nav class="navbar navbar-expand-lg bg-gray-900 shadow-lg">
  <div class="container">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <router-link class="navbar-brand" to="/">BITTLE-BITTLE</router-link>

    <div class="d-lg-none">
      <template v-if="loginUser == null">
        <router-link :to="{name:'UserLoginComp'}">
            <button type="button" class="custom-btn btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              로그인
            </button>
        </router-link>
        <router-link :to="{name:'UserRegisterComp'}">
            <button type="button" class="custom-btn btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              회원가입
            </button>
        </router-link>
      </template>
      <template v-else>
        <router-link :to="{name:'UserMyPageComp', params: {userNo: loginUser.userNo }}">
            <button type="button" class="custom-btn btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              {{ loginUser.nickname }}
            </button>
        </router-link>
        <!-- <router-link to="/"> -->
            <button type="button" @click="logout" class="custom-btn btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              로그아웃
            </button>
        <!-- </router-link> -->
      </template>
    </div>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav mx-auto">
        <!-- <li class="nav-item">
          <template v-if="loginUser != null && loginUser.adminYN == 'Y'">
            <router-link class="nav-link active" to="/admin">Home</router-link>
          </template>
          <template v-else>
            <router-link class="nav-link active" to="/">Home</router-link>
          </template>
        </li> -->
        <li class="nav-item">
          <template v-if="loginUser != null && loginUser.adminYN == 'Y'">
            <router-link class="nav-link" to="/admin/users">회원관리</router-link>
          </template>
        </li>

        <li class="nav-item">
          <template v-if="loginUser != null && loginUser.adminYN == 'Y'">
            <router-link class="nav-link" to="/admin/bottles">bottle</router-link>
          </template>
          <template v-else>
            <router-link class="nav-link" to="/bottles">bottle</router-link>
          </template>
        </li>

        <li class="nav-item">
          <router-link class="nav-link" to="/boards">자유게시판</router-link>
        </li>

        <li class="nav-item">
          <template v-if="loginUser != null && loginUser.adminYN == 'Y'">
            <router-link class="nav-link" to="/admin/notices">공지사항</router-link>
          </template>
          <template v-else>
          <router-link class="nav-link" to="/notices">공지사항</router-link>
          </template>
        </li>

        <!-- <li class="nav-item">
          <template v-if="loginUser != null && loginUser.adminYN == 'Y'">

          <router-link class="nav-link" to="/admin/faqs">FAQ</router-link>
          </template>
          <template v-else>
          <router-link class="nav-link" to="/faqs">FAQ</router-link>
        </template>
        </li> -->

        <li class="nav-item">
          <template v-if="loginUser != null && loginUser.adminYN == 'Y'">
            <router-link class="nav-link" to="/admin/tags">태그 수정</router-link>
          </template>
        </li>
      </ul>
    </div>

    <div class="d-none d-lg-block">
      <template v-if="loginUser == null">
        <router-link :to="{name:'UserLoginComp'}">
            <button type="button" class="custom-btn btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              로그인
            </button>
        </router-link>
        <router-link :to="{name:'UserRegisterComp'}">
            <button type="button" class="custom-btn btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              회원가입
            </button>
        </router-link>
      </template>
      <template v-else>
        <router-link :to="{name:'UserMyPageComp', params: {userNo: loginUser.userNo }}">
            <button type="button" class="custom-btn btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              {{ loginUser.nickname }}
            </button>
        </router-link>
        <!-- <router-link to="/"> -->
            <button type="button" @click="logout" class="custom-btn btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#BookingModal">
              로그아웃
            </button>
        <!-- </router-link> -->
      </template>

    </div>

  </div>
</nav>
</template>

<script>
import { useUserStore } from '@/stores/users'
import { $getUser, $logoutUser } from '@/api/user'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

export default {
  name: 'TheHeader',
  setup () {
    const loginUser = ref(null)

    const user = useUserStore()

    const router = useRouter()

    // const getLoginUser = () => {
    //   const userInfo = user.getLoginUserInfo
    //   if (userInfo != null && loginUser.value == null) {
    //     $getUser(userInfo.userNo
    //     ).then(res => {
    //       console.log(res.data)
    //       loginUser.value = res.data
    //       loginUser.value.adminYN = userInfo.adminYN
    //     }).catch(err => console.log(err))
    //   }
    // }
    const getLoginUser = async () => {
      const userInfo = user.getLoginUserInfo
      if (userInfo != null && loginUser.value == null) {
        try {
          const res = await $getUser(userInfo.userNo)
          console.log(res.data)
          loginUser.value = res.data
          loginUser.value.adminYN = userInfo.adminYN
        } catch (error) {
          console.log(error)
        }
      }
    }

    // 로그아웃
    async function logout () {
      try {
        const res = await $logoutUser()
        if (res.data.success === true) {
          user.setLoginUserInfo({})
          loginUser.value = null
          router.push('/')
        } else {
          console.log('로그아웃 실패')
        }
      } catch (error) {
        console.log(error)
      }
    }

    onMounted(() => {
      getLoginUser()
    })

    return {
      getLoginUser,
      loginUser,
      logout
    }
  }
}

</script>
<style scoped>
.custom-btn {
  border: 0;
    color: var(--white-color);
    font-size: var(--menu-font-size);
    padding: 10px 20px;
    margin-left: 10px;
}
</style>
