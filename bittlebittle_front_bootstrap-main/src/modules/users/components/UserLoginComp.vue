<template>
  <div class="login-page">
    <div class="form">
      <div style="height: 200px;">
        <router-link to="/">
          <img src="../../../assets/images/logo/logo-1.jpeg" style="height: 200px;">
        </router-link>
      </div>
      <input type="text" id="email" v-model="loginData.userId" placeholder="아이디"/>
      <input type="password" id="password_" v-model="loginData.userPwd"  @keyup.enter="getLoginUser" placeholder="비밀번호" />
      <button type="button" @click="getLoginUser">로그인</button>
      <br>
      <p class="message">아직 회원이 아니신가요?
        <router-link :to='{name:"UserRegisterComp"}' class="back">회원가입</router-link>
      </p>
      <p class="message" style="margin-top: 7px;">비밀번호를 잊으셨나요? <a class="find">비밀번호 찾기</a></p>
    </div>
  </div>
</template>

<script>
import { $loginUser } from '@/api/user'
import { useUserStore } from '@/stores/users'
import { useRouter } from 'vue-router'

export default {
  name: 'UserLoginComp',
  setup () {
    const loginData = {
      userId: '',
      userPwd: ''
    }

    const user = useUserStore()

    const router = useRouter()

    const getLoginUser = () => {
      $loginUser(loginData)
        .then(res => {
          if (res.data.success === true) {
            const userInfo = {
              Authorization: res.headers.authorization,
              RefreshTokenIdx: res.headers.refreshtokenidx,
              userNo: res.data.userNo,
              adminYN: res.data.adminYn
            }
            user.setLoginUserInfo(userInfo)

            // 회원 정보가 필요한 페이지에 접속할 때에는
            // import { useUserStore } from '@/stores/users'
            // const user = useUserStore()
            // user.getLoginUserHeaderInfo
            // => {Authorization: 'bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEyMz…c4OX0.Cx_AETN-jjXWOi-RfO08uNWiGNLHdLCnix0hyyYLkaw',
            //  RefreshTokenIdx: '$2a$10$LIinM0T3QxBxVHXL6teSPuqgLKx.DYYtHOpciO8k113onr3JBy/uK'}
            // 이런 식으로 데이터가 조회되니
            // 이 데이터를 header 에 넣어서 axios 에 전송해주시면 됩니다.
            // axios.get(url, data, {header :
            //   {
            //     Authorization: user.getLoginUserHeaderInfo.Authorization,
            //     RefreshTokenIdx: user.getLoginUserHeaderInfo.RefreshTokenIdx
            //   }
            // })
            if (userInfo.adminYN === 'Y') {
              router.push('/admin')
            } else {
              router.push('/')
            }
          } else {
            console.log('잘못된 접근입니다.')
          }
        })
        .catch(err => console.log(err)
        )
    }

    return {
      loginData,
      getLoginUser
    }
  }
}
</script>

<style scoped >
@font-face {
    font-family: 'NanumSquareRound';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
:root {
  --text-color: black;
  --background-color: #f8ded2;
  --accent-color: #ff4d00;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'NanumSquareRound';
}
#email, #email-2, #emailcheck, #password, #password-confirm, #nickname, #birthday, #mail-check-warn1, #mail-check-warn2 {
   font-family: 'NanumSquareRound';
}
.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  border-radius: 40px;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
 font-family: 'NanumSquareRound';
  outline: 0;
  background: #F2F2F2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  border-radius: 20px;
  font-size: 14px;
}
.form button {
  text-transform: uppercase;
  outline: 0;
  background: #ffa704;
  width: 100%;
  border: 0;
  border-radius: 20px;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,.form button:active,.form button:focus {
  background: rgba(229, 112, 2, 0.967);
}
.form .message {
  margin: 15px 0 0;
  color: #B3B3B3;
  font-size: 13px;
}
.form .message a {
  color: rgba(229, 112, 2, 0.967);
  font-weight: bold;
  text-decoration: none;
}
.form .message a:hover {
  cursor: pointer;
}
.form .register-form,
.form .find-form {
  display: none;
}
.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before, .container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1A1A1A;
}
.container .info span {
  color: #4D4D4D;
  font-size: 12px;
}
.container .info span a {
  color: #000000;
  text-decoration: none;
}
.container .info span .fa {
  color: #EF3B3A;
}
body {
  background: green; /* fallback for old browsers */
  background: green;
  background: linear-gradient(90deg, rgb(183, 226, 159) 0%, rgb(185, 229, 160) 50%);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
#email-2, #nickname {
  width: 205px;
  float: left;
  border-top-right-radius: 1px;
  border-bottom-right-radius:1px;
  margin-right: 0;
}
#email-2 {
    height: 46.67px;
}
#confirm {
  width: 65px;
  height: 46.67px;
  box-sizing: border-box;
  background: #80ca6a;
  border-top-left-radius:1px;
  border-bottom-left-radius:1px;
}
#confirm:hover {
  background-color: rgb(219, 113, 0);
}
</style>
