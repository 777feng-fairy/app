<template>
  <div>
    <man-header></man-header>
    <man-search></man-search>
    <man-content-view></man-content-view>
  </div>
</template>

<script>

  import manHeader from '../components/header.vue'
  import manSearch from '../components/search.vue'
  import manContentView from '../components/contentview.vue'

  export default{
    components:{
     
      manHeader,
      manSearch,
      manContentView
    }
  }

</script>

<style>
</style>
