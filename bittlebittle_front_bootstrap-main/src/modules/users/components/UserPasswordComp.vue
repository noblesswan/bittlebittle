<template>
  <form class="find-form" method="post">
          <input type="email" id="email_" name="email" placeholder="회원가입 시 사용했던 이메일" />

          <button type="button" id="pw-rset-btn">비밀번호 재설정</button>
          <p class="message"><a href="#" class="back-first">로그인으로 돌아가기</a></p>
  </form>
</template>

<script>
export default {

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
  background-color: green;
}
</style>
