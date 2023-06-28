<template>
  <div class="container">
   <div class="content"> <!-- 수정 -->

    <p class="title">커뮤니티</p>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: 'BoardView'
}
</script>

<style scoped >
  .container {
    margin: 0 auto;
    width: 900px;
    min-height: 70vh; /* 추가 */
    display: flex;
    flex-direction: column; /* 추가 */
    background-color: #FFFAF6; /* 추가 */
  }

  .content {
    flex-grow: 1; /* 추가 */
    background-color: #FFFAF6; /* 추가 */
    padding: 20px; /* 추가 */
    border-radius: 5px; /* 추가 */
  }

  .title {
    font-size: 35px;
    text-align: center;
    margin-bottom: 30px;
  }

</style>
