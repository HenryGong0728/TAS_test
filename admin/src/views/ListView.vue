<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>考核管理端 · 提交记录</span>
          <el-button type="primary" :loading="loading" @click="loadList">
            刷新
          </el-button>
        </div>
      </template>

      <el-table v-loading="loading" :data="list" border stripe>
        <el-table-column prop="id" label="ID" width="70" />
        <el-table-column prop="name" label="姓名" width="100" />
        <el-table-column prop="studentId" label="学号" width="120" />
        <el-table-column prop="direction" label="报考方向" width="100" />
        <el-table-column prop="phone" label="联系方式" width="140" />
        <el-table-column prop="note" label="备注" min-width="160" />
        <el-table-column prop="createdAt" label="提交时间" width="180" />
      </el-table>

      <p class="tip">
        当前接口地址：{{ apiBase }} · 共 {{ list.length }} 条记录
      </p>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getSubmissions, type SubmissionItem } from '@/api/submission'

const list = ref<SubmissionItem[]>([])
const loading = ref(false)
const apiBase = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

async function loadList() {
  loading.value = true
  try {
    const data = await getSubmissions()
    list.value = data as SubmissionItem[]
  } catch (error) {
    ElMessage.error('获取数据失败，请检查后端服务是否启动、.env 接口地址是否正确')
  } finally {
    loading.value = false
  }
}

onMounted(loadList)
</script>

<style scoped>
.page {
  max-width: 1000px;
  margin: 40px auto;
  padding: 0 16px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: 600;
  font-size: 16px;
}

.tip {
  color: #909399;
  font-size: 12px;
  margin: 8px 0 0;
}
</style>
