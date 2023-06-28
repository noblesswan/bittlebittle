<template>
<div class="content-board">
    <form @submit.prevent="editBoard" style="margin: 0 auto; height: 480px; width: 730px;" class="content-form">
          <fieldset>
            <br>
              <table style=" padding-left: 35px; padding-top: 20px;">
                  <tr>
                      <td style="width: 5rem;"><b>제목</b></td>
                      <td>
                        <input type="text" v-model="board.boardTitle" id="title"  required>
                      </td>
                  </tr>
                  <tr>
                      <td><b>내용</b></td>
                      <td>
                        <textarea v-model="board.boardContent" id="content-text" cols="52" rows="12" required></textarea>
                      </td>
                  </tr>
                  <tr class="buttons">
                      <td colspan="2">
                        <input type="submit" value="수정하기" id="submit2">
                        <input type="button" value="뒤로가기" id="cancel">
                      </td>
                  </tr>
              </table>
              <br>
          </fieldset>
      </form>
</div>
</template>

<script>
// import { useBoardStore } from '@/stores/boards'
import { useUserStore } from '@/stores/users'
import { useBoardStore } from '@/stores/boards'
import { $editBoard } from '@/api/board'
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

export default {
  name: 'BoardEditComp',
  setup () {
    const board = useBoardStore().getBoardInfo

    const loginUser = useUserStore().getLoginUserInfo
    const router = useRouter()

    function editBoard () {
      $editBoard(loginUser.userNo, board)
        .then(res => {
          console.log(res.data)
          router.push(`/boards/${board.boardNo}`)
        }).catch(err => console.log(err))
    }

    onMounted(() => {
      console.log(board)
    })

    return {
      board,
      editBoard
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

/*게시판 작성하기-----------------------------------------------*/
/* fieldset {
    background-color: #333;
    border-radius: 50px;
    padding: 20px;
    padding-right: 50px;
    color:rgb(94, 87, 90);
    border: none;
} */
/* legend {
    color: rgb(70, 70, 70);
}

.content-board .content-form {
  background-color: #333;
  border-radius: 1%;
}

#title {
    background-color: rgb(253, 253, 253);
    border-radius: 5px;
    border: none;
    padding: 10px;
    width: 550px;
    margin-top:10px;
    margin-bottom: 10px;
}
#content-text {
    background-color: rgb(253, 253, 253);
    border-radius: 5px;
    border: none;
    padding: 10px;
    resize:none;
    width: 550px;
    margin-bottom: 10px;
}
*/
.buttons {
  text-align: center;
}

/*
.buttons input {
  width: 100px;
  height: 40px;
  background-color: white;
  color: black;
  border: none;
  margin-left: 1rem;
}
#submit {
  box-shadow: #f4b967 0px 1px 10px;
}
#submit2{

}
#submit:hover {
    background-color: #ffae00;
    color: white;
    cursor: pointer;
}
#cancel {
  box-shadow: #f47a67 0px 1px 10px;
}

#cancel:hover {
  background-color: #ff1e00;
    color: white;
    cursor: pointer;
}

input #fileselect {
    background-color: white;
    border: none;
}
td>b {
    font-size: 20px;
    color:orange;
    text-align: center;
    margin: 17px;
} */

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
