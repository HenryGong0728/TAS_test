import request from './request'

// 检查后端连接状态
export function checkHealth() {
  return request.get('/api/health')
}
