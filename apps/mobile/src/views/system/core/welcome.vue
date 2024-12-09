<template>
    <div class="flex h-full content-center items-center justify-items-center align-middle">
        <div class="m-auto inline-block h-auto text-center">
            <van-loading type="spinner" />
        </div>
    </div>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { onMounted } from 'vue';

onMounted(async () => {
    log('Page <<welcome>> mounted.');
});
</script>
