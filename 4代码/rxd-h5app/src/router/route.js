import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/main/Home.vue'
import Classfy from '../components/classfy/Classfy.vue'
import Shop from '../components/shop/Shop.vue'
import Shopcart from '../components/shopcart/Shopcart.vue'
import Mine from '../components/mine/Mine.vue'

Vue.use(Router)

const routes = [
  // 重定向,用于首页跳转
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/classfy',
    name: 'Classfy',
    component: Classfy
  },
  {
    path: '/shop',
    name: 'Shop',
    component: Shop
  },
  {
    path: '/shopcart',
    name: 'Shopcart',
    component: Shopcart
  },
  {
    path: '/mine',
    name: 'Mine',
    component: Mine
  }
]
const router = new Router({
  routes: routes
})
export default router
