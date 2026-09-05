// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/status' },
    {
      path: '/status',
      name: 'Status',
      component: () => import('@/views/StatusView.vue'),
    },
  ],
})

export default router
