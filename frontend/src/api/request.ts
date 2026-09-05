import axios from 'axios'

// 创建 axios 实例
const service = axios.create({
  // 读取 .env 中的 VITE_API_BASE_URL，未配置时默认指向本地后端
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
  timeout: 5000, // 请求超时时间
})

// 请求拦截器（可以在这里统一加 Token 等）
service.interceptors.request.use(
  (config) => {
    return config
  },
  (error) => {
    return Promise.reject(error)
  },
)

// 响应拦截器（可以在这里统一处理后端返回的错误码）
service.interceptors.response.use(
  (response) => {
    return response.data // 直接返回后端的数据
  },
  (error) => {
    return Promise.reject(error)
  },
)

export default service
