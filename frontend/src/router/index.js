import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Test from '../views/Test.vue'
import Todo from '../views/Todo.vue'
// import SignUp from '../views/SignUp.vue'
// import Login from '../views/Login.vue'.
import Concave from '../views/Concave.vue'

// For Cafe Board
import BoardListPage from '../view/BoardListPage.vue'
import BoardRegisterPage from '../view/BoardRegisterPage.vue'
import BoardModifyPage from '../view/BoardModifyPage.vue'
import BoardReadPage from '../view/BoardReadPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/todo',
    name: 'Todo',
    component: Todo
  },
  {
    path: '/concave',
    name: 'Concave',
    components: {
      default: Concave
    }
  },
  {
    path: '/boardlist',
    name: 'BoardListPage',
    components: {
      default: BoardListPage
    }
  },
  {
    path: '/boardregister',
    name: 'BoardRegisterPage',
    components: {
      default: BoardRegisterPage
    }
  },
  {
    path: '/boardmodify',
    name: 'BoardModifyPage',
    components: {
      default: BoardModifyPage
    }
  },
  {
    path: '/boardread',
    name: 'BoardReadPage',
    components: {
      default: BoardReadPage
    }
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
