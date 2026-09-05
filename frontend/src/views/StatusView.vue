<template>
  <div class="page">
    <p class="status" :class="connected ? 'ok' : 'fail'">
      {{ statusText }}
    </p>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { checkHealth } from '@/api/health'

// null=检测中 true=连接成功 false=连接失败
const connected = ref<boolean | null>(null)

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
</script>

<style scoped>
.page {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.status {
  font-size: 32px;
  font-weight: 600;
  color: #f56c6c;
}

.status.ok {
  color: #67c23a;
}
</style>
