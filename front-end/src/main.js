import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Vuelidate from 'vuelidate'
import { i18n } from './i18n'

// Bootstrap axios
axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.response.use(
  response => response,
  error => {
    return Promise.reject(error)
  }
)

Vue.config.productionTip = false

Vue.use(Vuelidate)

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
