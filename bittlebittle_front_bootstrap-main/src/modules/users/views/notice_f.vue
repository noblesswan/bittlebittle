<template>
    <div>
      <h1>공지사항</h1>
      <div>
        <input v-model="searchKeyword" type="text" placeholder="공지사항 검색" />
      </div>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성날짜</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(notice, index) in filteredNotices" :key="notice.id">
            <td>{{ index + 1 }}</td>
            <td>{{ notice.title }}</td>
            <td>{{ notice.date }}</td>
          </tr>
        </tbody>
      </table>
      <div v-if="isAdmin">
        <button @click="updateNotice">수정</button>
        <button @click="deleteNotice">삭제</button>
        <button @click="createNotice">작성하기</button>
      </div>
    </div>
  </template>
  <script>
  import { ref, computed, onMounted } from "vue";
  
  export default {
    setup() {
      const searchKeyword = ref("");
      const notices = ref([]);
      const isAdmin = ref(false); // 예시 값입니다. 실제로는 로그인한 사용자의 정보를 사용해야 합니다.
  
      const fetchNotices = async () => {
        // SQL에서 게시글을 가져오는 코드를 추가하세요.
        // const response = await fetch...
        // notices.value = response.data;
        // 예시 데이터입니다. 실제로는 API 요청을 통해 데이터를 가져와야 합니다.
        notices.value = [
          { id: 1, title: "공지사항 제목 1", date: "2023-03-01" },
          { id: 2, title: "공지사항 제목 2", date: "2023-03-02" },
          { id: 3, title: "공지사항 제목 3", date: "2023-03-03" },
        ];
      };
  
      onMounted(fetchNotices);
  
      const filteredNotices = computed(() => {
        if (searchKeyword.value === "") return notices.value;
        return notices.value.filter((notice) =>
          notice.title.includes(searchKeyword.value)
        );
      });
  
      const updateNotice = async (noticeId) => {
        // SQL에서 게시글을 수정하는 코드를 추가하세요.
        // const response = await fetch...
      };
  
      const deleteNotice = async (noticeId) => {
        // SQL에서 게시글의 status 값을 'n'으로 변경하는 코드를 추가하세요.
        // const response = await fetch...
      };
  
      const createNotice = async () => {
        // SQL에서 게시글을 추가하는 코드를 추가하세요.
        // const response = await fetch...
      };
  
      return {
        searchKeyword,
        filteredNotices,
        isAdmin,
        updateNotice,
        deleteNotice,
        createNotice,
      };
    },
  };
  </script>