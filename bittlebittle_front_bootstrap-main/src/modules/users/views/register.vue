<template>
    <div>
      <h1>회원가입</h1>
      <form @submit.prevent="submitForm">
        <div>
          아이디 <input v-model="userId" @input="checkIdDuplicate" type="text" />
          <button @click.prevent="checkIdDuplicate">중복확인</button>
        </div>
        <div>
          비밀번호 <input v-model="password" @input="comparePassword" type="password" />
        </div>
        <div>
          비밀번호 확인 <input v-model="passwordCheck" @input="comparePassword" type="password" />
          <span>{{ passwordMessage }}</span>
        </div>
        <div>이름 <input v-model="name" type="text" /></div>
        <div>닉네임 <input v-model="nickname" type="text" /></div>
        <div>전화번호 <input v-model="phoneNumber" type="tel" /></div>
        <div>
          이메일 <input v-model="email" type="email" />
          <button @click.prevent="sendEmailVerification">인증하기</button>
        </div>
        <h2>이용약관 동의</h2>
        <div>
          <input type="checkbox" v-model="allAgree" @change="checkAllAgree" />
          전체 동의합니다
        </div>
        <div>
          <input type="checkbox" v-model="termsAgree" />
          이용약관동의(필수)
        </div>
        <div>
          <input type="checkbox" v-model="privacyAgree" />
          개인정보 수입/이용동의(선택)
        </div>
        <button type="submit">회원가입</button>
      </form>
    </div>
  </template>
  
  <script>
  import { ref, watch } from "vue";
  
  export default {
    setup() {
      const userId = ref("");
      const password = ref("");
      const passwordCheck = ref("");
      const passwordMessage = ref("");
      const name = ref("");
      const nickname = ref("");
      const phoneNumber = ref("");
      const email = ref("");
      const allAgree = ref(false);
      const termsAgree = ref(false);
      const privacyAgree = ref(false);
  
      const comparePassword = () => {
        passwordMessage.value =
          password.value === passwordCheck.value
            ? "비밀번호가 일치합니다"
            : "비밀번호가 일치하지 않습니다";
      };
  
      const checkIdDuplicate = async () => {
        // SQL 조회 코드를 여기에 추가하세요
        // const result = await fetch...
        const isDuplicate = false; // 예시 값입니다. 실제로는 SQL 조회 결과를 사용하세요.
  
        if (isDuplicate) {
          alert("사용할 수 없는 아이디입니다.");
        } else {
          alert("사용할 수 있는 아이디입니다.");
        }
      };
  
      const sendEmailVerification = () => {
        // 이메일 인증 기능을 여기에 추가하세요
      };
  
      const checkAllAgree = () => {
        termsAgree.value = allAgree.value;
        privacyAgree.value = allAgree.value;
      };
  
      const submitForm = () => {
  if (!termsAgree.value) {
    alert("이용 약관 동의에 체크하지 않았습니다.");
  } else {
    // SQL에 정보들을 INSERT하는 코드를 여기에 추가하세요
    // const result = await fetch...
    alert("회원가입이 완료되었습니다.");
  }
};

return {
  userId,
  password,
  passwordCheck,
  passwordMessage,
  name,
  nickname,
  phoneNumber,
  email,
  allAgree,
  termsAgree,
  privacyAgree,
  comparePassword,
  checkIdDuplicate,
  sendEmailVerification,
  checkAllAgree,
  submitForm,
};
},
};
</script>
  