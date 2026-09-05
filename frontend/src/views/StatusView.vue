<template>
  <div class="page">
    <main class="panel">
      <section class="header">
        <p class="eyebrow">人才盘点系统</p>
        <h1>人员信息测试</h1>
        <p class="desc">请填写人员基础信息并提交，用于确认前端页面、环境配置和接口连接是否正常。</p>
      </section>

      <section class="status-card" :class="connected ? 'ok' : 'fail'">
        <span class="dot"></span>
        <span>{{ statusText }}</span>
      </section>

      <form class="form" @submit.prevent="handleSubmit">
        <label>
          <span>人员编号</span>
          <input v-model.trim="form.employeeNo" placeholder="请输入人员编号" />
        </label>

        <label>
          <span>姓名</span>
          <input v-model.trim="form.name" placeholder="请输入姓名" />
        </label>

        <label>
          <span>岗位方向</span>
          <select v-model="form.direction">
            <option value="">请选择岗位方向</option>
            <option value="设计方向">设计方向</option>
            <option value="前端方向">前端方向</option>
            <option value="后端方向">后端方向</option>
          </select>
        </label>

        <button type="submit" :disabled="submitting">
          {{ submitting ? '提交中...' : '提交测试' }}
        </button>
      </form>

      <section v-if="resultMessage" class="result" :class="resultOk ? 'ok' : 'fail'">
        {{ resultMessage }}
      </section>

      <section v-if="lastRecord" class="record">
        <p>最近提交</p>
        <div>人员编号：{{ lastRecord.employeeNo }}</div>
        <div>姓名：{{ lastRecord.name }}</div>
        <div>岗位方向：{{ lastRecord.direction }}</div>
      </section>
    </main>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { submitApplication, type ApplicationForm, type ApplicationRecord } from '@/api/applications'
import { checkHealth } from '@/api/health'

// null=检测中 true=连接成功 false=连接失败
const connected = ref<boolean | null>(null)
const submitting = ref(false)
const resultMessage = ref('')
const resultOk = ref(false)
const lastRecord = ref<ApplicationRecord | null>(null)
const form = ref<ApplicationForm>({
  employeeNo: '',
  name: '',
  direction: '',
})

const statusText = computed(() => {
  if (connected.value === true) return '后端连接成功'
  if (connected.value === false) return '后端未连接'
  return '检测中...'
})

onMounted(async () => {
  try {
    await checkHealth()
    connected.value = true
  } catch {
    connected.value = false
  }
})

async function handleSubmit() {
  resultMessage.value = ''
  resultOk.value = false

  if (!form.value.employeeNo || !form.value.name || !form.value.direction) {
    resultMessage.value = '请填写人员编号、姓名和岗位方向'
    return
  }

  submitting.value = true
  try {
    const response = await submitApplication(form.value)
    resultOk.value = response.success
    resultMessage.value = response.message
    lastRecord.value = response.data ?? null
  } catch {
    resultMessage.value = '提交失败，请检查后端服务和接口地址配置'
  } finally {
    submitting.value = false
  }
}
</script>

<style scoped>
.page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 32px;
  background: #f5f7fb;
}

.panel {
  width: min(560px, 100%);
  padding: 32px;
  border: 1px solid #d8dee9;
  border-radius: 8px;
  background: #ffffff;
  box-shadow: 0 12px 28px rgb(15 23 42 / 8%);
}

.header {
  margin-bottom: 24px;
}

.eyebrow {
  margin: 0 0 8px;
  color: #4f46e5;
  font-size: 14px;
  font-weight: 600;
}

h1 {
  margin: 0;
  color: #111827;
  font-size: 28px;
}

.desc {
  margin: 12px 0 0;
  color: #4b5563;
  line-height: 1.7;
}

.status-card,
.result,
.record {
  border-radius: 8px;
  padding: 14px 16px;
}

.status-card {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
  border: 1px solid #fecaca;
  color: #b91c1c;
  background: #fef2f2;
  font-weight: 600;
}

.status-card.ok {
  border-color: #bbf7d0;
  color: #15803d;
  background: #f0fdf4;
}

.dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: currentColor;
}

.form {
  display: grid;
  gap: 16px;
}

label {
  display: grid;
  gap: 8px;
  color: #374151;
  font-weight: 600;
}

input,
select {
  height: 42px;
  padding: 0 12px;
  border: 1px solid #cbd5e1;
  border-radius: 6px;
  font: inherit;
  color: #111827;
  background: #ffffff;
}

input:focus,
select:focus {
  border-color: #4f46e5;
  outline: none;
  box-shadow: 0 0 0 3px rgb(79 70 229 / 14%);
}

button {
  height: 44px;
  border: 0;
  border-radius: 6px;
  color: #ffffff;
  background: #4f46e5;
  font: inherit;
  font-weight: 700;
  cursor: pointer;
}

button:disabled {
  opacity: 0.65;
  cursor: not-allowed;
}

.result {
  margin-top: 18px;
  border: 1px solid #fecaca;
  color: #b91c1c;
  background: #fef2f2;
}

.result.ok {
  border-color: #bbf7d0;
  color: #15803d;
  background: #f0fdf4;
}

.record {
  margin-top: 16px;
  color: #1f2937;
  background: #f8fafc;
}

.record p {
  margin: 0 0 8px;
  font-weight: 700;
}
</style>
