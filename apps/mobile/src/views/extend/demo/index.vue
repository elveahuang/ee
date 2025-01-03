<template>
    <app-divider />
    <van-cell-group inset class="mt-2">
        <van-cell title="Slide" to="/demo/slider" is-link />
        <van-cell title="Editor" to="/demo/editor" is-link />
        <van-cell title="Player" to="/demo/player" is-link />
    </van-cell-group>
    <app-divider />
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { AppDivider } from '@commons/mobile/components';
import { onMounted } from 'vue';

onMounted(async () => {
    log('Page <<DemoPage>> mounted.');
});
</script>
