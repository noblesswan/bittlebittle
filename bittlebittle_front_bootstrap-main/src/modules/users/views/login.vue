<template>
    <div class="login-container">
      <h2>LOGIN</h2>
      <form @submit.prevent="handleSubmit">
        <div class="input-group">
          <label for="username">아이디</label>
          <input type="text" id="username" v-model="username" required />
        </div>
        <div class="input-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" v-model="password" required />
        </div>
        <div class="checkbox-group">
          <input type="checkbox" id="auto-login" v-model="autoLogin" />
          <label for="auto-login">자동로그인</label>
          <input type="checkbox" id="save-id" v-model="saveId" />
          <label for="save-id">아이디 저장</label>
        </div>
        <div class="button-group">
          <button type="submit" class="primary">로그인</button>
          <button type="button" @click="goToSignUp">회원가입</button>
        </div>
        <div class="google-auth">
          <button type="button" class="google">구글 간편인증 가입</button>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Login',
    data() {
      return {
        username: '',
        password: '',
        autoLogin: false,
        saveId: false
      };
    },
    methods: {
      handleSubmit() {
        // 로그인 처리 코드를 여기에 작성하세요.
        console.log('로그인 정보:', this.username, this.password, this.autoLogin, this.saveId);
      },
      goToSignUp() {
        // 회원가입 페이지로 이동하는 코드를 여기에 작성하세요.
        console.log('회원가입 페이지로 이동');
      }
    }
  };
  </script>
  
  <style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 300px;
  margin: 0 auto;
  padding: 40px;
  background-color: #ffffff;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
  color: #333333;
  font-weight: bold;
}

.input-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

label {
  margin-bottom: 5px;
  font-size: 14px;
  color: #333333;
}

input[type="text"],
input[type="password"] {
  padding: 5px;
  width: 100%;
  font-size: 14px;
  border: 1px solid #cccccc;
  border-radius: 5px;
}

input[type="checkbox"] {
  margin-right: 5px;
}

.checkbox-group {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

button {
  padding: 5px 10px;
  font-size: 14px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  outline: none;
}

button.primary {
  background-color: #007bff;
  color: #ffffff;
}

button.google {
  background-color: #ea4335;
  color: #ffffff;
}

.google-auth {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
</style>