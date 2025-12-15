import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name:  'Home',
    component: () => import('../pages/Home.vue')
  },
  {
    path: '/customers',
    name: 'Customers',
    component: () => import('../pages/Customers.vue')
  },
  {
    path: '/inventory',
    name: 'Inventory',
    component: () => import('../pages/Inventory.vue')
  },
  {
    path:  '/repairs',
    name: 'Repairs',
    component:  () => import('../pages/Repairs.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router