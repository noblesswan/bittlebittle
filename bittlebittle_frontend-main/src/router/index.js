import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "@/modules/bottles/views/HomeView"
import DefaultLayout from "@/modules/layouts/default/Index"

const routes = [
  {path : '/'
  , component : DefaultLayout
  , children : [
      {
        path: '/',
        name: 'home',
        component: HomeView
      }
  ]
}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
