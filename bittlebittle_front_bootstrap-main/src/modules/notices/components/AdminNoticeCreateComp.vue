<template>
  <div class="content-notice">
    <div style="padding-top: 70px; padding-bottom: 200px; margin: 0 auto">
      <form
        @submit.prevent="registerNotice"
        style="margin: 0 auto; width: 730px text-align: center;"
      >
        <fieldset>
          <table style="padding-left: 35px; padding-top: 20px; margin: 0 auto;">
            <tr>
              <td><b>제목</b></td>
              <td>
                <input
                  type="text"
                  v-model="noticeData.noticeTitle"
                  id="title"
                  placeholder="제목을 입력하세요"
                  required
                />
              </td>
            </tr>
            <tr>
              <td><b>내용</b></td>
              <td>
                <textarea
                  v-model="noticeData.noticeContent"
                  id="content-text"
                  cols="52"
                  rows="12"
                  placeholder="내용을 입력하세요"
                  required
                ></textarea>
              </td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" value="작성하기" id="submit"  style="display: block; margin: 0 auto;"></td>
            </tr>
          </table>
          <br>
        </fieldset>
      </form>
    </div>
  </div>
</template>

<script>
import { useUserStore } from "@/stores/users";
import { $addNotice } from "@/api/notice";
import { useRouter } from "vue-router";
// import { getJsonAxiosInstance } from '@/api'

export default {
  name: "AdminNoticeCreateComp",
  setup() {
    const user = useUserStore();

    const noticeData = {
      noticeTitle: "",
      noticeContent: "",
      userNo: user.getLoginUserInfo.userNo,
    };

    const router = useRouter();

    function registerNotice() {
      $addNotice(noticeData)
        .then((res) => {
          console.log(res.data);
          router.push("/admin/notices");
        })
        .catch((err) => console.log(err));
    }

    return {
      registerNotice,
      noticeData,
    };
  },
};
</script>

<style scoped >
/*글쓰기 관련 ----------*/
.content-notice {
  display: flex;
  flex-direction: column;
  align-items: center;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fff;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 2px 2px 10px orange;
}

fieldset {
  border: none;
  padding: 0;
  margin: 0;
}

table {
  width: 100%;
  border-collapse: collapse;
  table-layout: fixed;
}

td:first-child {
  width: 70px;
}

input[type="text"],
textarea {
  width: 100%;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #ccc;
  margin-bottom: 10px;
}

input[type="submit"] {
  background-color: orange;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}

input:focus {outline: 2px solid orange;}
textarea:focus {outline: 2px solid orange;}

</style>
