import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global_container.css'
import axios from 'axios'

axios.defaults.baseURL = "http://localhost:8080/wxfood"
axios.interceptors.request.use(config =>{
  //将token添加至请求头
  console.log(config)
  config.headers.Authorization = window.sessionStorage.getItem('token')
  // if(config.method === 'post'){
  //   config.headers[ 'Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8'
  // }
  return config
})

Vue.prototype.$http = axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
