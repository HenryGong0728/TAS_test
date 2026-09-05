// src/main.ts
import { createApp } from 'vue'
import { createPinia } from 'pinia'

// 1. 引入 Element Plus 相关资源
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
import router from './router'

const app = createApp(App)

// 2. 挂载 Pinia
app.use(createPinia())

// 3. 挂载路由
app.use(router)

// 4. 挂载 Element Plus
app.use(ElementPlus)

app.mount('#app')
