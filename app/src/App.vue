<template>
  <div id="app">
    <div class="app-content">
      <router-view v-if="isrefresh"></router-view>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'App',
    components: {

    },
    data(){
      return {
        isrefresh:true
      }
    },
    provide() {
      return {
        reload: this.reload
      }
    },
    methods: {
      reload() {
        this.isrefresh = false
        this.$nextTick(function() {
          this.isrefresh = true
        })
      }
    }

  }
</script>

<style>
  @import url("../static/css/reset.css");

  #app {
    height: 100%;
    width: 100%;

  }

  .app-content {
    height: 947px;
    width: 100%;
  }
</style>
