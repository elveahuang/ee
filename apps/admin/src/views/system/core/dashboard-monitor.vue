<template>
    <el-card title="工作台" shadow="never">
        <span>开心每一天</span>
    </el-card>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { onMounted } from 'vue';

onMounted(async () => {
    log('Page <<Dashboard Monitor>> mounted.');
});
</script>
