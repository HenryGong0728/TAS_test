// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/submissions' },
    {
      path: '/submissions',
      name: 'Submissions',
      component: () => import('@/views/ListView.vue'),
    },
  ],
})

export default router
