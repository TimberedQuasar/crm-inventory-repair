import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [],
})

const routes = [
  { path: '/', name: 'home', component: () => import('../views/HomeView.vue') },
]

export default router
