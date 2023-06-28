<template>
    <div>
      <h1>{{ selectedNotice.title }}</h1>
      <table>
        <tr>
          <td>작성자</td>
          <td>{{ selectedNotice.nickname }}</td>
        </tr>
        <tr>
          <td>작성날짜</td>
          <td>{{ selectedNotice.date }}</td>
        </tr>
        <tr>
          <td colspan="2">{{ selectedNotice.noticeContent }}</td>
        </tr>
      </table>
      <div v-if="isAdmin">
        <button @click="updateNotice">수정</button>
        <button @click="deleteNotice">삭제</button>
      </div>
    </div>
  </template>
  <script>
  import { ref, onMounted } from "vue";
  
  export default {
    props: ["noticeId"], // 게시글의 ID를 props로 받습니다.
    setup(props) {
      const selectedNotice = ref({});
      const isAdmin = ref(false); // 예시 값입니다. 실제로는 로그인한 사용자의 정보를 사용해야 합니다.
  
      const fetchNotice = async () => {
        // SQL에서 게시글을 가져오는 코드를 추가하세요.
        // const response = await fetch...
        // selectedNotice.value = response.data;
        // 예시 데이터입니다. 실제로는 API 요청을 통해 데이터를 가져와야 합니다.
        selectedNotice.value = {
          id: 1,
          title: "공지사항 제목 1",
          nickname: "작성자 닉네임",
          date: "2023-03-01",
          noticeContent: "공지사항 상세 내용",
        };
      };
  
      onMounted(() => fetchNotice(props.noticeId));
  
      const updateNotice = async () => {
        // SQL에서 게시글을 수정하는 코드를 추가하세요.
        // const response = await fetch...
      };
  
      const deleteNotice = async () => {
        // SQL에서 게시글의 status 값을 'n'으로 변경하는 코드를 추가하세요.
        // const response = await fetch...
      };
  
      return {
        selectedNotice,
        isAdmin,
        updateNotice,
        deleteNotice,
      };
    },
  };
  </script>
  
  
  