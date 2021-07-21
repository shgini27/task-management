import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const Home = { template: '<div>home</div>' }

const LoginPage = () => import('../views/LoginPage.vue')

const routes = [
  {
    path: '/',
    component: Home
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/LoginPage.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
