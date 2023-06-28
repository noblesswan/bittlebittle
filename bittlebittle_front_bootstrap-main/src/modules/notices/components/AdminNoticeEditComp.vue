<template>
<div class="content-notice">
  <div>
    <form @submit.prevent="editNotice" style="margin: 0 auto; width: 730px;">
          <fieldset>
              <table style=" padding-left: 35px; padding-top: 20px;">
                  <tr>
                      <td style="width: 5rem;"><b>제목</b></td>
                      <td>
                        <input type="text" v-model="notice.noticeTitle" id="title"  required>
                      </td>
                  </tr>
                  <tr>
                      <td><b>내용</b></td>
                      <td>
                        <textarea v-model="notice.noticeContent" id="content-text" cols="52" rows="12" required></textarea>
                      </td>
                  </tr>
                  <tr class="buttons">
                      <td colspan="2">
                        <input type="submit" value="수정하기" id="submit">
                        <input type="button" value="뒤로가기" id="cancel">
                      </td>
                  </tr>
              </table>
              <br>
          </fieldset>
      </form>
  </div>
</div>
</template>

<script>
// import { useNoticeStore } from '@/stores/notices'
import { useUserStore } from '@/stores/users'
import { useNoticeStore } from '@/stores/notices'
import { $editNotice } from '@/api/notice'
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

export default {
  name: 'AdminNoticeEditComp',
  setup () {
    const notice = useNoticeStore().getNoticeInfo

    const loginUser = useUserStore().getLoginUserInfo

    const router = useRouter()

    function editNotice () {
      $editNotice(notice.noticeNo, notice)
        .then(res => {
          console.log(res.data)
          console.log(notice.noticeNo)
          router.push(`/admin/notices/${notice.noticeNo}`)
        }).catch(err => console.log(err))
    }

    onMounted(() => {
      console.log(notice)
    })

    return {
      notice,
      editNotice
    }
  }
}
</script>

<style scoped >
/*글쓰기 관련 ----------*/
a{
    text-decoration: none;
    color: #3B3B3B;
}
select:focus {outline: 1px solid orange;}
input:focus {outline: 1px solid orange;}
textarea:focus {outline: 1px solid orange;}

.buttons {
  text-align: center;
}


.content-board {
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
  margin: 10px;
}


/*클릭 시 테두리*/
input:focus {outline: 2px solid orange;}
textarea:focus {outline: 2px solid orange;}

</style>
