<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/register">Register</router-link>
    </div>
    <router-view/>
  </div>
</template>

<script>
import 'bootstrap/dist/js/bootstrap.min'
export default {
  name: 'App',
  created () {
    this.$bus.$on('myDataFetched', myData => {
      // Initializing the real time connection
      this.$rt.init(myData.settings.realTimeServerUrl, myData.user.token)
    })
  }
}
</script>
<style lang="scss">
  html, body {
    height: 100%;
    font-size: 14px;
  }

  #app, .page {
    height: 100%;
    position: relative;
  }

  .page {
    display: flex;
    flex-direction: column;
  }
</style>
