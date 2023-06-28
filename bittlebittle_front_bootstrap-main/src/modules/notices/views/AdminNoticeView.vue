<template>
  <div class="container">
   <div class="content">

    <p class="title">Notice</p>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminNoticeView'
}

</script>

<style scoped >
  .container {
    margin: 0 auto;
    width: 900px;
    min-height: 70vh;
    display: flex;
    flex-direction: column;
    background-color: #FFFAF6;
  }

  .content {
    flex-grow: 1;
    background-color: #FFFAF6;
    padding: 20px;
    border-radius: 5px;
  }

  .title {
    font-size: 35px;
    text-align: center;
    margin-bottom: 30px;
  }

</style>
