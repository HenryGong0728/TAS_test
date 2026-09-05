import request from './request'

export interface ApplicationForm {
  employeeNo: string
  name: string
  direction: string
}

export interface ApplicationRecord extends ApplicationForm {
  id: number
  createdAt: string
}

export interface ApplicationResponse {
  success: boolean
  message: string
  data?: ApplicationRecord
}

export function submitApplication(data: ApplicationForm) {
  return request.post<unknown, ApplicationResponse>('/api/applications', data)
}

export function listApplications() {
  return request.get<unknown, ApplicationRecord[]>('/api/applications')
}
