import request from './request'

export interface SubmissionItem {
  id: number
  name: string
  studentId: string
  direction: string
  phone: string
  note: string
  createdAt: string
}

// 查询全部提交记录
export function getSubmissions() {
  return request.get('/api/submissions')
}
