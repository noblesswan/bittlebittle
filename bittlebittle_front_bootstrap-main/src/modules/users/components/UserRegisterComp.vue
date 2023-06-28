<template>
  <p class="title">회원 가입</p>
  <form class="register-form" @submit.prevent="registerUser">
		  <p style="margin-bottom: 10px; font-size: 15px;" ><br></p>
	      <p style="margin-bottom: 50px;">
          <router-link to='/' style="font-weight: bold; color: #fd6500; text-decoration: none">BITTLE-BITTLE</router-link>
          에 오신 것을 환영합니다!</p>
          <table style="width: 27rem;">
	      <tr>
          <td style="width: 7rem;">* 아이디</td>
          <td style="width: 10rem;"><input type="text" id="" v-model="registerData.userId" placeholder="아이디" /></td>
          <td><button type="button" @click="idCheck" class="custom-btn btn btn-warning btn-sm" style="padding:8px; float: right;">아이디 확인</button>
          </td>
        </tr><br>
        <tr>
          <td colspan="3" style="text-align: center;">{{idCheckMsg}}</td>
        </tr><br>
        <tr>
    <td>* 비밀번호</td>
    <td colspan="2">
      <input type="password" v-model="registerData.userPwd" placeholder="비밀번호" />
      <p v-if="pwdErrorMsg" class="error-msg">{{ pwdErrorMsg }}</p>
    </td>
  </tr>
        <br>
        <tr>
    <td>* 비밀번호 확인</td>
    <td colspan="2">
      <input type="password" id="password-confirm" v-model="registerData.chkPwd" placeholder="비밀번호 확인" />
      <p v-if="pwdConfirmErrorMsg" class="error-msg">{{ pwdConfirmErrorMsg }}</p>
    </td>
  </tr>
        <br>
        <tr>
          <td>* 이름</td>
          <td colspan="2"><input type="text" v-model="registerData.userName" placeholder="이름" /></td>
        </tr><br>
        <tr>
          <td>* 닉네임</td>
          <td><input type="text" id="nickname" v-model="registerData.nickname" placeholder="닉네임(2글자 이상)" minlength="2" maxlength="10" /></td>
          <td><button type="button" @click="nicknameCheck" class="custom-btn btn btn-warning btn-sm" style="padding:8px; float: right;" >확인</button></td>
        </tr><br>
        <tr>
          <td colspan="3" style="text-align: center;">{{nicknameCheckMsg}}</td>
        </tr><br>
        <tr>
          <td>이메일</td>
          <td colspan="2"><input type="email" id="email" v-model="registerData.email" placeholder="email" /></td>
        </tr><br>
        <tr>
          <td>연락처</td>
          <td colspan="2">
            <!-- <select v-model="phoneNum.phone_1">
                <option value="010" selected>010</option>
                <option value="011">011</option>
                <option value="016">016</option>
                <option value="017">017</option>
                <option value="019">019</option>
            </select>
            - <input type="text" v-model="phoneNum.phone_2" style="width: 7rem;"/> - <input style="width: 7rem;" type="text" v-model="phoneNum.phone_3" /></td> -->
            <input type="text" v-model="registerData.phone" style="width: 15rem;" placeholder="000-0000-0000 '-'을 붙여서 작성해주세요 '"/>
            </td>
          </tr>
        </table><br><br><br>
      <button type="submit" class="custom-btn btn btn-warning" style="padding: 14px;">회원가입</button> <br><br>
      <p class="message">이미 회원이신가요? <router-link to="/">home</router-link></p>
    </form>

</template>

<script>
import { ref, watch } from 'vue'
import { $checkDuplicate, $checkDuplicateNickname, $addUser } from '@/api/user'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/users'

export default {
  name: 'UserRegisterComp',
  setup () {
    const idCheckMsg = ref(null)
    const nicknameCheckMsg = ref(null)

    const pwdErrorMsg = ref(null)
    const pwdConfirmErrorMsg = ref(null)

    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/

    // const phoneNum = {
    //   phone_1: '',
    //   phone_2: '',
    //   phone_3: ''
    // }

    // const phone = computed(() => {
    //   return phoneNum.phone_1 + '-' + phoneNum.phone_2 + '-' + phoneNum.phone_3
    // })

    const registerData = {
      userId: '',
      userPwd: '',
      chkPwd: '',
      userName: '',
      nickname: '',
      email: '',
      phone: ''

    }

    watch(registerData.userPwd, newVal => {
      if (!newVal.match(passwordRegex)) {
        pwdErrorMsg.value = '입력을 다시 해주세요'
      } else {
        pwdErrorMsg.value = null
      }
    })

    watch(registerData.chkPwd, newVal => {
      if (registerData.userPwd !== newVal) {
        pwdConfirmErrorMsg.value = '비밀번호가 일치하지 않습니다.'
      } else {
        pwdConfirmErrorMsg.value = null
      }
    })

    function idCheck () {
      if (registerData.userId === '') {
        idCheckMsg.value = '아이디를 입력해주세요.'
        return
      }
      $checkDuplicate(registerData.userId)
        .then(res => {
          console.log(res)
          if (res.data.isDuplicate) {
            idCheckMsg.value = '이미 존재하는 아이디 입니다.'
          } else {
            idCheckMsg.value = '사용 가능한 아이디 입니다.'
          }
        })
        .catch(err => console.log(err))
    }

    function nicknameCheck () {
      if (registerData.nickname === '') {
        nicknameCheckMsg.value = '닉네임을 입력해주세요.'
        return
      }
      $checkDuplicateNickname(registerData.nickname)
        .then(res => {
          console.log(res)
          if (!res.data.isDuplicate) {
            nicknameCheckMsg.value = '이미 존재하는 닉네임 입니다.'
          } else {
            nicknameCheckMsg.value = '사용 가능한  입니다.'
          }
        })
        .catch(err => console.log(err))
    }

    function checkAllFields () {
      const fields = Object.values(registerData)
      const result = fields.every(field => field !== '')
      console.log(registerData)
      if (!result) {
        alert('입력되지 않은 항목이 있습니다.')
        return false
      }

      if (idCheckMsg.value !== '사용 가능한 아이디 입니다.') {
        alert('아이디 중복확인을 해주세요.')
        return false
      }
      return true
    }

    const router = useRouter()
    const user = useUserStore()

    async function registerUser () {
      if (checkAllFields()) {
        await $addUser(registerData).then(
          res => {
            if (res.data.request) {
              const userInfo = {
                Authorization: res.headers.authorization,
                RefreshTokenIdx: res.headers.refreshtokenidx,
                userNo: res.data.userNo,
                adminYN: res.data.adminYn
              }
              user.setLoginUserInfo(userInfo)
              router.push({ name: 'TagRegisterComp' })
            }
          }
        ).catch(err => console.log(err))
      }
    }

    return {
      registerData,
      idCheckMsg,
      idCheck,
      nicknameCheck,
      registerUser,
      nicknameCheckMsg
    }
  }
}
</script>

<style scoped >
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
  background: #80ca6a;
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
  background: green;
}
.form .message {
  margin: 15px 0 0;
  color: #B3B3B3;
  font-size: 13px;
}
.form .message a {
  color: GREEN;
  font-weight: bold;
  text-decoration: none;
}
.form .message a:hover {
  cursor: pointer;
}

.register-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form .find-form {
  display: none;
  text-align: center;
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
  background-color: green;
}
.error-msg {
    font-size: 12px;
    color: red;
    margin-top: 5px;
  }
</style>
