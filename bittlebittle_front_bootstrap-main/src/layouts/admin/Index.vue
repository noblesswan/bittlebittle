<template>
  <the-header></the-header>
  <router-view></router-view>
  <the-footer></the-footer>
</template>

<script>
import TheFooter from '@/modules/shared-components/TheFooter.vue'
import TheHeader from '@/modules/shared-components/TheHeader.vue'
export default {
  name: 'AdminLayout',
  components: {
    TheFooter,
    TheHeader
  }
}
</script>

<style>

</style>
