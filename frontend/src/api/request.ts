import axios from 'axios'

// 创建 axios 实例
const service = axios.create({
  // 读取 .env 中的 VITE_API_BASE_URL，未配置时默认指向本地后端
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
  timeout: 5000, // 请求超时时间
})

// 响应拦截器：直接返回后端的数据体
service.interceptors.response.use(
  (response) => {
    return response.data
  },
  (error) => {
    return Promise.reject(error)
  },
)

export default service
