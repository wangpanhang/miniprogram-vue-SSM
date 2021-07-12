import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import FoodInsert from '../components/food/foodinsert.vue'
import FoodManage from '../components/food/foodmanage.vue'
import UserReview from '../components/user/userreview.vue'
import UserDynamic from '../components/user/userdynamic.vue'
import AlertPassword from '../components/self/alertpwd.vue'
import Welcome from '../components/welcome.vue'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  }, {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [{
      path: '/welcome',
      component: Welcome
    }, {
      path: '/foodinsert',
      component: FoodInsert
    }, {
      path: '/foodmanage',
      component: FoodManage
    }, {
      path: '/userreview',
      component: UserReview
    }, {
      path: '/userdynamic',
      component: UserDynamic
    }, {
      path: '/alertpwd',
      component: AlertPassword
    }]
  }
]

const router = new VueRouter({
  routes
})

// 路由导航守卫， 防止未登录进入后台系统
router.beforeEach((to, from, next) => {
  //to将要访问的路径
  //from 表示从哪个路径跳转而来
  //next 是一个函数，表示放行
  if (to.path === '/login') return next()
  const tokenstr = window.sessionStorage.getItem("token")
  if (!tokenstr) return next('/login')
  next()
})

export default router