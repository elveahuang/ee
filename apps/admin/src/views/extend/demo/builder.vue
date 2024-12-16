<template>
    <div class="m-6">
        <app-home-builder />
    </div>
</template>

<script setup lang="ts">
import { log } from '@commons/core/utils';
import { AppHomeBuilder } from '@commons/webapp/components';
import { onMounted } from 'vue';

onMounted(async () => {
    log('Page <<Builder>> mounted.');
});
</script>
